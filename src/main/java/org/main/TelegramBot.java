package org.main;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.io.IOException;

public class TelegramBot extends TelegramLongPollingBot {
    @Override
    public String getBotUsername() {
        return "Ohonovskiy_weather_bot";
    }

    @Override
    public String getBotToken() {
        return "5605062169:AAGSelKV72opcZXWx8v16M5LkQ1Rolpr_q8";
    }

    @Override
    public void onUpdateReceived(Update update) {
        if(update.hasMessage() && update.getMessage().hasText()){
            String user_message = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            System.out.println(user_message);

            SendMessage sendMessage = new SendMessage();
            try {
                sendMessage.setChatId(chat_id);
                sendMessage.setText(Parser.parse(user_message));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            try {
                execute(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

public class Parser {
    public static void parse() throws IOException {
        Scanner scanner = new Scanner(System.in);
        
        

        String city, temp, hum, wind, status, name, day;
        city = scanner.nextLine();

        Document doc = Jsoup.connect("https://www.google.com/search?q="+city+"weather").get();
        Element tempElem = doc.selectFirst("span.wob_t.q8U8x");

        temp = Objects.requireNonNull(doc.selectFirst("span.wob_t.q8U8x")).text();
        hum = Objects.requireNonNull(doc.selectFirst("#wob_hm")).text();
        wind = Objects.requireNonNull(doc.selectFirst("#wob_ws")).text();
        status = Objects.requireNonNull(doc.selectFirst("#wob_dc")).text();
        name = Objects.requireNonNull(doc.selectFirst("#wob_loc.q8U8x")).text();
        day = Objects.requireNonNull(doc.selectFirst("#wob_dts")).text();
        
        if(tempElem == null){
            System.out.println("City's not found");
            System.exit(0);
        }

        System.out.println("Погода у " + name + ". ("+day+", "+status+")"+
                "\n Температура: " + temp + "°C" +
                "\n Вологість: " + hum +
                "\n Вітер: " + wind);
    }
}

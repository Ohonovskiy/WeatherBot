package org.main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Parser {
    public static String parse(String city) throws IOException {

        String temp, hum, wind, status, name, day, encoded;

        encoded = URLEncoder.encode(city+" weather", UTF_8);

        Document doc = Jsoup.connect("https://www.google.com/search?q="+encoded).get();

        temp = (Objects.requireNonNull(doc.selectFirst("span.wob_t.q8U8x"))).text();
        hum = (Objects.requireNonNull(doc.selectFirst("#wob_hm"))).text();
        wind = (Objects.requireNonNull(doc.selectFirst("#wob_ws"))).text();
        status = (Objects.requireNonNull(doc.selectFirst("#wob_dc"))).text();
        name = (Objects.requireNonNull(doc.selectFirst("#wob_loc.q8U8x"))).text();
        day = (Objects.requireNonNull(doc.selectFirst("#wob_dts"))).text();

        if(temp.equals("")){
            System.out.println(doc);
            return ("Населений пункт не найдено");
        }

        return ("Погода у " + name + ". ("+day+", "+status+")"+
                "\nТемпература: " + temp + "°C" +
                "\nОпади: " + hum +
                "\nВітер:  " + wind);
    }
}

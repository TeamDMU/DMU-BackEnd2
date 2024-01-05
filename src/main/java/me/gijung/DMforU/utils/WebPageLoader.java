package me.gijung.DMforU.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class WebPageLoader {
    public Document getHTML(String url)  {
        // Jsoup을 사용하여 웹 페이지의 HTML 가져오기
        try {
            return Jsoup.connect(url).get();
        } catch (IOException e) {
            return null;
        }
    }
}

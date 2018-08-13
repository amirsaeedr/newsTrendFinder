package ir.sahab.nimbo.moama.newstrendfinder.Util;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class Util {
    public static Document getPage(String link){
        try {
            return Jsoup.connect(link).validateTLSCertificates(true).get();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("connection failed");
        }
    }
}

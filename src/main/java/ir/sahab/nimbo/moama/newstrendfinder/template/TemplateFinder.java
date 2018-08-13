package ir.sahab.nimbo.moama.newstrendfinder.template;

import ir.sahab.nimbo.moama.newstrendfinder.Util.Util;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Integer.min;

public class TemplateFinder {
    public static Template findTemplate(String rss) throws IOException {
        try {
            return work(rss);
        }catch (RuntimeException err){
            if (err.getMessage().equals("connection failed"))
                throw new  IOException();
            else
                throw err;
        }
    }

    private static Template work(String rss) {
        Document rssDoc = Util.getPage(rss);
        Document goodPage = findMaxTextPage(rssDoc);
        String dateFormat = findDateFormat(rssDoc);
        String newsBodyAddress = findBodyAddress(goodPage);
        return new Template(newsBodyAddress, "Class", dateFormat, rss);
    }

    private static String findBodyAddress(Document goodPage) {
        ArrayList<MyElement> myElements = new ArrayList<>();
        Elements classElements = goodPage.getElementsByAttribute("class");
        for (int i = 0; i < classElements.size(); i++) {
            if (classElements.get(i).outerHtml().contains("mobile"))
                continue;
            int numberOFDot = classElements.get(i).text().split("[.]+").length;
            myElements.add(new MyElement(classElements.get(i), numberOFDot));
        }
        Collections.reverse(myElements);
        Collections.sort(myElements);
        Collections.reverse(myElements);
        for (int i = 0; i < myElements.size() / 5; i++) {
            if (!myElements.get(i).getElement().text().contains(myElements.get(i + 1).getElement().text())) {
                return myElements.get(i).getElement().className();
            }
        }
        return null;
    }
    private static String findDateFormat(Document rssDoc) {
        Elements elementsOfPubDate = rssDoc.getElementsByTag("pubDate");
        return DateFomatFinder.parse(elementsOfPubDate.get(0).text());
    }
    private static Document findMaxTextPage(Document rssDoc) {
        Elements elementsOfLinks = rssDoc.getElementsByTag("link");
        Document maxTextPage = null;
        int maxTextSize = -1;
        ArrayList<Document> documentArrayList=new ArrayList<>();
        elementsOfLinks.subList(4,min(20,elementsOfLinks.size())).parallelStream().forEach(
                element-> documentArrayList.add(Util.getPage(element.text())));
        for (Document document : documentArrayList) {
            if (document.text().length() > maxTextSize) {
                maxTextSize = document.text().length();
                maxTextPage=document;
            }
        }
        return maxTextPage;
    }
}

class MyElement implements Comparable<MyElement> {
    private int numberOfDots;
    private Element element;

    MyElement(Element element, int numberOfDots) {
        this.numberOfDots = numberOfDots;
        this.element = element;
    }

    @Override
    public int compareTo(MyElement o) {
        if (numberOfDots == o.getNumberOfDots()) {
            return 0;
        } else if (numberOfDots > o.getNumberOfDots()) {
            return 1;
        }
        return -1;
    }


    int getNumberOfDots() {
        return numberOfDots;
    }

    void setNumberOfDots(int numberOfDots) {
        this.numberOfDots = numberOfDots;
    }

    Element getElement() {
        return element;
    }

    void setElement(Element element) {
        this.element = element;
    }
}

class DateFomatFinder {

    private static final String[] formats =
            {
                    "EEE, dd MMM yyyy HH:mm:ss Z",
                    "dd MMM yyyy HH:mm:ss Z",
                    "EEE, dd MMM yyyy HH:mm",
                    "dd MMM yyyy HH:mm",
                    "yyyy-MM-dd'T'HH:mm:ss.SSSZ",
                    "EEE, d MMM yyyy HH:mm:ss Z",
                    "EEE, dd MMM yyyy HH:mm:ss zzz",
                    "yyyy-mm-dd HH:mm:ss",
                    "yyyy-mm-dd hh:mm:ss",
                    "yyyy-MM-dd'T'HH:mm:ssZ",
                    "yyyy-MM-dd'T'HH:mm:ss",
                    "yyyy-MM-dd'T'HH:mm:ssZ",
                    "yyyy-MM-dd'T'HH:mm:ss Z",
                    "yyyy-MM-dd'T'HH:mm:ss.SSSXXX",
                    "yyyy-MM-dd'T'hh:mm:ssXXX",
                    "dd MMM yyyy HH:mm:ss Z",
                    "MM/dd/yyyy",
            };

    public static String parse(String d) {
        if (d != null) {
            for (String parse : formats) {
                SimpleDateFormat sdf = new SimpleDateFormat(parse);
                try {
                    sdf.parse(d);
                    return parse;
                } catch (ParseException ignored) {
                }
            }
        }

        return null;
    }
}
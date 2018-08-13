package ir.sahab.nimbo.moama.newstrendfinder.template;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Objects;

public class Template implements Serializable {
    private String attValue;
    private String funcName;
    private String dateFormatString;
    private String rssLink;
    private SimpleDateFormat dateFormatter;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Template)) return false;
        Template template = (Template) o;
        return Objects.equals(getAttValue(), template.getAttValue()) &&
                Objects.equals(getFuncName(), template.getFuncName()) &&
                Objects.equals(getDateFormatString(), template.getDateFormatString()) &&
                Objects.equals(getRssLink(), template.getRssLink());
    }

    public Template(String attrValue, String attrModel, String dateFormat, String rssLink) {
        this.attValue = attrValue;
        this.rssLink = rssLink;
        switch (attrModel.toLowerCase()) {
            case "id":
                funcName = "getElementById";
                break;
            default:
                funcName = "getElementsBy" + attrModel;
                break;
        }
        this.dateFormatString = dateFormat;
        this.dateFormatter = new SimpleDateFormat(dateFormat, Locale.ENGLISH);
    }

    public SimpleDateFormat getDateFormatter() {
        return dateFormatter;
    }

    public String getDateFormatString() {
        return dateFormatString;
    }

    public String getAttValue() {
        return attValue;
    }

    public String getFuncName() {
        return funcName;
    }

    public String getRssLink() {
        return rssLink;
    }
}

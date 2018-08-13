package ir.sahab.nimbo.moama.newstrendfinder.database.news;

import java.util.Date;

public class News {
    private String title;
    private String author;
    private String description;
    private String content;
    private String website;
    private String link;
    private Date date;

    public News(String title, String author, String description, String content, String website, String link, Date date) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.content = content;
        this.website = website;
        this.link = link;
        this.date = date;
    }

    private News(Builder builder) {
        this.title = builder.title;
        this.author = builder.author;
        this.description = builder.description;
        this.content = builder.content;
        this.website = builder.website;
        this.date = builder.date;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public static Builder newNews() {
        return new Builder();
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getContent() {
        return content;
    }

    public String getWebsite() {
        return website;
    }

    public Date getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Title: " + title + "\n"
                + "Author: " + author + "\n"
                + "Website: " + website + "\n"
                + "Date: " + date + "\n"
                + "Description: " + description + "\n"
                + "Content: " + content + "\n";
    }

    public static final class Builder {
        private String title;
        private String author;
        private String description;
        private String content;
        private String website;
        private Date date;

        private Builder() {
        }

        public News build() {
            return new News(this);
        }

        // TODO: 7/16/18 Find a better way for ' stuff

        public Builder title(String title) {
            this.title = title.replace("'", ";");
            return this;
        }

        public Builder author(String author) {
            this.author = author.replace("'", ";");
            return this;
        }

        public Builder description(String description) {
            this.description = description.replace("'", ";");
            return this;
        }

        public Builder content(String content) {
            this.content = content.replace("'", ";");
            return this;
        }

        public Builder website(String website) {
            this.website = website;
            return this;
        }

        public Builder date(Date date) {
            this.date = date;
            return this;
        }
    }
}

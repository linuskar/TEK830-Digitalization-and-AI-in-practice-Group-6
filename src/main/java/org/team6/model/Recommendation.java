package org.team6.model;

public class Recommendation {
    private String title;
    private String text;
    private String url;

    public Recommendation(String title, String text, String url) {
        this.title = title;
        this.text = text;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }
}

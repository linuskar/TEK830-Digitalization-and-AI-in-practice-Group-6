package org.team6.model.recommendations;

public abstract class Recommendation {
    private String title;
    private String text;

    public Recommendation(String title, String text) {
        this.title = title;
        this.text = text;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public abstract String getRecommendationImage();

    public abstract String getReadMoreUrl();
}

package org.team6.model.Recommendations;

public class Recommendation {
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

    public String getRecommendationImage(){
        return RecommendationImagePath.getImagePath(title);
    }

    public String getReadMoreUrl() {
        return RecommendationReadMoreUrl.getReadMoreUrl(title);
    }
}

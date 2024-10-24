package org.team6.model.recommendations;

public class TipRecommendation extends Recommendation {
    public TipRecommendation(String title, String text) {
        super(title, text);
    }

    @Override
    public String getRecommendationImage() {
        return RecommendationImagePath.getTipImagePath(getTitle());
    }

    @Override
    public String getReadMoreUrl() {
        return RecommendationReadMoreUrl.getTipReadMoreUrl(getTitle());
    } 
}
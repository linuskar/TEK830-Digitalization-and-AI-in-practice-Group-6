package org.team6.model.Recommendations;

import org.team6.model.Products.ProductCategory;

public class Recommendation {
    private String title;
    private String text;
    private ProductCategory productCategory;

    public Recommendation(String title, String text, ProductCategory productCategory) {
        this.title = title;
        this.text = text;
        this.productCategory = productCategory;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getRecommendationImage(){
        return RecommendationImagePath.getImagePath(title, productCategory);
    }

    public String getReadMoreUrl() {
        return RecommendationReadMoreUrl.getReadMoreUrl(title, productCategory);
    }
}

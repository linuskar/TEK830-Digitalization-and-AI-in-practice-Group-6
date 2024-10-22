package org.team6.model.Recommendations;

import org.team6.model.Products.ProductCategory;

public class ProductRecommendation extends Recommendation {
    private ProductCategory productCategory;

    public ProductRecommendation(String title, String text, ProductCategory productCategory) {
        super(title, text);  
        this.productCategory = productCategory; 
    }


    @Override
    public String getRecommendationImage(){
        return RecommendationImagePath.getProductImagePath(getTitle(), productCategory);
    }

    @Override
    public String getReadMoreUrl() {
        return RecommendationReadMoreUrl.getProductReadMoreUrl(getTitle(), productCategory);
    }
}

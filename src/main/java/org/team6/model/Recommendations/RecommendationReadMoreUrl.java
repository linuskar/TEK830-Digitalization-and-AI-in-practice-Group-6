package org.team6.model.Recommendations;

import org.team6.model.Products.ProductCategory;

public class RecommendationReadMoreUrl {
    public static String getTipReadMoreUrl(String recommendationTitle) {
        // IKEA would have to implement tips that link to an indepth article on the IKEA website or something
        String readMoreUrl = "";
        switch (recommendationTitle) {
            case "Close the refrigerator door":
                break;
            case "Avoid hot food in the refigerator":
                break;
            case "Oven usage":
                break;
            case "Use lids":
                break;
            case "Avoid letting the water run":
                break;
            case "Reduce time in the shower":
                break;
            case "Lights usage":
                break;
            case "Use natural light":   
                break;        
            default:
                throw new AssertionError();
        }
        return readMoreUrl;
    }

    public static String getProductReadMoreUrl(String recommendationTitle, ProductCategory productCategory) {
        String readMoreUrl = "";
        switch (recommendationTitle) {
            case "Tynnerås":
                if (productCategory == ProductCategory.FRIDGE) {
                    readMoreUrl = "https://www.ikea.com/se/en/p/tynneras-fridge-ikea-500-freestanding-stainless-steel-10567950/";
                }
                else if (productCategory == ProductCategory.FREEZER) {
                    readMoreUrl = "https://www.ikea.com/se/en/p/tynneras-freezer-ikea-500-freestanding-stainless-steel-00568078/";
                }
                break;
            case "Mutebo":
                readMoreUrl = "https://www.ikea.com/se/en/p/mutebo-forced-air-oven-with-full-steam-ikea-700-stainless-steel-colour-10557041/";
                break;
            case "Alingsås":
                readMoreUrl = "https://www.ikea.com/se/en/p/alingsas-fridge-freezer-ikea-500-freestanding-stainless-steel-20567959/";
                break;
            case "Brändbo":
                readMoreUrl = "https://www.ikea.com/se/en/p/braendbo-forced-air-oven-ikea-500-black-60557656/";
                break;
            case "Forneby":
                readMoreUrl = "https://www.ikea.com/se/en/p/forneby-forced-air-oven-with-direct-steam-ikea-500-stainless-steel-colour-30557790/";
                break;
            case "Mattradition":
                readMoreUrl = "https://www.ikea.com/se/en/p/mattradition-forced-air-oven-ikea-300-black-80411724/";
                break;
            case "Mölnås":
                readMoreUrl = "https://www.ikea.com/se/en/p/moelnas-fridge-freezer-ikea-700-freestanding-stainless-steel-colour-70560842/";
                break;
            case "Lagan":
                readMoreUrl = "https://www.ikea.com/se/en/p/lagan-oven-stainless-steel-90547915/";
                break;
            case "Forsnäs":
                if (productCategory == ProductCategory.FREEZER){
                    readMoreUrl = "https://www.ikea.com/se/en/p/forsnaes-freezer-ikea-700-integrated-00572990/";
                }
                else if (productCategory == ProductCategory.FRIDGE) {
                    readMoreUrl = "https://www.ikea.com/se/en/p/forsnaes-fridge-ikea-700-integrated-30572984/";
                }
                break;
            default:
                throw new AssertionError();
        }
        
        return readMoreUrl;
    }
}

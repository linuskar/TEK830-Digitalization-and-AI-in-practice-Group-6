package org.team6.model.Recommendations;

public class RecommendationReadMoreUrl {
    public static String getReadMoreUrl(String recommendationTitle) {
        String readMoreUrl = "";
        switch (recommendationTitle) {
            case "Tynnerås":
                readMoreUrl = "https://www.ikea.com/se/en/p/tynneras-fridge-ikea-500-freestanding-stainless-steel-10567950/";
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
            default:
                throw new AssertionError();
        }
        
        return readMoreUrl;
    }
}

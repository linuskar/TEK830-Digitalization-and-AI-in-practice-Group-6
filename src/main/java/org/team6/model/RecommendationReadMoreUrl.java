package org.team6.model;

public class RecommendationReadMoreUrl {
    public static String getReadMoreUrl(String recommendationTitle) {
        String readMoreUrl = "";
        switch (recommendationTitle) {
            case "Tynnerås":
                readMoreUrl = "https://www.ikea.com/se/sv/p/tynneras-bord-svart-00431702/";
                break;
            case "Mutebo":
                readMoreUrl = "https://www.ikea.com/se/en/p/mutebo-forced-air-oven-with-full-steam-ikea-700-stainless-steel-colour-10557041/";
                break;
            default:
                throw new AssertionError();
        }
        System.out.println("Read more URL: " + readMoreUrl);
        return readMoreUrl;
    }
}

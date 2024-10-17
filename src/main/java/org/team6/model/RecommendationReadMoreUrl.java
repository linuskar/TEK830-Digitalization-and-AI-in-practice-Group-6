package org.team6.model;

public class RecommendationReadMoreUrl {
    public static String getReadMoreUrl(String recommendationTitle) {
        String readMoreUrl = "";
        switch (recommendationTitle) {
            case "Tynnerås":
                readMoreUrl = "https://www.ikea.com/se/sv/p/tynneras-bord-svart-00431702/";
                break;
            default:
                throw new AssertionError();
        }
        System.out.println("Read more URL: " + readMoreUrl);
        return readMoreUrl;
    }
}

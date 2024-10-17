package org.team6.model;

public class RecommendationImagePath {
    public static String getImagePath(String recommendationTitle) {
        String imagePath = "";
        switch (recommendationTitle) {
            case "Tynnerås":
                imagePath = "/org/team6/images/products/tynneras.png";
                break;
            case "Mutebo":
                imagePath = "/org/team6/images/products/mutebo.png";
                break;
            default:
                throw new AssertionError();
        }
        System.out.println("Image path: " + imagePath);
        return imagePath;
    }
}
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
            case "Alingsås":
                imagePath = "/org/team6/images/products/alingsas.png";
                break;
            case "Brändbo":
                imagePath = "/org/team6/images/products/brandbo.png";
                break;
            case "Forneby":
                imagePath = "/org/team6/images/products/forneby.png";
                break;
            case "Mattradition":
                imagePath = "/org/team6/images/products/mattradition.png";
                break;
            case "Mölnås":
                imagePath = "/org/team6/images/products/molnas.png";
                break;
            case "Lagan":
                imagePath = "/org/team6/images/products/lagan.png";
                break;
            default:
                throw new AssertionError();
        }
        return imagePath;
    }
}
package org.team6.model.Recommendations;

import org.team6.model.Products.ProductCategory;

public class RecommendationImagePath {
    public static String getTipImagePath(String recommendationTitle) {
        String imagePath = "";
        switch (recommendationTitle) {
            case "Close the refrigerator door":
                imagePath = "/org/team6/images/tips/refrigerator_closed.png";
                break;
            case "Avoid hot food in the refrigerator":
                imagePath = "/org/team6/images/tips/hot_food.png";
                break;
            case "Oven usage":
                imagePath = "/org/team6/images/tips/open_oven.png";
                break;
            case "Use lids":
                imagePath = "/org/team6/images/tips/pot_lid.png";
                break;
            case "Avoid letting the water run":
                imagePath = "/org/team6/images/tips/water_running.png";
                break;
            case "Reduce time in the shower":
                imagePath = "/org/team6/images/tips/shower.png";
                break;
            case "Lights usage":
                imagePath = "/org/team6/images/tips/lamp_turning_off.png";
                break;
            case "Use natural light":   
                imagePath = "/org/team6/images/tips/natural_light.png";
                break;     
            default:
                throw new AssertionError();
        }
        return imagePath;
    }

    public static String getProductImagePath(String recommendationTitle, ProductCategory productCategory) {
        String imagePath = "";
        switch (recommendationTitle) {
            case "Tynnerås":
                if (productCategory == ProductCategory.FRIDGE) {
                    imagePath = "/org/team6/images/products/tynneras_fridge.png";
                }
                else if (productCategory == ProductCategory.FREEZER) {
                    imagePath = "/org/team6/images/products/tynneras_freezer.png";
                }
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
            case "Forsnäs":
                if (productCategory == ProductCategory.FREEZER){
                    imagePath = "/org/team6/images/products/forsnas_freezer.png";
                }
                else if (productCategory == ProductCategory.FRIDGE) {
                    imagePath = "/org/team6/images/products/forsnas_fridge.png";
                }

                break;
            default:
                throw new AssertionError();
        }
        return imagePath;
    }
}
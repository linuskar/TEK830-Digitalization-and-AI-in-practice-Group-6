package org.team6.model.recommendations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.team6.database.DatabaseConnection;
import org.team6.model.EnergyUsageCategory;
import org.team6.model.products.ConventionalOven;
import org.team6.model.products.ForcedAirOven;
import org.team6.model.products.Freezer;
import org.team6.model.products.Fridge;
import org.team6.model.products.FridgeFreezer;
import org.team6.model.products.Oven;
import org.team6.model.products.Product;
import org.team6.model.products.ProductCategory;

public class RecommendationsBackend {
    // if personal recommendations are on, user data will be collected and used to generate recommendations
    private static boolean personalRecommendationsOn = true;
    private static final List<RecommendationObserver> observers = new ArrayList<>();

    private static List<Product> dataBaseProducts = new ArrayList<>();
    private static Map<EnergyUsageCategory, Integer> dataBaseEnergySpenders = new EnumMap<>(EnergyUsageCategory.class);

    private static final List<ProductRecommendation> generalProductRecommendations = new ArrayList<>();
    private static final List<ProductRecommendation> personalProductRecommendations  = new ArrayList<>();

    private static final List<TipRecommendation> generalTipsRecommendations = new ArrayList<>();
    private static final List<TipRecommendation> personalTipsRecommendations  = new ArrayList<>();

    private static final List<Tip> tips = new ArrayList<>();

    private RecommendationsBackend() {
    }

    public static void initialize(){
        dataBaseProducts = DatabaseConnection.getProducts();
        dataBaseEnergySpenders = DatabaseConnection.getEnergySpenders();

        createTips();

        createRecommendations();
    }

    private static void  createRecommendations(){
        createGeneralRecommendations();
        createPersonalRecommendations();
    }

    private static void createGeneralRecommendations() {
        recommendGeneralProducts();
        recommendGeneralTips();
    }

    private static void recommendGeneralProducts() {
        for (Product product : dataBaseProducts) {
            ProductRecommendation recommendation = new ProductRecommendation(product.getName(), product.getProductDescription(), product.getProductCategory());
            generalProductRecommendations.add(recommendation);
        }      
    }

    private static void recommendGeneralTips(){
        for (Tip tip : tips) {
            TipRecommendation recommendation = new TipRecommendation(tip.getTipTitle(), tip.getTipDescription());
            generalTipsRecommendations.add(recommendation);    
        }
    }

    private static void createTips(){    
        Tip refrigerationTip1 = new Tip("Close the refrigerator door", "Make sure the refrigerator is properly closed if you have to be away from the refrigerator for a moment. Leaving the door open lets the refrigerator work harder to bring the temperature down due to the cool air going out.", EnergyUsageCategory.REFRIGERATION);
        Tip refrigerationTip2 = new Tip("Avoid hot food in the refrigerator", "Having hot things in the refrigerator forces the refrigerator to work more to bring the temperature down. Therefore allow the food to cool down to room temperature before putting it in the refrigerator.", EnergyUsageCategory.REFRIGERATION);

        Tip cookingTip1 = new Tip("Oven usage", "With high temperatures and longer cooking times the oven can consume a high amount of energy. Therefore try to not preheat the oven for too long. Try to avoid opening the oven door too much, as you will let heat out each time, which means you will have to cook longer with the oven.", EnergyUsageCategory.COOKING);
        Tip cookingTip2 = new Tip("Use lids", "Cover pots and pans with lids to save energy. As while cooking this can trap the heat speeding the cooking process, reducing energy consumption.", EnergyUsageCategory.COOKING);

        Tip hotWaterTip1 = new Tip("Avoid letting the water run", "Using less water means less wasted water. Therefore, think about not wasting too much water to save on energy consumption.", EnergyUsageCategory.HOT_WATER);
        Tip hotWaterTip2 = new Tip("Reduce time in the shower", "Avoid spending too much unnecessary time in the shower to reduce energy consumption. For example, try turning the water off while using soap and shampoo.", EnergyUsageCategory.HOT_WATER);

        Tip lightingTip1 = new Tip("Lights usage", "Make sure to keep lights low or off when not used. As this is one of the better ways to reduce energy consumption.", EnergyUsageCategory.LIGHTING);
        Tip lightingTip2 = new Tip("Use natural light", "When possible, try to use more natural light to reduce the need of having the lights on, which will reduce energy consumption.", EnergyUsageCategory.LIGHTING);

        tips.add(refrigerationTip1);
        tips.add(refrigerationTip2);

        tips.add(cookingTip1);
        tips.add(cookingTip2);

        tips.add(hotWaterTip1);
        tips.add(hotWaterTip2);

        tips.add(lightingTip1);
        tips.add(lightingTip2);
    }

    private static void createPersonalRecommendations(){
        Map <EnergyUsageCategory, Integer> topEnergySpenders = getTopEnergySpenders();

        for (Map.Entry<EnergyUsageCategory, Integer> entry : topEnergySpenders.entrySet()) {
            EnergyUsageCategory category = entry.getKey();
            recommendPersonalProducts(category);
            recommendPersonalTips(category);
        }
    }

    private static void recommendPersonalTips(EnergyUsageCategory category){
        for (Tip tip : tips) {
            if (tip.getEnergyUsageCategory().equals(category)) {
                String description = personalTipRecommendationDescription(tip, category);

                TipRecommendation recommendation = new TipRecommendation(tip.getTipTitle(), description);
                personalTipsRecommendations.add(recommendation);
            }
        }
    }

    private static String personalTipRecommendationDescription(Tip tip, EnergyUsageCategory category){
        StringBuilder sb = new StringBuilder();
        String energyUsagePercentageForCategory = Math.round(getEnergyUsagePercantage(category)) + "%";
        String personalRecommendationDescription = personalTipRecommendationEnergySavingsDescription(category, energyUsagePercentageForCategory);

        sb.append(personalRecommendationDescription);
        sb.append("\n\n");
        sb.append(tip.getTipDescription());

        return sb.toString();
    }

    private static String personalTipRecommendationEnergySavingsDescription(EnergyUsageCategory category, String energyUsagePercentageForCategory){
        StringBuilder sb = new StringBuilder();
        String energyUsageDescription = personalRecommendationEnergyUsageDescription(category, energyUsagePercentageForCategory);

        sb.append(energyUsageDescription);
        sb.append(". We therefore recommend this tip to save energy.");

        return sb.toString();
    }

    private static EnumMap <EnergyUsageCategory, Integer> getTopEnergySpenders(){
        // Get top energy spenders based on the energy usage in dataBaseEnergySpenders summing up to 50% of the total energy usage
        // sort to get the top energy spenders
        // then go through the energy spenders till the usage sums up to or greater than 50% of the total energy usage

        EnumMap<EnergyUsageCategory, Integer> topEnergySpenders = new EnumMap<>(EnergyUsageCategory.class);

        List<Map.Entry<EnergyUsageCategory, Integer>> sortedEnergySpenders = new ArrayList<>(dataBaseEnergySpenders.entrySet());

        sortedEnergySpenders.sort((entry1, entry2) -> entry2.getValue().compareTo(entry1.getValue()));

        double totalEnergyUsage = getTotalEnergyUsage();
        double energyUsageSum = 0;
        double threshold = totalEnergyUsage/2;

        for (Map.Entry<EnergyUsageCategory, Integer> entry : sortedEnergySpenders) {
            if (energyUsageSum >= threshold) {
                break;
            }

            topEnergySpenders.put(entry.getKey(), entry.getValue());
            energyUsageSum += entry.getValue();
        }

        return topEnergySpenders;
    }

    private static void recommendPersonalProducts(EnergyUsageCategory category) {
        // Get products from the database
        Map<String, List<Product>> groupedProducts = new HashMap<>();
        for (Product product : dataBaseProducts) {
            if (product.getEnergyUsageCategory().equals(category.toString())) {
                if (!groupedProducts.containsKey(product.getProductCategoryString())) {
                    groupedProducts.put(product.getProductCategoryString(), new ArrayList<>());
                }

                groupedProducts.get(product.getProductCategoryString()).add(product);     
            }
        }

        groupedProducts.forEach((productCategory, productsInCategory) -> {
            if (productCategory.equals(ProductCategory.FRIDGE.toString())) {
                Fridge lowestConsumptionFridge = recommendFridge(productsInCategory);

                if (lowestConsumptionFridge != null) {
                    String description = personalProductRecommendationDescription(lowestConsumptionFridge, category);

                    personalProductRecommendations.add(new ProductRecommendation(lowestConsumptionFridge.getName(), description, ProductCategory.FRIDGE));
                }
            }
            else if (productCategory.equals(ProductCategory.FREEZER.toString())) {
                Freezer lowestConsumptionFreezer = recommendFreezer(productsInCategory);

                if (lowestConsumptionFreezer != null) {
                    String description = personalProductRecommendationDescription(lowestConsumptionFreezer, category);

                    personalProductRecommendations.add(new ProductRecommendation(lowestConsumptionFreezer.getName(), description, ProductCategory.FREEZER));
                }
            }
            else if (productCategory.equals(ProductCategory.FRIDGE_FREEZER.toString())) {
                FridgeFreezer lowestConsumptionFridgeFreezer = recommendFridgeFreezer(productsInCategory);

                if (lowestConsumptionFridgeFreezer != null) {
                    String description = personalProductRecommendationDescription(lowestConsumptionFridgeFreezer, category);

                    personalProductRecommendations.add(new ProductRecommendation(lowestConsumptionFridgeFreezer.getName(), description, ProductCategory.FRIDGE_FREEZER));
                }
            }
            else if (productCategory.equals(ProductCategory.OVEN.toString())) {
                Oven lowestConsumptionOven = recommendOven(productsInCategory);

                if (lowestConsumptionOven != null) {
                    String description = personalProductRecommendationDescription(lowestConsumptionOven, category);

                    personalProductRecommendations.add(new ProductRecommendation(lowestConsumptionOven.getName(), description, ProductCategory.OVEN));
                }
            }
            else{
                ForcedAirOven lowestConsumptionOven = recommendForcedAirOven(productsInCategory);
 
                if (lowestConsumptionOven != null) {
                    String description = personalProductRecommendationDescription(lowestConsumptionOven, category);

                    personalProductRecommendations.add(new ProductRecommendation(lowestConsumptionOven.getName(), description, ProductCategory.FORCED_AIR_OVEN));
                }
            }
        });    
    }

    
    private static Fridge recommendFridge(List<Product> products) {
        // Sort products in the category based on energy consumption
        return products.stream()
            .filter(Fridge.class::isInstance)
            .map(Fridge.class::cast)
            .min(Comparator.comparingDouble(Fridge::getEnergyConsumption))
            .orElse(null);
    }

    private static Freezer recommendFreezer(List<Product> products) {
        return products.stream()
            .filter(Freezer.class::isInstance)
            .map(Freezer.class::cast)
            .min(Comparator.comparingDouble(Freezer::getEnergyConsumption))
            .orElse(null);
    }

    private static FridgeFreezer recommendFridgeFreezer(List<Product> products) {
        return products.stream()
            .filter(FridgeFreezer.class::isInstance)
            .map(FridgeFreezer.class::cast)
            .min(Comparator.comparingDouble(FridgeFreezer::getEnergyConsumption))
            .orElse(null);
    }

    private static Oven recommendOven(List<Product> products) {
        return products.stream()
            .filter(ConventionalOven.class::isInstance)
            .map(ConventionalOven.class::cast)
            .min(Comparator.comparingDouble(ConventionalOven::getEnergyConsumptionConventional))
            .orElse(null);
    }

    private static ForcedAirOven recommendForcedAirOven(List<Product> products) {
        return products.stream()
            .filter(ForcedAirOven.class::isInstance)
            .map(ForcedAirOven.class::cast)
            .min(Comparator.comparingDouble(ForcedAirOven::getEnergyConsumptionFanForcedConvection)
            .thenComparingDouble(ForcedAirOven::getEnergyConsumptionConventional))
            .orElse(null);
    }

    private static String personalProductRecommendationDescription(Product product, EnergyUsageCategory category){
        StringBuilder sb = new StringBuilder();
        String energyUsagePercentageForCategory = Math.round(getEnergyUsagePercantage(category)) + "%";
        String personalRecommendationDescription = personalProductRecommendationEnergySavingsDescription(product, category, energyUsagePercentageForCategory);

        sb.append(personalRecommendationDescription);
        sb.append("\n\n");
        sb.append(product.getProductDescription());

        return sb.toString();
    }

    private static double getEnergyUsagePercantage(EnergyUsageCategory category){
        double totalEnergyUsage = getTotalEnergyUsage();
        double energyUsageForCategory = dataBaseEnergySpenders.get(category);

        double totalEnergyUsageProcent = 100*energyUsageForCategory/totalEnergyUsage;

        return totalEnergyUsageProcent;
    }

    private static double getTotalEnergyUsage() {
        double totalEnergyUsage = 0;

        for (Map.Entry<EnergyUsageCategory, Integer> entry : dataBaseEnergySpenders.entrySet()) {
            int energyUsage = entry.getValue();
            totalEnergyUsage += energyUsage;
        }

        return totalEnergyUsage;
    }

    private static String personalProductRecommendationEnergySavingsDescription(Product product, EnergyUsageCategory category, String energyUsagePercentageForCategory){
        StringBuilder sb = new StringBuilder();
        String energyUsageDescription = personalRecommendationEnergyUsageDescription(category, energyUsagePercentageForCategory);


        sb.append(energyUsageDescription);
        sb.append(". We therefore recommend a ");
        sb.append(product.getName());
        sb.append(" ");
        sb.append(product.getProductCategoryString().toLowerCase());
        sb.append(" to save energy.");

        return sb.toString();
    }

    private static String personalRecommendationEnergyUsageDescription(EnergyUsageCategory category, String energyUsagePercentageForCategory){
        StringBuilder sb = new StringBuilder();

        sb.append("You are spending ");
        sb.append(energyUsagePercentageForCategory);
        sb.append(" kWh on ");
        sb.append(category.toString().toLowerCase());

        return sb.toString();
    }

    public static List<Product> getDataBaseProducts() {
        return dataBaseProducts;
    }

    public static List<ProductRecommendation> getPersonalProductRecommendations() {
        return personalProductRecommendations;
    }

    public static List<ProductRecommendation> getGeneralProductRecommendations() {
        return generalProductRecommendations;
    }

    public static List<TipRecommendation> getGeneralTipsRecommendations() {
        return generalTipsRecommendations;
    }

    public static List<TipRecommendation> getPersonalTipsRecommendations() {
        return personalTipsRecommendations;
    }

    public static void addObserver(RecommendationObserver observer) {
        observers.add(observer);
    }

    public static void removeObserver(RecommendationObserver observer) {
        observers.remove(observer);
    }

    private static void notifyObservers() {
        for (RecommendationObserver observer : observers) {
            observer.update();
        }
    }

    public static boolean isPersonalRecommendationsOn() {
        return personalRecommendationsOn;
    }

    public static void setPersonalRecommendationsOn(boolean privateRecommendationsOn) {
        RecommendationsBackend.personalRecommendationsOn = privateRecommendationsOn;
        notifyObservers();
    }
}
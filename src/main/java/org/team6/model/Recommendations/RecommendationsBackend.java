package org.team6.model.Recommendations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.team6.database.DatabaseConnection;
import org.team6.model.EnergyUsageCategory;
import org.team6.model.Products.ForcedAirOven;
import org.team6.model.Products.Freezer;
import org.team6.model.Products.Fridge;
import org.team6.model.Products.FridgeFreezer;
import org.team6.model.Products.Oven;
import org.team6.model.Products.Product;
import org.team6.model.Products.ProductCategory;

public class RecommendationsBackend {
    // if personal recommendations are on, user data will be collected and used to generate recommendations
    private static boolean personalRecommendationsOn = true;
    private static final List<RecommendationObserver> observers = new ArrayList<>();

    private static List<Product> dataBaseProducts = new ArrayList<>();

    private static List<Recommendation> generalProductRecommendations = new ArrayList<>();
    private static List<Recommendation> personalProductRecommendations  = new ArrayList<>();

    private static HashMap<EnergyUsageCategory, Integer> dataBaseEnergySpenders = new HashMap<>();

    private RecommendationsBackend() {
    }

    public static void initialize(){
        dataBaseProducts = DatabaseConnection.getProducts();
        dataBaseEnergySpenders = DatabaseConnection.getEnergySpenders();

        // What amount of the total energy consumption for user is based on this category
        //dataBaseEnergySpenders.put(EnergyUsageCategory.REFRIGERATION, 325);
        //dataBaseEnergySpenders.put(EnergyUsageCategory.COOKING, 100);

        createRecommendations();
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

    private static void  createRecommendations(){
        createGeneralRecommendations();
        createPersonalRecommendations();
    }

    private static void createGeneralRecommendations() {
        for (EnergyUsageCategory category : EnergyUsageCategory.values()) {
            recommendGeneralProducts(category);
        }
    }

    private static void createPersonalRecommendations(){
        for (Map.Entry<EnergyUsageCategory, Integer> entry : dataBaseEnergySpenders.entrySet()) {
            EnergyUsageCategory category = entry.getKey();
            recommendPersonalProducts(category);
        }
    }

    public static List<Product> getDataBaseProducts() {
        return dataBaseProducts;
    }

    public static List<Recommendation> getPersonalProductRecommendations() {
        return personalProductRecommendations;
    }

    public static List<Recommendation> getGeneralProductRecommendations() {
        return generalProductRecommendations;
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
                    String description = personalRecommendationDescription(lowestConsumptionFridge, category);

                    personalProductRecommendations.add(new Recommendation(lowestConsumptionFridge.getName(), description, ProductCategory.FRIDGE));
                }
            }
            else if (productCategory.equals(ProductCategory.FREEZER.toString())) {
                Freezer lowestConsumptionFreezer = recommendFreezer(productsInCategory);

                if (lowestConsumptionFreezer != null) {
                    String description = personalRecommendationDescription(lowestConsumptionFreezer, category);

                    personalProductRecommendations.add(new Recommendation(lowestConsumptionFreezer.getName(), description, ProductCategory.FREEZER));
                }
            }
            else if (productCategory.equals(ProductCategory.FRIDGE_FREEZER.toString())) {
                FridgeFreezer lowestConsumptionFridgeFreezer = recommendFridgeFreezer(productsInCategory);

                if (lowestConsumptionFridgeFreezer != null) {
                    String description = personalRecommendationDescription(lowestConsumptionFridgeFreezer, category);

                    personalProductRecommendations.add(new Recommendation(lowestConsumptionFridgeFreezer.getName(), description, ProductCategory.FRIDGE_FREEZER));
                }
            }
            else if (productCategory.equals(ProductCategory.OVEN.toString())) {
                Oven lowestConsumptionOven = recommendOven(productsInCategory);

                if (lowestConsumptionOven != null) {
                    String description = personalRecommendationDescription(lowestConsumptionOven, category);

                    personalProductRecommendations.add(new Recommendation(lowestConsumptionOven.getName(), description, ProductCategory.OVEN));
                }
            }
            else{
                ForcedAirOven lowestConsumptionOven = recommendForcedAirOven(productsInCategory);
 
                if (lowestConsumptionOven != null) {
                    String description = personalRecommendationDescription(lowestConsumptionOven, category);

                    personalProductRecommendations.add(new Recommendation(lowestConsumptionOven.getName(), description, ProductCategory.FORCED_AIR_OVEN));
                }
            }
        });    
    }

    private static String personalRecommendationDescription(Product product, EnergyUsageCategory category){
        StringBuilder sb = new StringBuilder();
        String energyUsagePercentageForCategory = Math.round(getEnergyUsagePercantage(category)) + "%";
        String personalRecommendationDescription = personalRecommendationEnergySavingsDescription(product, category, energyUsagePercentageForCategory);

        sb.append(personalRecommendationDescription);
        sb.append("\n\n");
        sb.append(product.getProductDescription());

        return sb.toString();
    }

    private static String personalRecommendationEnergySavingsDescription(Product product, EnergyUsageCategory category, String energyUsagePercentageForCategory){
        StringBuilder sb = new StringBuilder();

        sb.append("You are spending ");
        sb.append(energyUsagePercentageForCategory);
        sb.append(" kWh on ");
        sb.append(category.toString().toLowerCase());
        sb.append(". We therefore recommend a ");
        sb.append(product.getName());
        sb.append(" ");
        sb.append(product.getProductCategoryString().toLowerCase());
        sb.append(" to save energy.");

        return sb.toString();
    }

    private static Fridge recommendFridge(List<Product> products) {
        // Sort products in the category based on energy consumption
        Fridge lowestConsumptionFridge = products.stream()
            .filter(product -> product instanceof Fridge)
            .map(product -> (Fridge) product)
            .sorted(Comparator.comparingDouble(Fridge::getEnergyConsumption))
            .findFirst()
            .orElse(null);

        return lowestConsumptionFridge;
    }

    private static Freezer recommendFreezer(List<Product> products) {
        Freezer lowestConsumptionFreezer = products.stream()
            .filter(product -> product instanceof Freezer)
            .map(product -> (Freezer) product)
            .sorted(Comparator.comparingDouble(Freezer::getEnergyConsumption))
            .findFirst()
            .orElse(null);

        return lowestConsumptionFreezer;
    }

    private static FridgeFreezer recommendFridgeFreezer(List<Product> products) {
        FridgeFreezer lowestConsumptionFridgeFreezer = products.stream()
            .filter(product -> product instanceof FridgeFreezer)
            .map(product -> (FridgeFreezer) product)
            .sorted(Comparator.comparingDouble(FridgeFreezer::getEnergyConsumption))
            .findFirst()
            .orElse(null);

        return lowestConsumptionFridgeFreezer;
    }

    private static ForcedAirOven recommendForcedAirOven(List<Product> products) {
        ForcedAirOven lowestConsumptionOven = products.stream()
            .filter(product -> product instanceof ForcedAirOven)
            .map(product -> (ForcedAirOven) product)
            .sorted(Comparator.comparingDouble(ForcedAirOven::getEnergyConsumptionFanForcedConvection)
                .thenComparingDouble(ForcedAirOven::getEnergyConsumptionConventional))
            .findFirst()
            .orElse(null);

        return lowestConsumptionOven;
    }

    private static Oven recommendOven(List<Product> products) {
        Oven lowestConsumptionOven = products.stream()
            .filter(product -> product instanceof Oven)
            .map(product -> (Oven) product)
            .sorted(Comparator.comparingDouble(Oven::getEnergyConsumptionConventional))
            .findFirst()
            .orElse(null);

        return lowestConsumptionOven;
    }

    private static void recommendGeneralProducts(EnergyUsageCategory category) {
        List<Product> relevantProducts = new ArrayList<>();

        for (Product product : dataBaseProducts) {
            if (product.getEnergyUsageCategory().equals(category.toString())) {
                relevantProducts.add(product);
            }
        }

        for (Product product : relevantProducts) {
            Recommendation recommendation = new Recommendation(product.getName(), product.getProductDescription(), product.getProductCategory());
            generalProductRecommendations.add(recommendation);
        }      
    }
}
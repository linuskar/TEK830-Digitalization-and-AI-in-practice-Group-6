package org.team6.model.Recommendations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.team6.database.DatabaseConnection;
import org.team6.model.EnergyUsageCategory;
import org.team6.model.Products.Fridge;
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
                if (!groupedProducts.containsKey(product.getProductCategory())) {
                    groupedProducts.put(product.getProductCategory(), new ArrayList<>());
                }

                groupedProducts.get(product.getProductCategory()).add(product);     
            }
        }

        groupedProducts.forEach((productCategory, productsInCategory) -> {
            if (productCategory.equals(ProductCategory.FRIDGE.toString())) {
                Fridge lowestConsumptionFridge = recommendFridge(productsInCategory);

                if (lowestConsumptionFridge != null) {
                    String description = personalRecommendationDescription(lowestConsumptionFridge, category);

                    personalProductRecommendations.add(new Recommendation(lowestConsumptionFridge.getName(), description));
                }
            }
            else{
                Oven lowestConsumptionOven = recommendOven(productsInCategory);
 
                if (lowestConsumptionOven != null) {
                    String description = personalRecommendationDescription(lowestConsumptionOven, category);

                    personalProductRecommendations.add(new Recommendation(lowestConsumptionOven.getName(), description));
                }
            }
        });    
    }

    private static String personalRecommendationDescription(Product product, EnergyUsageCategory category){
        StringBuilder sb = new StringBuilder();
        String energyUsagePercentageForCategory = Math.round(getEnergyUsagePercantage(category)) + "%";
        String personalRecommendationDescription = personalRecommendationEnergySavingsDescription(product, category, energyUsagePercentageForCategory);
        String productRecommendationDescription = productRecommendationDescription(product);

        sb.append(personalRecommendationDescription);
        sb.append("\n\n");
        sb.append(productRecommendationDescription);

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
        sb.append(product.getProductCategory().toLowerCase());
        sb.append(" to save energy.");

        return sb.toString();
    }

    private static String productRecommendationDescription(Product product){
        StringBuilder sb = new StringBuilder();

        if (product instanceof Fridge){
            Fridge fridge = (Fridge) product;
            sb.append("• This product costs ");
            sb.append(fridge.getPrice());
            sb.append(" kr.");
            sb.append("\n");
            sb.append("• Product category: ");
            sb.append(fridge.getProductCategory());
            sb.append("\n");
            sb.append("• Energy spender category: ");
            sb.append(fridge.getEnergyUsageCategory());
            sb.append("\n");
            sb.append("• This product consumes ");
            sb.append(fridge.getEnergyConsumption());
            sb.append(" kWh/annum.");
        }
        else{
            Oven oven = (Oven) product;
            sb.append("• This product costs ");
            sb.append(oven.getPrice());
            sb.append(" kr.");
            sb.append("\n");
            sb.append("• Product category: ");
            sb.append(oven.getProductCategory());
            sb.append("\n");
            sb.append("• Energy spender category: ");
            sb.append(oven.getEnergyUsageCategory());
            sb.append("\n");
            sb.append("• This product consumes ");
            sb.append(oven.getEnergyConsumptionConventional());
            sb.append(" kWh/cycle (conventional).");
            sb.append("\n");
            sb.append("• This product consumes ");
            sb.append(oven.getEnergyConsumptionFanForcedConvection());
            sb.append(" kWh/cycle (fan forced convection).");
        }

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

    private static Oven recommendOven(List<Product> products) {
        Oven lowestConsumptionOven = products.stream()
            .filter(product -> product instanceof Oven)
            .map(product -> (Oven) product)
            .sorted(Comparator.comparingDouble(Oven::getEnergyConsumptionFanForcedConvection)
                .thenComparingDouble(Oven::getEnergyConsumptionConventional))
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
            // Add product information based on product category as needed
            if (product.getProductCategory().equals(ProductCategory.FRIDGE.toString())) {
                Fridge fridge = (Fridge) product;

                String productRecommendationDescription = productRecommendationDescription(fridge);
                generalProductRecommendations.add(new Recommendation(product.getName(), productRecommendationDescription));
            }
            else{
                Oven oven = (Oven) product;

                String productRecommendationDescription = productRecommendationDescription(oven);
                generalProductRecommendations.add(new Recommendation(product.getName(), productRecommendationDescription));
            }
        }      
    }
}
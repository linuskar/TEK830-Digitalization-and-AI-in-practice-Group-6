package org.team6.model.Recommendations;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.team6.model.EnergyUsage;
import org.team6.model.EnergyUsageCategory;
import org.team6.model.Observer;
import org.team6.model.Products.Fridge;
import org.team6.model.Products.Oven;
import org.team6.model.Products.Product;
import org.team6.model.Products.ProductCategory;

public class RecommendationsBackend {
    // if personal recommendations are on, user data will be collected and used to generate recommendations
    private static boolean personalRecommendationsOn = true;
    private static final List<Observer> observers = new ArrayList<>();

    private static List<Product> dataBaseProducts = new ArrayList<>();
    private static List<EnergyUsage> dataBaseEnergyUsageData = new ArrayList<>();

    private static List<Recommendation> generalProductRecommendations = new ArrayList<>();
    private static List<Recommendation> personalProductRecommendations  = new ArrayList<>();

    private static HashMap<EnergyUsageCategory, Integer> energySpenders = new HashMap<>();

    private RecommendationsBackend() {
    }

    public static void initialize(){
        // TEMP: Products and energy usage data are hardcoded for now
        // TODO: Put products in database
        Fridge fridge1 = new Fridge("Tynnerås", ProductCategory.FRIDGE, EnergyUsageCategory.REFRIGERATION, 114, 7995);
        Fridge fridge2 = new Fridge("Mölnås", ProductCategory.FRIDGE, EnergyUsageCategory.REFRIGERATION, 164, 8995);
        Fridge fridge3 = new Fridge("Alingsås", ProductCategory.FRIDGE, EnergyUsageCategory.REFRIGERATION, 198, 7995);

        Oven oven1 = new Oven("Mutebo", ProductCategory.OVEN, EnergyUsageCategory.COOKING, 10995, 1.09, 0.52, 1, 70);
        Oven oven2 = new Oven("Forneby", ProductCategory.OVEN, EnergyUsageCategory.COOKING, 5495, 0.93,0.69, 1, 72);
        Oven oven3 = new Oven("Brändbo", ProductCategory.OVEN, EnergyUsageCategory.COOKING, 4995,0.93,0.69, 1, 72);
        // TODO: ovens with no fan forced convection
        Oven oven4 = new Oven("Lagan", ProductCategory.OVEN, EnergyUsageCategory.COOKING, 2495, 0.82,0,0,74);
        Oven oven5 = new Oven("Mattradition", ProductCategory.OVEN, EnergyUsageCategory.COOKING, 3995, 0.99,0.81, 1, 71);

        dataBaseProducts.add(fridge1);
        dataBaseProducts.add(fridge2);
        dataBaseProducts.add(fridge3);
        
        dataBaseProducts.add(oven1);
        dataBaseProducts.add(oven2);
        dataBaseProducts.add(oven3);
        dataBaseProducts.add(oven4);
        dataBaseProducts.add(oven5);

        // What amount of the total energy consumption for user is based on this category
        energySpenders.put(EnergyUsageCategory.REFRIGERATION, 325);
        energySpenders.put(EnergyUsageCategory.COOKING, 100);

        createRecommendations();
    }

    private static double getEnergyUsageProcentage(EnergyUsageCategory category){
        double totalEnergyUsage = getTotalEnergyUsage();
        double energyUsageForCategory = energySpenders.get(category);

        double totalEnergyUsageProcent = 100*energyUsageForCategory/totalEnergyUsage;

        return totalEnergyUsageProcent;
    }

    private static double getTotalEnergyUsage() {
        double totalEnergyUsage = 0;

        for (Map.Entry<EnergyUsageCategory, Integer> entry : energySpenders.entrySet()) {
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
        for (Map.Entry<EnergyUsageCategory, Integer> entry : energySpenders.entrySet()) {
            EnergyUsageCategory category = entry.getKey();
            recommendPersonalProducts(category);
        }
    }

    public static List<Product> getDataBaseProducts() {
        return dataBaseProducts;
    }

    public static List<EnergyUsage> getDataBaseEnergyUsageData() {
        return dataBaseEnergyUsageData;
    }

    public static List<Recommendation> getPersonalProductRecommendations() {
        return personalProductRecommendations;
    }

    public static List<Recommendation> getGeneralProductRecommendations() {
        return generalProductRecommendations;
    }

    public static void addObserver(Observer observer) {
        observers.add(observer);
    }

    public static void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    private static void notifyObservers() {
        for (Observer observer : observers) {
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

    // TODO 
    // Use database to get energy usage data
    // method to recommend products based on energy usage

    // thershold is the recommended maximum energy consumption for the category
    // in this case we base it on industry standards or guidelnies for energy consumption
    // base it on industry standards, regulations, or guidelines for energy consumption
    // this would also need to be thought of based on the country the user is based in

    // Thought: maybe user could also set their own threshold?

    // Thought: recommend for top 5 uses?

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
            StringBuilder sb = new StringBuilder();

            if (productCategory.equals(ProductCategory.FRIDGE.toString())) {
                Fridge lowestConsumptionFridge = recommendFridge(productsInCategory);

                if (lowestConsumptionFridge != null) {
                    String energyUsageProcentageForCategory = Math.round(getEnergyUsageProcentage(category)) + "%";
        
                    sb.append("You are spending ");
                    sb.append(energyUsageProcentageForCategory);
                    sb.append(" kWh on ");
                    sb.append(category.toString().toLowerCase());
                    sb.append(". We therefore recommend a ");
                    sb.append(lowestConsumptionFridge.getName());
                    sb.append(" ");
                    sb.append(lowestConsumptionFridge.getProductCategory().toLowerCase());
                    sb.append(" to save energy.");
                    sb.append("\n\n");
                    sb.append("• This product costs ");
                    sb.append(lowestConsumptionFridge.getPrice());
                    sb.append(" kr.");
                    sb.append("\n");
                    sb.append("• This product consumes ");
                    sb.append(lowestConsumptionFridge.getEnergyConsumption());
                    sb.append(" kWh/annum.");
        
                    String productRecommendationDescription = sb.toString();
                    personalProductRecommendations.add(new Recommendation(lowestConsumptionFridge.getName(), productRecommendationDescription));
                }
            }
            else{
                Oven lowestConsumptionOven = recommendOven(productsInCategory);
 
                if (lowestConsumptionOven != null) {
                    String energyUsageProcentageForCategory = Math.round(getEnergyUsageProcentage(category)) + "%";

                    sb.append("You are spending ");
                    sb.append(energyUsageProcentageForCategory);
                    sb.append(" kWh on ");
                    sb.append(category.toString().toLowerCase());
                    sb.append(". We therefore recommend a ");
                    sb.append(lowestConsumptionOven.getName());
                    sb.append(" ");
                    sb.append(lowestConsumptionOven.getProductCategory().toLowerCase());
                    sb.append(" to save energy.");
                    sb.append("\n\n");
                    sb.append("• This product costs ");
                    sb.append(lowestConsumptionOven.getPrice());
                    sb.append(" kr.");
                    sb.append("\n");
                    sb.append("• This product consumes ");
                    sb.append(lowestConsumptionOven.getEnergyConsumptionConventional());
                    sb.append(" kWh/cycle (conventional).");
                    sb.append("\n");
                    sb.append("• This product consumes ");
                    sb.append(lowestConsumptionOven.getEnergyConsumptionFanForcedConvection());
                    sb.append(" kWh/cycle (fan forced convection).");

                    String productRecommendationDescription = sb.toString();
                    personalProductRecommendations.add(new Recommendation(lowestConsumptionOven.getName(), productRecommendationDescription));
                }
            }
        });    
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
            StringBuilder sb = new StringBuilder();

            // Add product information based on product category as needed
            if (product.getProductCategory().equals(ProductCategory.FRIDGE.toString())) {
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

                String productRecommendationDescription = sb.toString();
                generalProductRecommendations.add(new Recommendation(product.getName(), productRecommendationDescription));
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

                String productRecommendationDescription = sb.toString();
                generalProductRecommendations.add(new Recommendation(product.getName(), productRecommendationDescription));
            }
        }      
    }
}
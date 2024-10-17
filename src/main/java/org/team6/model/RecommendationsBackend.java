package org.team6.model;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Product fridge1 = new Product("Tynnerås", ProductCategory.FRIDGE, EnergyUsageCategory.REFRIGERATION, 114, 7995);
        Product fridge2 = new Product("Mölnås", ProductCategory.FRIDGE, EnergyUsageCategory.REFRIGERATION, 164, 8995);
        Product fridge3 = new Product("Alingsås", ProductCategory.FRIDGE, EnergyUsageCategory.REFRIGERATION, 198, 7995);

        Product oven1 = new Product("Mutebo", ProductCategory.OVEN, EnergyUsageCategory.COOKING, 200, 10995);
        Product oven2 = new Product("Forneby", ProductCategory.OVEN, EnergyUsageCategory.COOKING, 200, 5495);
        Product oven3 = new Product("Brändbo", ProductCategory.OVEN, EnergyUsageCategory.COOKING, 200, 4995);
        Product oven4 = new Product("Lagan", ProductCategory.OVEN, EnergyUsageCategory.COOKING, 200, 2495);
        Product oven5 = new Product("Mattradition", ProductCategory.OVEN, EnergyUsageCategory.COOKING, 200, 3995);

        dataBaseProducts.add(fridge1);
        dataBaseProducts.add(fridge2);
        dataBaseProducts.add(fridge3);
        
        dataBaseProducts.add(oven1);
        dataBaseProducts.add(oven2);
        dataBaseProducts.add(oven3);
        dataBaseProducts.add(oven4);
        dataBaseProducts.add(oven5);

        // What amount of the total energy consumption for user is based on this category
        energySpenders.put(EnergyUsageCategory.REFRIGERATION, 300);

        createRecommendations();
    }

    private static void  createRecommendations(){
        createGeneralRecommendations();
        createPersonalRecommendations();
    }

    private static void createGeneralRecommendations() {
        // TODO
        for (EnergyUsageCategory category : EnergyUsageCategory.values()) {
            recommendGeneralProducts(category);
        }
    }

    private static void createPersonalRecommendations(){
        // TODO
        for (Map.Entry<EnergyUsageCategory, Integer> entry : energySpenders.entrySet()) {
            EnergyUsageCategory category = entry.getKey();
            System.out.println(category);
            int energyUsage = entry.getValue();
            recommendPersonalProducts(category, energyUsage);
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

    private static void recommendPersonalProducts(EnergyUsageCategory category, int energyUsage) {
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
            // Sort products in the category based on energy consumption
            Product lowestConsumptionProduct = productsInCategory.stream()
                    .sorted(Comparator.comparingDouble(Product::getProductConsumption))
                    .findFirst()
                    .orElse(null);

            if (lowestConsumptionProduct != null) {
                StringBuilder sb = new StringBuilder();

                sb.append("You are spending ");
                sb.append(energyUsage);
                sb.append(" kWh on ");
                sb.append(category.toString().toLowerCase());
                sb.append(" . We therefore recommend a ");
                sb.append(lowestConsumptionProduct.getName());
                sb.append(" ");
                sb.append(lowestConsumptionProduct.getEnergyUsageCategory().toLowerCase());
                sb.append(" to save energy.");
                sb.append("\n\n");
                sb.append("• This product costs ");
                sb.append(lowestConsumptionProduct.getPrice());
                sb.append(" kr.");
                sb.append("\n");
                sb.append("• This product consumes ");
                sb.append(lowestConsumptionProduct.getProductConsumption());
                sb.append(" kWh/annum.");

                String productRecommendationDescription = sb.toString();
                personalProductRecommendations.add(new Recommendation(lowestConsumptionProduct.getName(), productRecommendationDescription));
            }
        });    
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
                sb.append("• This product costs ");
                sb.append(product.getPrice());
                sb.append(" kr.");
                sb.append("\n");
                sb.append("• Product category: ");
                sb.append(product.getProductCategory());
                sb.append("\n");
                sb.append("• Energy spender category: ");
                sb.append(product.getEnergyUsageCategory());
                sb.append("\n");
                sb.append("• This product consumes ");
                sb.append(product.getProductConsumption());
                sb.append(" kWh/annum.");
                String productRecommendationDescription = sb.toString();
                generalProductRecommendations.add(new Recommendation(product.getName(), productRecommendationDescription));
            }
            else{
                sb.append("• This product costs ");
                sb.append(product.getPrice());
                sb.append(" kr.");
                sb.append("\n");
                sb.append("• Product category: ");
                sb.append(product.getProductCategory());
                sb.append("\n");
                sb.append("• Energy spender category: ");
                sb.append(product.getEnergyUsageCategory());
                sb.append("\n");
                sb.append("• This product consumes ");
                sb.append(product.getProductConsumption());
                sb.append(" kWh");
                String productRecommendationDescription = sb.toString();
                generalProductRecommendations.add(new Recommendation(product.getName(), productRecommendationDescription));
            }
        }      
    }
}
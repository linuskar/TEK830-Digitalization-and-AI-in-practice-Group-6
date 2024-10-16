package org.team6.model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RecommendationsBackend {
    // if personal recommendations are on, user data will be collected and used to generate recommendations
    private static boolean personalRecommendationsOn = true;
    private static final List<Observer> observers = new ArrayList<>();

    private RecommendationsBackend() {
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

    public List<Product> recommendProductsBasedOnEnergyUsage(List<EnergyUsage> energyUsageData, List<Product> products, List<Product> currentProducts) {
        List<Product> recommendations = new ArrayList<>();

        // Analyze energy usage data
        for (EnergyUsage usage : energyUsageData) {
            if (usage.getConsumption() > usage.getThreshold()) {
                // Find relevant products
                List<Product> relevantProducts = products.stream()
                        .filter(product -> product.getCategory().equals(usage.getCategory()) && product.isEnergyEfficient())
                        .collect(Collectors.toList());

                // Compare with current products
                for (Product currentProduct : currentProducts) {
                    if (currentProduct.getCategory().equals(usage.getCategory())) {
                        relevantProducts = relevantProducts.stream()
                                .filter(product -> product.getEnergySavings(currentProduct) > 0)
                                .collect(Collectors.toList());
                    }
                }

                // Sort products by potential energy savings (assuming higher savings are better)
                relevantProducts.sort((p1, p2) -> Double.compare(p2.getEnergySavings(), p1.getEnergySavings()));

                // Add top recommendations to the list
                for (Product product : relevantProducts) {
                    if (recommendations.stream().noneMatch(p -> p.getName().equals(product.getName()))) {
                        recommendations.add(product);
                    }
                }
            }
        }

        return recommendations;
    }
}
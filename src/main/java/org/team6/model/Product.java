package org.team6.model;

public class Product {
    // TODO: get product image based on id from database
    // not saving image in database due to size
    private String name;
    private EnergyUsageCategory energySpendingcategory;
    private double productConsumption; // Energy consumption of this product in kWh

    public Product(String name, EnergyUsageCategory energySpendingcategory, double productConsumption) {
        this.name = name;
        this.energySpendingcategory = energySpendingcategory;
        this.productConsumption = productConsumption;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return energySpendingcategory.toString();
    }

    public double getProductConsumption() {
        return productConsumption;
    }

    public double getEnergySavings(Product currentProduct) {
        return currentProduct.getProductConsumption() - this.productConsumption;
    }
}

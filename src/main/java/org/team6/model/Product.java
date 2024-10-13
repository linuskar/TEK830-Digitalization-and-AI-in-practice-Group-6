package org.team6.model;

public class Product {
    // TODO: get product image based on id from database
    // not saving image in database due to size
    private String name;
    private String category;
    private boolean energyEfficient;
    private double standardConsumption; // Energy consumption of a standard product in kWh
    private double productConsumption; // Energy consumption of this product in kWh

    public Product(String name, String category, boolean energyEfficient, double standardConsumption, double productConsumption) {
        this.name = name;
        this.category = category;
        this.energyEfficient = energyEfficient;
        this.standardConsumption = standardConsumption;
        this.productConsumption = productConsumption;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public boolean isEnergyEfficient() {
        return energyEfficient;
    }

    public double getStandardConsumption() {
        return standardConsumption;
    }

    public double getProductConsumption() {
        return productConsumption;
    }

    public double getEnergySavings() {
        return standardConsumption - productConsumption;
    }

    public double getEnergySavings(Product currentProduct) {
        return currentProduct.getProductConsumption() - this.productConsumption;
    }
}

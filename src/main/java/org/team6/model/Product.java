package org.team6.model;

public class Product {
    // TODO: get product image based on id from database
    // not saving image in database due to size
    private String name;
    private EnergyUsageCategory energySpendingcategory;
    private ProductCategory productCategory;
    private double productConsumption; // Energy consumption of this product in kWh

    public Product(String name, ProductCategory productCategory, EnergyUsageCategory energySpendingcategory, double productConsumption) {
        this.name = name;
        this.energySpendingcategory = energySpendingcategory;
        this.productConsumption = productConsumption;
        this.productCategory = productCategory;
    }

    public String getName() {
        return name;
    }

    public String getProductCategory() {
        return productCategory.toString();
    }

    public String getEnergyUsageCategory() {
        return energySpendingcategory.toString();
    }

    public double getProductConsumption() {
        return productConsumption;
    }

    public double getEnergySavings(Product currentProduct) {
        return currentProduct.getProductConsumption() - this.productConsumption;
    }
}

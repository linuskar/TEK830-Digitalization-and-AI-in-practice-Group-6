package org.team6.model;

public class Product {
    // TODO: get product image based on id from database
    // not saving image in database due to size
    private String name;
    private EnergyUsageCategory energySpendingCategory;
    private ProductCategory productCategory;
    private double productConsumption; // Energy consumption of this product in kWh
    private int price; // price in kr

    public Product(String name, ProductCategory productCategory, EnergyUsageCategory energySpendingcategory, double productConsumption, int price) {
        this.name = name;
        this.energySpendingCategory = energySpendingcategory;
        this.productConsumption = productConsumption;
        this.productCategory = productCategory;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getProductCategory() {
        return productCategory.toString();
    }

    public String getEnergyUsageCategory() {
        return energySpendingCategory.toString();
    }

    public double getProductConsumption() {
        return productConsumption;
    }

    public int getPrice() {
        return price;
    }
}

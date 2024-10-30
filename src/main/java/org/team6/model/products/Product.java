package org.team6.model.products;

import org.team6.model.EnergyUsageCategory;

public abstract class Product implements IProduct  {
    private final String name;
    private final EnergyUsageCategory energySpendingCategory;
    private final ProductCategory productCategory;
    private final int price; // price in kr

    public Product(String name, ProductCategory productCategory, EnergyUsageCategory energySpendingcategory, int price) {
        this.name = name;
        this.energySpendingCategory = energySpendingcategory;
        this.productCategory = productCategory;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public ProductCategory getProductCategory() {
        return productCategory;
    }

    public String getProductCategoryString() {
        return productCategory.toString();
    }

    public String getEnergyUsageCategory() {
        return energySpendingCategory.toString();
    }

    public int getPrice() {
        return price;
    }
}

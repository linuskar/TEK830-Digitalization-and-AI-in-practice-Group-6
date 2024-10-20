package org.team6.model.Products;

import org.team6.model.EnergyUsageCategory;

public abstract class Product implements IProduct  {
    // TODO: get product image based on id from database
    // not saving image in database due to size
    private String name;
    private EnergyUsageCategory energySpendingCategory;
    private ProductCategory productCategory;
    private int price; // price in kr

    public Product(String name, ProductCategory productCategory, EnergyUsageCategory energySpendingcategory, int price) {
        this.name = name;
        this.energySpendingCategory = energySpendingcategory;
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

    public int getPrice() {
        return price;
    }
}

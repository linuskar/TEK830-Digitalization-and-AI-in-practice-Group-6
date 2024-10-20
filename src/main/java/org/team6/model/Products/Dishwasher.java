package org.team6.model.Products;

import org.team6.model.EnergyUsageCategory;

public class Dishwasher extends Product {
    private double energyConsumption; // Energy consumption of this product in kWh/annum for dishwasher

    public Dishwasher(String name, ProductCategory productCategory, EnergyUsageCategory energySpendingcategory, double energyConsumption, int price) {
        super(name, productCategory, energySpendingcategory, price);
        this.energyConsumption = energyConsumption;
    }    

    public double getEnergyConsumption() {
        return energyConsumption;
    }

    @Override
    public String getProductDescription() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
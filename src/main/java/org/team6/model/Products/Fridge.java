package org.team6.model.Products;

import org.team6.model.EnergyUsageCategory;

public class Fridge extends Product{
    private double energyConsumption; // Energy consumption of this product in kWh/annum for fridge

    public Fridge(String name, ProductCategory productCategory, EnergyUsageCategory energySpendingcategory, double energyConsumption, int price) {
        super(name, productCategory, energySpendingcategory, price);
        this.energyConsumption = energyConsumption;
    }    

    public double getEnergyConsumption() {
        return energyConsumption;
    }
}

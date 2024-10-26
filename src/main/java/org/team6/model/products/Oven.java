package org.team6.model.products;

import org.team6.model.EnergyUsageCategory;

public abstract class Oven extends Product {
    // energy consumption per cycle for each cavity (conventional) in kWh/cycle
    private final double energyConsumptionConventional;
    // volume of each cavity in liters
    private final double volumeOfEachCavity; 

    public Oven(String name, ProductCategory productCategory, EnergyUsageCategory energySpendingcategory, int price, double energyConsumptionConventional, double volumeOfEachCavity) {
        super(name, productCategory, energySpendingcategory, price);
        this.energyConsumptionConventional = energyConsumptionConventional;
        this.volumeOfEachCavity = volumeOfEachCavity;
    }

    public double getEnergyConsumptionConventional() {
        return energyConsumptionConventional;
    }

    public double getVolumeOfEachCavity() {
        return volumeOfEachCavity;
    }

    @Override
    public String getProductDescription() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
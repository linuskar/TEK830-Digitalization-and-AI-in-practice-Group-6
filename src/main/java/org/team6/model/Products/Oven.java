package org.team6.model.Products;

import org.team6.model.EnergyUsageCategory;

public class Oven extends Product{
    // energy consumption per cycle for each cavity (conventional) in kWh/cycle
    private double energyConsumptionConventional;
    // energy consumption per cycle for each cavity (Fan forced convection) in kWh/cycle
    private double energyConsumptionFanForcedConvection;     
    private int numberOfCavities; 
    // volume of each cavity in liters
    private double volumeOfEachCavity; 

    public Oven(String name, ProductCategory productCategory, EnergyUsageCategory energySpendingcategory, int price, double energyConsumptionConventional, double energyConsumptionFanForcedConvection, int numberOfCavities, double volumeOfEachCavity) {
        super(name, productCategory, energySpendingcategory, price);
        this.energyConsumptionConventional = energyConsumptionConventional;
        this.energyConsumptionFanForcedConvection = energyConsumptionFanForcedConvection;
        this.numberOfCavities = numberOfCavities;
        this.volumeOfEachCavity = volumeOfEachCavity;

    }
    
    public double getEnergyConsumptionConventional() {
        return energyConsumptionConventional;
    }

    public double getEnergyConsumptionFanForcedConvection() {
        return energyConsumptionFanForcedConvection;
    }

    public int getNumberOfCavities() {
        return numberOfCavities;
    }

    public double getVolumeOfEachCavity() {
        return volumeOfEachCavity;
    }
}
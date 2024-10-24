package org.team6.model.products;

import org.team6.model.EnergyUsageCategory;

public abstract class Oven extends Product {
    // energy consumption per cycle for each cavity (conventional) in kWh/cycle
    private double energyConsumptionConventional;
    // volume of each cavity in liters
    private double volumeOfEachCavity; 

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
/*
    @Override
    public String getProductDescription() {
        StringBuilder sb = new StringBuilder();

        sb.append("• This product costs ");
        sb.append(this.getPrice());
        sb.append(" kr.");
        sb.append("\n");
        sb.append("• Product category: ");
        sb.append(this.getProductCategory());
        sb.append("\n");
        sb.append("• Energy spender category: ");
        sb.append(this.getEnergyUsageCategory());
        sb.append("\n");
        sb.append("• This product consumes ");
        sb.append(this.getEnergyConsumptionConventional());
        sb.append(" kWh/cycle (conventional).");
        sb.append("\n");
        sb.append("• This product has a volume of ");
        sb.append(this.getVolumeOfEachCavity());
        sb.append(" liters.");

        return sb.toString();
    }
 */
    @Override
    public String getProductDescription() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
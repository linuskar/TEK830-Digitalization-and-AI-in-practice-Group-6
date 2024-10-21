package org.team6.model.Products;

import org.team6.model.EnergyUsageCategory;

public class ForcedAirOven extends Oven {
    // energy consumption per cycle for each cavity (Fan forced convection) in kWh/cycle
    private double energyConsumptionFanForcedConvection;     
    private int numberOfCavities; 
    
    public ForcedAirOven(String name, ProductCategory productCategory, EnergyUsageCategory energySpendingcategory, int price, double energyConsumptionConventional, double energyConsumptionFanForcedConvection, int numberOfCavities, double volumeOfEachCavity) {
        super(name, productCategory, energySpendingcategory, price, energyConsumptionConventional, volumeOfEachCavity);
        this.energyConsumptionFanForcedConvection = energyConsumptionFanForcedConvection;
        this.numberOfCavities = numberOfCavities;
    }

    public double getEnergyConsumptionFanForcedConvection() {
        return energyConsumptionFanForcedConvection;
    }

    public int getNumberOfCavities() {
        return numberOfCavities;
    }

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
        sb.append("• This product consumes ");
        sb.append(this.getEnergyConsumptionFanForcedConvection());
        sb.append(" kWh/cycle (fan forced convection).");
        sb.append("\n");
        sb.append("• This product has ");
        sb.append(this.getNumberOfCavities());
        sb.append(" cavities.");
        sb.append("\n");
        sb.append("• Each cavity has a volume of ");
        sb.append(this.getVolumeOfEachCavity());
        sb.append(" liters.");

        return sb.toString();
    }
}

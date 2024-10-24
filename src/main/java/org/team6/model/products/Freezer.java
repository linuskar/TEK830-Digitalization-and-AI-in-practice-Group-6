package org.team6.model.products;

import org.team6.model.EnergyUsageCategory;

public class Freezer extends Product{
    private double energyConsumption; // Energy consumption of this product in kWh/annum for freezer
    private int freezerVolume; // volume of the freezer in liters

    public Freezer(String name, ProductCategory productCategory, EnergyUsageCategory energySpendingcategory, double energyConsumption, int price, int freezerVolume) {
        super(name, productCategory, energySpendingcategory, price);
        this.energyConsumption = energyConsumption;
        this.freezerVolume = freezerVolume;
    }    

    public double getEnergyConsumption() {
        return energyConsumption;
    }

    public int getFreezerVolume() {
        return freezerVolume;
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
        sb.append(this.getEnergyConsumption());
        sb.append(" kWh/annum.");
        sb.append("\n");
        sb.append("• This product has a frozen compartments volume of ");
        sb.append(this.getFreezerVolume());
        sb.append(" liters.");
        
        return sb.toString();
    }
}

package org.team6.model.products;

import org.team6.model.EnergyUsageCategory;

public class Fridge extends Product {
    private final double energyConsumption; // Energy consumption of this product in kWh/annum for fridge
    private final int fridgeVolume; // volume of the fridge in liters

    public Fridge(String name, ProductCategory productCategory, EnergyUsageCategory energySpendingcategory, double energyConsumption, int price, int fridgeVolume) {
        super(name, productCategory, energySpendingcategory, price);
        this.energyConsumption = energyConsumption;
        this.fridgeVolume = fridgeVolume;
    }    

    public double getEnergyConsumption() {
        return energyConsumption;
    }

    public int getFridgeVolume() {
        return fridgeVolume;
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
        sb.append("• This product has a chill compartments volume of ");
        sb.append(this.getFridgeVolume());
        sb.append(" liters.");

        return sb.toString();
    }
}

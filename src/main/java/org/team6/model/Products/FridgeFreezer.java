package org.team6.model.Products;

import org.team6.model.EnergyUsageCategory;

public class FridgeFreezer extends Product {
    private int freezerVolume; // volume of the freezer in liters
    private int fridgeVolume; // volume of the fridge in liters
    private double energyConsumption; // Energy consumption of this product in kWh/annum for fridge freezer

    

    public FridgeFreezer(String name, ProductCategory productCategory, EnergyUsageCategory energySpendingcategory, double energyConsumption, int price, int fridgeVolume, int freezerVolume) {
        super(name, productCategory, energySpendingcategory, price);
        this.energyConsumption = energyConsumption;
        this.fridgeVolume = fridgeVolume;
        this.freezerVolume = freezerVolume;
    }    

    public int getFreezerVolume() {
        return freezerVolume;
    }

    public int getFridgeVolume() {
        return fridgeVolume;
    }

    public double getEnergyConsumption() {
        return energyConsumption;
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
        sb.append("\n");
        sb.append("• This product has a frozen compartments volume of ");
        sb.append(this.getFreezerVolume());
        sb.append(" liters.");
        
        return sb.toString();
    }
}
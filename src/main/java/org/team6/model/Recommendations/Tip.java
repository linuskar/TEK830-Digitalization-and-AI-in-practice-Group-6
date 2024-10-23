package org.team6.model.Recommendations;

import org.team6.model.EnergyUsageCategory;

public class Tip {
    private String tipTitle;
    private String tipDescription;
    private EnergyUsageCategory energyUsageCategory;

    public Tip(String title, String text, EnergyUsageCategory energyUsageCategory) {
        this.tipTitle = title;
        this.tipDescription = text;
        this.energyUsageCategory = energyUsageCategory;
    }

    public String getTipTitle() {
        return tipTitle;
    }

    public String getTipDescription() {
        StringBuilder sb = new StringBuilder();

        sb.append(tipDescription);
        sb.append("\n\n");
        sb.append("This tip is for ");
        sb.append(getEnergyUsageCategory().toString().toLowerCase());
        sb.append(".");

        return sb.toString();
    }

    public EnergyUsageCategory getEnergyUsageCategory() {
        return energyUsageCategory;
    }
}

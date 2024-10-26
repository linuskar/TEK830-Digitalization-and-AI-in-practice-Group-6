package org.team6.model.recommendations;

import org.team6.model.EnergyUsageCategory;

class Tip {
    private final String tipTitle;
    private final String tipDescription;
    private final EnergyUsageCategory energyUsageCategory;

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

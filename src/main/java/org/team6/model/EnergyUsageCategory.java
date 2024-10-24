package org.team6.model;

// Some guesses for what the energy spending categories
// might be for IKEAS Energy Insight in the IKEA Home Smart App.
public enum EnergyUsageCategory {
    HEATING("Heating"),
    COOKING("Cooking"),
    HOT_WATER("Hot Water"),
    COOLING("Cooling"),
    HOME_ELECTRONICS("Home Electronics"),
    REFRIGERATION("Refrigeration"),
    LIGHTING("Lighting"),
    ALWAYS_ON("Always On"),
    LAUNDRY("Laundry"),
    OTHER("Other");

    private final String categoryLabel;

    EnergyUsageCategory(String categoryLabel) {
        this.categoryLabel = categoryLabel;
    }

    @Override
    public String toString() {
        return categoryLabel;
    }
}

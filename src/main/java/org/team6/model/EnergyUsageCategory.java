package org.team6.model;

public enum EnergyUsageCategory {
    LIGHTING,
    HEATING,
    COOLING,
    REFRIGERATION,
    OTHER;

    @Override
    public String toString() {
        switch (this) {
            case LIGHTING:
                return "Lighting";
            case HEATING:
                return "Heating";
            case COOLING:
                return "Cooling";
            case REFRIGERATION:
                return "Refrigeration";
            case OTHER:
                return "Other";
            default:
                return super.toString();
        }
    }
}

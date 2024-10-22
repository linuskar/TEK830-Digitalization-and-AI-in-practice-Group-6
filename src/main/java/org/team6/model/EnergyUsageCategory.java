package org.team6.model;

// Some guesses for what the energy spending categories
// might be for IKEAS Energy Insight in the IKEA Home Smart App.
public enum EnergyUsageCategory {
    HEATING,
    COOKING,
    HOT_WATER,
    COOLING,
    HOME_ELECTRONICS,
    REFRIGERATION,
    LIGHTING,
    ALWAYS_ON,
    LAUNDRY,
    OTHER;

    @Override
    public String toString() {
        switch (this) {
            case HEATING:
                return "Heating";
            case COOKING:
                return "Cooking";
            case HOT_WATER:
                return "Hot Water";
            case COOLING:
                return "Cooling";
            case HOME_ELECTRONICS:
                return "Home Electronics";
            case REFRIGERATION:
                return "Refrigeration";
            case LIGHTING:
                return "Lighting";
            case ALWAYS_ON:
                return "Always On";
            case LAUNDRY:
                return "Laundry";
            case OTHER:
                return "Other";
            default:
                return super.toString();
        }
    }
}

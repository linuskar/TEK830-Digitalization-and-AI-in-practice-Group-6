package org.team6.model;

public class EnergyUsage {
    // Helper classes for the example
    public enum Category {
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

    private Category category;

    private double consumption;
    //private static final List<String> refridgerationThreshold = 1.5;

    // thershold is the recommended maximum energy consumption for the category
    // in this case we base it on industry standards or guidelnies for energy consumption
    // base it on industry standards, regulations, or guidelines for energy consumption

    // Thought: maybe user could also set their own threshold?
    private double threshold;
    private String timeFrame; // "week", "month", or "year"

    public EnergyUsage(double consumption) {
        this.consumption = consumption;
    }

    public double getConsumption() {
        return consumption;
    }

    public double getThreshold() {
        return threshold;
    }

    public String getTimeFrame() {
        return timeFrame;
    }
}

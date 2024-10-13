package org.team6.model;

public class EnergyUsage {
    // Helper classes for the example
    private String category;
    private double consumption;
    // thershold is the recommended maximum energy consumption for the category
    // in this case we base it on industry standards or guidelnies for energy consumption
    // base it on industry standards, regulations, or guidelines for energy consumption

    // Thought: maybe user could also set their own threshold?
    private double threshold;
    private String timeFrame; // "week", "month", or "year"

    public EnergyUsage(String category, double consumption, double threshold, String timeFrame) {
        this.category = category;
        this.consumption = consumption;
        this.threshold = threshold;
        this.timeFrame = timeFrame;
    }

    public String getCategory() {
        return category;
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

package org.team6.model;

public enum Notification {
    LOW_ELECTRICITY_PRICE("Electricity price under predefined value"),
    HIGH_ELECTRICITY_PRICE("Electricity price over predefined value"),
    SUNNY_WEATHER("Sunny weather, feel guilty free to run that load of laundry"),
    COLD_WEATHER("Cold weather, make sure to close all doors and windows"),
    DAILY_REPORT("For the next 24 hours electricity price will be above predefined value during these times");

    private final String text;

    Notification(String label) {
        this.text = label;
    }

    public static String getText(Notification notification) {
        return notification.text;
    }
}

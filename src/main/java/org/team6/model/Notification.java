package org.team6.model;

public enum Notification {
    ONE("Electricity price under predefined value"),
    TWO("Electricity price over predefined value"),
    THREE("Sunny weather, feel guilty free to run that load of laundry"),
    FOUR("Cold weather, make sure to close all doors and windows");

    private final String text;

    Notification(String label) {
        this.text = label;
    }

    public static String getText(Notification notification) {
        return notification.text;
    }
}

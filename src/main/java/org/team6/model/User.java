package org.team6.model;

import java.util.EnumMap;
import java.util.Map;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private double volume = 0.5;
    private boolean notificationsOn = true;
    private final Map<Notification, Boolean> notifications = new EnumMap<>(Notification.class);

    // TODO: make notifications send based on these values?
    private int dailyReportTime = 10;
    private double electricityPriceLimit = 60;
    private int startNotificationTime = 8;
    private int endNotificationTime = 20;

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

        notifications.put(Notification.LOW_ELECTRICITY_PRICE, true);
        notifications.put(Notification.HIGH_ELECTRICITY_PRICE, true);
        notifications.put(Notification.SUNNY_WEATHER, true);
        notifications.put(Notification.COLD_WEATHER, true);
        notifications.put(Notification.DAILY_REPORT, true);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public double getVolume() {
        return volume;
    }

    /** Set volume for the user that has been set previously. Only volumes [0, 1] is allowed.
     * Volumes over will be changed to 1 and volumes under will be changed to 0.
     * @param volume volume to change to.
     */
    public void setVolume(double volume) {
        if (volume < 0) {
            volume = 0;
        } else if (volume > 1) {
            volume = 1;
        }
        this.volume = volume;
    }

    public boolean areNotificationsOn() {
        return notificationsOn;
    }

    public void toggleNotifications() {
        notificationsOn = !notificationsOn;
    }

    public boolean isNotificationOn(Notification notification) {
        return notifications.get(notification);
    }

    public void toggleSpecificNotification(Notification notification) {
        if (!notificationsOn) {
            return;
        }

        Boolean currentState = notifications.get(notification);
        Boolean toggledState = !currentState;
        notifications.put(notification, toggledState);
    }

    public int getDailyReportTime() {
        return dailyReportTime;
    }

    public void setDailyReportTime(int dailyReportTime) {
        this.dailyReportTime = dailyReportTime;
    }

    public double getElectricityPriceLimit() {
        return electricityPriceLimit;
    }

    public void setElectricityPriceLimit(double electricityPriceLimit) {
        this.electricityPriceLimit = electricityPriceLimit;
    }

    public int getStartNotificationTime() {
        return startNotificationTime;
    }

    public void setStartNotificationTime(int startNotificationTime) {
        this.startNotificationTime = startNotificationTime;
    }

    public int getEndNotificationTime() {
        return endNotificationTime;
    }

    public void setEndNotificationTime(int endNotificationTime) {
        this.endNotificationTime = endNotificationTime;
    }
}

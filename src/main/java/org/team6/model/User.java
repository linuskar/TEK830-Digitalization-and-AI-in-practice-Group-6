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

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

        notifications.put(Notification.LOW_ELECTRICITY_PRICE, true);
        notifications.put(Notification.HIGH_ELECTRICITY_PRICE, true);
        notifications.put(Notification.SUNNY_WEATHER, true);
        notifications.put(Notification.COLD_WEATHER, true);
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

    void setVolume(double volume) {
        this.volume = volume;
    }

    public boolean areNotificationsOn() {
        return notificationsOn;
    }

    void toggleNotifications() {
        notificationsOn = !notificationsOn;
    }

    public boolean isNotificationOn(Notification notification) {
        return notifications.get(notification);
    }

    void toggleSpecificNotification(Notification notification) {
        Boolean currentState = notifications.get(notification);
        Boolean toggledState = !currentState;
        notifications.put(notification, toggledState);
    }
}

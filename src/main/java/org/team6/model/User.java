package org.team6.model;

import java.util.EnumMap;
import java.util.Map;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private SoundLevel soundLevel = SoundLevel.VIBRATIONS;
    private boolean notificationsOn = true;
    private final Map<Notification, Boolean> notifications = new EnumMap<>(Notification.class);
    private final Map<Notification, String> notificationText = new EnumMap<>(Notification.class);

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

        notifications.put(Notification.ONE, true);
        notifications.put(Notification.TWO, true);
        notifications.put(Notification.THREE, true);
        notifications.put(Notification.FOUR, true);
        notificationText.put(Notification.ONE, "Electricity price under predefined value");
        notificationText.put(Notification.TWO, "Electricity price over predefined value");
        notificationText.put(Notification.THREE, "Sunny weather, feel guilty free to run that load of laundry");
        notificationText.put(Notification.FOUR, "Cold weather, make sure to close all doors and windows");
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

    public SoundLevel getSoundLevel() {
        return soundLevel;
    }

    public void setSoundLevel(SoundLevel soundLevel) {
        this.soundLevel = soundLevel;
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

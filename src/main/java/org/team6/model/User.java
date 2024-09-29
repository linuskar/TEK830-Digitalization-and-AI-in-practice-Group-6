package org.team6.model;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private SoundLevel soundLevel = SoundLevel.VIBRATIONS;
    private boolean notificationsOn = true;
    private final Map<Notification, Boolean> notifications = new HashMap<>();

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

        notifications.put(Notification.ONE, true);
        notifications.put(Notification.TWO, true);
        notifications.put(Notification.THREE, true);
        notifications.put(Notification.FOUR, true);
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

    public void toggleNotifications() {
        notificationsOn = !notificationsOn;
    }

    public boolean isNotificationOn(Notification notification) {
        return notifications.get(notification);
    }

    public void toggleSpecificNotification(Notification notification) {
        notifications.compute(notification, (k, oldValue) -> Boolean.FALSE.equals(oldValue));
    }
}

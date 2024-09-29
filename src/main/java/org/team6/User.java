package org.team6;

import java.util.HashMap;
import java.util.Map;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Sound soundLevel = Sound.VIBRATIONS;
    private boolean notificationsOn = true;
    private final Map<Notifications, Boolean> notifications = new HashMap<>();

    public User(String firstName, String lastName, String email, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;

        notifications.put(Notifications.ONE, true);
        notifications.put(Notifications.TWO, true);
        notifications.put(Notifications.THREE, true);
        notifications.put(Notifications.FOUR, true);
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

    public Sound getSoundLevel() {
        return soundLevel;
    }

    public void setSoundLevel(Sound soundLevel) {
        this.soundLevel = soundLevel;
    }

    public boolean areNotificationsOn() {
        return notificationsOn;
    }

    public void toggleNotifications() {
        notificationsOn = !notificationsOn;
    }

    public boolean isNotificationOn(Notifications notification) {
        return notifications.get(notification);
    }

    public void toggleSpecificNotification(Notifications notification) {
        notifications.compute(notification, (k, oldValue) -> Boolean.FALSE.equals(oldValue));
    }
}

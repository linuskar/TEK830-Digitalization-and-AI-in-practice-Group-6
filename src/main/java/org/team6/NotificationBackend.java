package org.team6;

public class NotificationBackend {
    // Static classes shouldn't expose constructor.
    private NotificationBackend() {}

    public static void turnOnTurnOffAllNotifications(User u) {
        u.toggleNotifications();
    }

    public static void turnOnTurnOffASpecificNotification(User u, Notifications type) {
        if (u.areNotificationsOn()) {
            u.toggleSpecificNotification(type);
        }
    }

    public static void changeSoundLevel(User u, Sound type) {
        u.setSoundLevel(type);
    }
}

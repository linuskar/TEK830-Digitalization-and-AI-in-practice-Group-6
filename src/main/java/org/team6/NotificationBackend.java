package org.team6;

public class NotificationBackend {
    // Static classes shouldn't expose constructor.
    private NotificationBackend() {}

    public static void turnOnTurnOffAllNotifications(User u) {
        u.toggleNotifications();
    }

    public static void turnOnTurnOffASpecificNotification(User u, Notification type) {
        if (u.areNotificationsOn()) {
            u.toggleSpecificNotification(type);
        }
    }

    public static void changeSoundLevel(User u, SoundLevel type) {
        u.setSoundLevel(type);
    }
}

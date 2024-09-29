package org.team6.model;

public class NotificationBackend {
    // Static classes shouldn't expose constructor.
    private NotificationBackend() {}

    public static void turnOnTurnOffAllNotifications(User user) {
        user.toggleNotifications();
    }

    public static void turnOnTurnOffASpecificNotification(User user, Notification type) {
        if (user.areNotificationsOn()) {
            user.toggleSpecificNotification(type);
        }
    }

    public static void changeSoundLevel(User user, SoundLevel type) {
        user.setSoundLevel(type);
    }
}

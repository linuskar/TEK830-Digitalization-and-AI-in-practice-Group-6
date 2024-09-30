package org.team6.model;

public class NotificationBackend {
    // Static classes shouldn't expose constructor.
    private NotificationBackend() {}

    public static void toggleAllNotifications(User user) {
        user.toggleNotifications();
    }

    public static void toggleASpecificNotification(User user, Notification type) {
        if (user.areNotificationsOn()) {
            user.toggleSpecificNotification(type);
        }
    }

    public static void changeSoundLevel(User user, SoundLevel type) {
        user.setSoundLevel(type);
    }
}

package org.team6.model;

public class NotificationBackend {
    private static User user;

    // Static classes shouldn't expose constructor.
    private NotificationBackend() {}

    public static void toggleAllNotifications() {
        user.toggleNotifications();
    }

    public static void toggleASpecificNotification(Notification type) {
        if (user.areNotificationsOn()) {
            user.toggleSpecificNotification(type);
        }
    }

    public static double getVolume() {
        return user.getVolume();
    }

    // TODO: only allow between [0, 1]
    public static void changeVolume(double volume) {
        user.setVolume(volume);
    }

    public static void setUser(User user) {
        NotificationBackend.user = user;
    }
}

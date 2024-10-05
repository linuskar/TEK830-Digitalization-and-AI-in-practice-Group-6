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

    /** Set volume for the user that has been set previously. Only volumes [0, 1] is allowed.
     * Volumes over will be changed to 1 and volumes under will be changed to 0.
     * @param volume volume to change to.
     */
    public static void setVolume(double volume) {
        if (volume < 0) {
            volume = 0;
        } else if (volume > 1) {
            volume = 1;
        }
        user.setVolume(volume);
    }

    public static void setUser(User user) {
        NotificationBackend.user = user;
    }
}

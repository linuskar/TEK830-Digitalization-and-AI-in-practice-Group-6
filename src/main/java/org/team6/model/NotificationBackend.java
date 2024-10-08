package org.team6.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotificationBackend {
    private static User user;
    private static final List<NotificationListener> notificationListeners = new ArrayList<>();

    // Static classes shouldn't expose constructor.
    private NotificationBackend() {}

    public static void sendNotification(Notification notification) {
        boolean canSendNotification;
        if (notification == Notification.DAILY_REPORT) {
            Calendar calendar = Calendar.getInstance();
            int currentHour = calendar.get(Calendar.HOUR_OF_DAY);

            canSendNotification = areNotificationsOn()
                    && isNotificationOn(notification)
                    && user.getDailyReportTime() == currentHour;
        } else {
            canSendNotification = areNotificationsOn()
                    && isNotificationOn(notification);
        }

        if (canSendNotification) {
            for (NotificationListener notificationListener : notificationListeners) {
                notificationListener.onNotificationSent(notification);
            }
        }
    }

    public static void addNotificationListener(NotificationListener listenerToAdd) {
        notificationListeners.add(listenerToAdd);
    }

    // So far only exists for testing purposes.
    public static void removeNotificationListener(NotificationListener listenerToRemove) {
        notificationListeners.remove(listenerToRemove);
    }

    public static boolean areNotificationsOn() {
        return user.areNotificationsOn();
    }

    public static void toggleAllNotifications() {
        user.toggleNotifications();
    }

    public static boolean isNotificationOn(Notification type) {
        return user.isNotificationOn(type);
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

    public static int getDailyReportTime() {
        return user.getDailyReportTime();
    }

    public static void setDailyReportTime(int dailyReportTime) {
        user.setDailyReportTime(dailyReportTime);
    }

    public static double getElectricityPriceLimit() {
        return user.getElectricityPriceLimit();
    }

    public static void setElectricityPriceLimit(double electricityPriceLimit) {
        user.setElectricityPriceLimit(electricityPriceLimit);
    }

    public static int getStartNotificationTime() {
        return user.getStartNotificationTime();
    }

    public static void setStartNotificationTime(int startNotificationTime) {
        user.setStartNotificationTime(startNotificationTime);
    }

    public static int getEndNotificationTime() {
        return user.getEndNotificationTime();
    }

    public static void setEndNotificationTime(int endNotificationTime) {
        user.setEndNotificationTime(endNotificationTime);
    }

    public static void setUser(User user) {
        NotificationBackend.user = user;
    }
}

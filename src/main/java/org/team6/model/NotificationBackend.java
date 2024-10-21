package org.team6.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class NotificationBackend {
    private final User user;
    private final List<NotificationListener> notificationListeners = new ArrayList<>();

    // Static classes shouldn't expose constructor.
    public NotificationBackend(User user) {
        this.user = user;
    }

    public void sendNotification(Notification notification) {
        boolean canSendNotification;
        if (notification == Notification.DAILY_REPORT) {
            Calendar calendar = Calendar.getInstance();
            int currentHour = calendar.get(Calendar.HOUR_OF_DAY);

            canSendNotification = user.areNotificationsOn()
                    && user.isNotificationOn(notification)
                    && user.getDailyReportTime() == currentHour;
        } else {
            canSendNotification = user.areNotificationsOn()
                    && user.isNotificationOn(notification);
        }

        if (canSendNotification) {
            for (NotificationListener notificationListener : notificationListeners) {
                notificationListener.onNotificationSent(notification);
            }
        }
    }

    public void addNotificationListener(NotificationListener listenerToAdd) {
        notificationListeners.add(listenerToAdd);
    }

    // So far only exists for testing purposes.
    public void removeNotificationListener(NotificationListener listenerToRemove) {
        notificationListeners.remove(listenerToRemove);
    }
}

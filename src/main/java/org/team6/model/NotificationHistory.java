package org.team6.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class NotificationHistory {
    private static final int MAX_NOTIFICATION_AMOUNT = 5;
    private final Queue<Notification> sentNotifications = new LinkedList<>();

    // Adds notification to a list.
    public void addNotification(Notification notification) {
        if (sentNotifications.size() == MAX_NOTIFICATION_AMOUNT) {
            sentNotifications.remove();  // Remove the oldest notification
        }
        sentNotifications.add(notification);  // Add the new notification
    }

    /**
     * Return a list of notifications in reverse order, that is the most recent notification first and the oldest last.
     * @return List of notifications
     */
    public List<Notification> getNotificationList() {
        List<Notification> reversedList = new ArrayList<>(sentNotifications);
        Collections.reverse(reversedList);
        return reversedList;
    }
}

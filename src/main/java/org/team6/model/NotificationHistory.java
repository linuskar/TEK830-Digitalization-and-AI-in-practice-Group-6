package org.team6.model;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;

public class NotificationHistory {
    private static final int MAX_NOTIFICATION_AMOUNT = 5;
    private final List<Notification> sentNotifications = new ArrayList<>(MAX_NOTIFICATION_AMOUNT);

    // Adds notification to a list.
    @FXML
    public void addNotification(Notification notification) {
        if (sentNotifications.size() == MAX_NOTIFICATION_AMOUNT) {
            sentNotifications.remove(0);  // Remove the oldest notification
        }
        sentNotifications.add(notification);  // Add the new notification
    }
    public List<Notification> getNotificationList(){
        return sentNotifications;
    }
}

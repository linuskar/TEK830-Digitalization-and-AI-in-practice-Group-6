package org.team6.model;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;

public class NotificationHistory {

        private ArrayList<Notification> notificationArray = new ArrayList<>();

        //Adds notification to an arraylist.
        @FXML
        public void addNotification(Notification notification) {
            // TODO : Change max array size.
            if (notificationArray.size() == 5) {
                notificationArray.remove(0);  // Remove the oldest notification
            }
            notificationArray.add(notification);  // Add the new notification
        }
        public List<Notification> getNotificationList(){
            return notificationArray;
        }
    }
    
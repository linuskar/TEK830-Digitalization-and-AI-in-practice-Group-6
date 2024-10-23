package org.team6.model;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;

    public class NotificationHistory {

        private ArrayList<AnchorPane> notificationArray = new ArrayList<>();



        //Adds notification to an arraylist.
        @FXML
        public void addNotification(AnchorPane notificationPane) {
            // TODO : Change max array size.
            if (notificationArray.size() == 5) {
                notificationArray.remove(0);  // Remove the oldest notification
            }
            notificationArray.add(notificationPane);  // Add the new notification
        }
        public ArrayList<AnchorPane> getNotificationList(){
            return notificationArray;
        }
    }


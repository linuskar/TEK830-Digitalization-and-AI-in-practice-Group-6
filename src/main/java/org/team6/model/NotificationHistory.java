package org.team6.model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.AccessibleAction;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import org.team6.controller.NotificationController;
import org.team6.controller.NotificationPageController;

import java.io.IOException;
import java.util.ArrayList;

    public class NotificationHistory {

        private ArrayList<AnchorPane> notificationArray = new ArrayList<>();



        //Adds notification to an arraylist.
        @FXML
        public void addNotification(AnchorPane notificationPane) {
            // TODO : Change max array size.
            if (notificationArray.size() == 2) {
                notificationArray.remove(0);  // Remove the oldest notification
                System.out.println("I am removed");
            }
            notificationArray.add(notificationPane);  // Add the new notification
            System.out.println("I have been added");
        }
        public ArrayList<AnchorPane> getNotificationList(){
            return notificationArray;
        }
    }


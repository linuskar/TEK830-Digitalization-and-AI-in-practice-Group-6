package org.team6.model;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.layout.AnchorPane;
import org.team6.controller.NotificationController;

import java.io.IOException;
import java.util.ArrayList;

public class NotificationHistory {

    private ArrayList<AnchorPane> notificationArray = new ArrayList<>();

    //private NotificationController notification = new NotificationController();

//Adds notification to an arraylist.

    @FXML
    public void addNotification() {
       /* try {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/NotificationTemplate.fxml"));
            AnchorPane notificationPane = loader.load();

            if (notificationArray.size() == 3) {
                notificationArray.remove(0);  // Remove the oldest notification
            }
            notificationArray.add(notificationPane);  // Add the new notification

        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public ArrayList<AnchorPane> getNotificationList(){
        return notificationArray;
    }
}


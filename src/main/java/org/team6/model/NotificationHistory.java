package org.team6.model;

import javafx.scene.layout.AnchorPane;
import org.team6.controller.NotificationController;
import java.util.ArrayList;

public class NotificationHistory {

    //private AnchorPane[] notificationArray = new AnchorPane[10];
    private ArrayList<AnchorPane> notificationArray = new ArrayList<>();

    private NotificationController notification = new NotificationController();

//Adds notification to an arraylist.
    private ArrayList<AnchorPane> addNotification(){
        if(notification.getNotificationStatus()){

        }
        return notificationArray;

    }
}

package org.team6.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.team6.model.NotificationHistory;
import org.team6.view.PageStarter;

import java.io.IOException;
import java.util.ArrayList;

public class NotificationPageController {


    @FXML
    private AnchorPane notificationPageAnchor;


    @FXML
    private Button backButton;

    @FXML
    private VBox notificationHistoryVBox;

    @FXML
    private Button testButton;

    private NotificationHistory notificationHistory;

    @FXML
    private Button searchButton;

    @FXML
    private Button accountButton;

    @FXML
    private ToggleButton toggleButton;

    @FXML
    private String notificationText;

    public void setNotificationHistory(NotificationHistory history) {
        this.notificationHistory = history;
    }

    public void setNotificationText(String text){
        this.notificationText = text;
    }

    @FXML
    private void handleHomeButton(){
        PageStarter.switchToHomePage();
    }




    @FXML
    private void initialize() {
        System.out.println(notificationText);
    }

    @FXML
    private void hideVbox(){
        notificationHistoryVBox.setVisible(false);
    }

    // Method adds a notification to a VBox. When a button is pressed the notification is added to the vbox.
    @FXML
    public void addNotificationToVbox() throws IOException {
        if (notificationHistoryVBox == null) {
            System.out.println("notificationHistoryVBox is null!");
            return;
        }
        notificationHistoryVBox.getChildren().clear();
        ArrayList<AnchorPane> notificationList = notificationHistory.getNotificationList();

        System.out.println("I am added");

        for (AnchorPane notification : notificationList) {
            // Load a new instance of the FXML file, could not make it work otherwise.
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/NotificationTemplate.fxml"));
            AnchorPane notificationCopy = loader.load();  // This gives a fresh copy

            //Transfer the data from the original notification.
            NotificationController notificationController = loader.getController();
            notificationController.setNotificationText(notificationText);
            System.out.println(notificationText);

            notificationHistoryVBox.getChildren().add(notificationCopy);
            System.out.println("Hello!");
            notificationCopy.setVisible(true);
            notificationCopy.toFront();

        }
        notificationHistoryVBox.setVisible(toggleButton.isSelected());
    }



    @FXML
    private void handleEnergyButtonAction(){
        PageStarter.switchToEnergyPage();
    }

    @FXML
    private void handleSettingsButtonAction(){
        PageStarter.switchToSettingsPage();
    }
}
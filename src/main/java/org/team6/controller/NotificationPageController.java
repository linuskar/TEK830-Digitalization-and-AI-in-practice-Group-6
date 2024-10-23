package org.team6.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import org.team6.model.Notification;
import org.team6.model.NotificationHistory;
import org.team6.view.PageStarter;

import java.io.IOException;
import java.util.ArrayList;

public class NotificationPageController {

    @FXML
    private VBox notificationHistoryVBox;

    private NotificationHistory notificationHistory;

    private AnchorPane notificationPane;

    @FXML
    private ToggleButton toggleButton;

    public void setNotificationHistory(NotificationHistory history) {
        this.notificationHistory = history;
    }

    public void initialize(){
        toggleButton.setSelected(true);
        notificationHistoryVBox.setVisible(true);
    }

    @FXML
    public void addNotificationToVbox() throws IOException {
        if (notificationHistoryVBox == null) {
            System.out.println("notificationHistoryVBox is null!");
            return;
        }

        notificationHistoryVBox.getChildren().clear(); // Clear the VBox

        ArrayList<Notification> notificationList = notificationHistory.getNotificationList(); // Get notifications

        for (Notification notification : notificationList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/NotificationTemplate.fxml"));
            AnchorPane notificationPane = loader.load();  // Load FXML for each notification
            // Set the notification text in the controller of the notification template
            NotificationController notificationController = loader.getController();
            notificationController.setNotificationText(Notification.getText(notification));  // Use actual notification text

            notificationHistoryVBox.getChildren().add(notificationPane); // Add to VBox

            notificationPane.setVisible(true);
        }



    }

    public void makeNotificationHistoryComponentsVisible() {
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

    @FXML
    private void handleHomeButton(){
        PageStarter.switchToHomePage();
    }

}
package org.team6.controller;

import java.io.IOException;
import java.util.List;

import org.team6.model.Notification;
import org.team6.model.NotificationHistory;
import org.team6.view.PageStarter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class NotificationPageController {

    @FXML
    private VBox notificationHistoryVBox;

    private NotificationHistory notificationHistory;


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

        List<Notification> notificationList = notificationHistory.getNotificationList(); // Get notifications

        for (Notification notification : notificationList) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/NotificationTemplate.fxml"));
            AnchorPane notificationPane = loader.load();  // Load FXML for each notification
            NotificationController notificationController = loader.getController();

            notificationController.setNotificationText(Notification.getText(notification));  // Used the actual notification text

            notificationHistoryVBox.getChildren().add(notificationPane); // Add to VBox

            notificationPane.setVisible(true);
        }



    }

    public void makeNotificationHistoryComponentsVisible() {
        //if the toggleButton is selected then the vbox is visible
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
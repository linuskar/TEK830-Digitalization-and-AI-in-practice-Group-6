package org.team6.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.team6.controller.NotificationPageController;
import org.team6.model.NotificationHistory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SettingsPageController {

    @FXML
    private AnchorPane settingsPane;

    @FXML
    private ToggleButton sendNotificationsToggleButton;
    @FXML
    private ToggleButton sendLowElectricPriceToggleButton;
    @FXML
    private Slider volumeSlider;

    @FXML
    private Button NotificationButton;

    private NotificationHistory sharedNotificationHistory;
    private NotificationPageController notificationPageController;

    // List of specific notification buttons. For instance used for
    // enabling and disabling all buttons if notifications are switched on or off.

    private final List<ToggleButton> notificationButtons = new ArrayList<>();

    @FXML
    public void initialize(){
        //settingsPane.setVisible(true);
        //settingsPane.setDisable(false);
    }

    public void setNotificationHistory(NotificationHistory notificationHistory) {
        this.sharedNotificationHistory = notificationHistory;
    }

    public void setNotificationPageController(NotificationPageController notificationPageController) {
        this.notificationPageController = notificationPageController;
    }



   /* public SettingsPageController() {
        notificationButtons.add(sendNotificationsToggleButton);
        // Don't think you can add it directly in Scenebuilder.
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // If double is not the supported type in the model, it can easily be changed here I think.
            handleVolumeChanged(newValue.doubleValue());
        });
    }*/

    // Meant for toggling notifications in general.
    @FXML
    private void handleSendNotificationsOnAction() {
        // TODO: make it work with model.
        boolean isSelected = sendNotificationsToggleButton.isSelected();
        if (isSelected) {
            // NotificationModel.enableNotifications()
        } else {
            // NotificationModel.disableNotifications()
        }

        for (ToggleButton button : notificationButtons) {
            button.setDisable(!isSelected);
        }
    }

    // Example of specifically toggling a notification.
    @FXML
    private void handleLowElectricPriceOnAction() {
        // TODO: make it work with model.
        boolean isSelected = sendLowElectricPriceToggleButton.isSelected();
        if (isSelected) {
            // NotificationModel.enableNotification(NotificationType.LOW_ELECTRIC)
        } else {
            // NotificationModel.disableNotification(NotificationType.LOW_ELECTRIC)
        }
    }

    @FXML
    private void handleBackOnAction() {
        settingsPane.setVisible(false);
    }

    private void handleVolumeChanged(double newVolume) {
        // TODO: make it work with model.
        // NotificationModel.setVolume(newVolume)
    }
    //Pops up the notification page.

    private NotificationHistory sharedHistory = new NotificationHistory();  // Shared instance

    @FXML
    private void handleNotificationPageOnAction() throws IOException {
        // Load NotificationPage FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/NotificationPage.fxml"));
        Parent notificationPage = loader.load();

        // Get the controller for NotificationPage
        NotificationPageController notificationPageController = loader.getController();

        // Inject the shared NotificationHistory instance
        notificationPageController.setNotificationHistory(sharedNotificationHistory);

        // Now, inject the same shared NotificationHistory to NotificationController
        NotificationController notificationController = new NotificationController();
        notificationController.setNotificationHistory(sharedNotificationHistory);

        // Inject the NotificationPageController into NotificationController
        notificationController.setNotificationPageController(notificationPageController);

        // Add NotificationPage to your scene
        settingsPane.getChildren().add(notificationPage);
    }
}




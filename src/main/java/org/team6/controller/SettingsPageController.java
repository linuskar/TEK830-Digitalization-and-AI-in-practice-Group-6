package org.team6.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

public class SettingsPageController {
    @FXML
    private ToggleButton sendNotificationsToggleButton;
    @FXML
    private ToggleButton sendLowElectricPriceToggleButton;

    // Meant for toggling notifications in general.
    @FXML
    private void handleSendNotificationsOnAction() {
        boolean isSelected = sendNotificationsToggleButton.isSelected();
        if (isSelected) {
            // NotificationModel.enableNotifications()
        } else {
            // NotificationModel.disableNotifications()
        }
    }

    // Example of specifically toggling a notification.
    @FXML
    private void handleLowElectricPriceOnAction() {
        boolean isSelected = sendLowElectricPriceToggleButton.isSelected();
        if (isSelected) {
            // NotificationModel.enableNotification(NotificationType.LOW_ELECTRIC)
        } else {
            // NotificationModel.disableNotification(NotificationType.LOW_ELECTRIC)
        }
    }

}

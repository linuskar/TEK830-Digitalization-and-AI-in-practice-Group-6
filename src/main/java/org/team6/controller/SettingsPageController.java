package org.team6.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

public class SettingsPageController {
    @FXML
    private ToggleButton sendNotificationsToggleButton;

    @FXML
    private void handleSendNotificationsOnAction() {
        boolean isSelected = sendNotificationsToggleButton.isSelected();
        if (isSelected) {
            // NotificationModel.EnableNotifications()
        } else {
            // NotificationModel.DisableNotifications()
        }
    }

}

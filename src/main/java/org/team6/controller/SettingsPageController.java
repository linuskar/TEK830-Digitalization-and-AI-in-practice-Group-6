package org.team6.controller;

import javafx.fxml.FXML;
import javafx.scene.control.ToggleButton;

import java.util.ArrayList;
import java.util.List;

public class SettingsPageController {
    @FXML
    private ToggleButton sendNotificationsToggleButton;
    @FXML
    private ToggleButton sendLowElectricPriceToggleButton;

    // List of specific notification buttons. For instance used for
    // enabling and disabling all buttons if notifications are switched on or off.
    private final List<ToggleButton> notificationButtons = new ArrayList<>();

    public SettingsPageController() {
        notificationButtons.add(sendNotificationsToggleButton);
    }

    // Meant for toggling notifications in general.
    @FXML
    private void handleSendNotificationsOnAction() {
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
        boolean isSelected = sendLowElectricPriceToggleButton.isSelected();
        if (isSelected) {
            // NotificationModel.enableNotification(NotificationType.LOW_ELECTRIC)
        } else {
            // NotificationModel.disableNotification(NotificationType.LOW_ELECTRIC)
        }
    }
}

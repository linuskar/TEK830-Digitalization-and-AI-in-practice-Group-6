package org.team6.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import org.team6.model.NotificationBackend;

import java.util.ArrayList;
import java.util.List;

public class SettingsPageController {
    @FXML
    private ToggleButton sendNotificationsToggleButton;
    @FXML
    private ToggleButton sendLowElectricPriceToggleButton;
    @FXML
    private Slider volumeSlider;

    // List of specific notification buttons. For instance used for
    // enabling and disabling all buttons if notifications are switched on or off.
    private final List<ToggleButton> notificationButtons = new ArrayList<>();

    public SettingsPageController() {
        notificationButtons.add(sendNotificationsToggleButton);
        // Don't think you can add it directly in Scenebuilder.
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // If double is not the supported type in the model, it can easily be changed here I think.
            handleVolumeChanged(newValue.doubleValue());
        });
    }

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
        // TODO: go back to home page
    }

    private void handleVolumeChanged(double newVolume) {
        NotificationBackend.changeVolume(newVolume);
    }
}

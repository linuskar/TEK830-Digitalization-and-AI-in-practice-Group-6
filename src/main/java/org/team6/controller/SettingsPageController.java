package org.team6.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import org.team6.model.Notification;
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
    // Nicer to show the user a scale from 0 to 100 rather than 0 to 1.
    private static final int VOLUME_SCALE_FACTOR = 100;

    // List of specific notification buttons. For instance used for
    // enabling and disabling all buttons if notifications are switched on or off.
    private final List<ToggleButton> notificationButtons = new ArrayList<>();

    public SettingsPageController() {
        notificationButtons.add(sendNotificationsToggleButton);

        volumeSlider.setValue(NotificationBackend.getVolume()*VOLUME_SCALE_FACTOR);
        // Don't think you can add it directly in Scenebuilder.
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // If double is not the supported type in the model, it can easily be changed here I think.
            handleVolumeChanged(newValue.doubleValue());
        });

        sendNotificationsToggleButton.setSelected(NotificationBackend.areNotificationsOn());
        sendLowElectricPriceToggleButton.setSelected((NotificationBackend.isNotificationOn(Notification.LOW_ELECTRICITY_PRICE)));
    }

    // Meant for toggling notifications in general.
    @FXML
    private void handleSendNotificationsOnAction() {
        NotificationBackend.toggleAllNotifications();

        boolean isSelected = sendNotificationsToggleButton.isSelected();

        for (ToggleButton button : notificationButtons) {
            button.setDisable(!isSelected);
        }
    }

    // Example of specifically toggling a notification.
    @FXML
    private void handleLowElectricPriceOnAction() {
        NotificationBackend.toggleASpecificNotification(Notification.LOW_ELECTRICITY_PRICE);
    }

    @FXML
    private void handleBackOnAction() {
        // TODO: go back to home page
    }

    private void handleVolumeChanged(double newVolume) {
        NotificationBackend.setVolume(newVolume/VOLUME_SCALE_FACTOR);
    }
}

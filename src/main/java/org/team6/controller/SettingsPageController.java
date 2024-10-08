package org.team6.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.stage.Stage;
import org.team6.model.Notification;
import org.team6.model.NotificationBackend;
import org.team6.view.PageStarter;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class SettingsPageController implements Initializable {
    @FXML
    private ToggleButton sendNotificationsToggleButton;
    @FXML
    private ToggleButton sendLowElectricPriceToggleButton;
    @FXML
    private ToggleButton sendHighElectricPriceToggleButton;
    @FXML
    private ToggleButton sendSunnyWeatherToggleButton;
    @FXML
    private ToggleButton sendColdWeatherToggleButton;
    @FXML
    private Slider volumeSlider;

    @FXML
    private Button backButton;

    // Nicer to show the user a scale from 0 to 100 rather than 0 to 1.
    private static final int VOLUME_SCALE_FACTOR = 100;

    // List of specific notification buttons. For instance used for
    // enabling and disabling all buttons if notifications are switched on or off.
    private final List<ToggleButton> notificationButtons = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        notificationButtons.add(sendLowElectricPriceToggleButton);
        notificationButtons.add(sendHighElectricPriceToggleButton);
        notificationButtons.add(sendSunnyWeatherToggleButton);
        notificationButtons.add(sendColdWeatherToggleButton);
        initVolumeSlider();
        initToggleButtons();
    }

    private void initVolumeSlider() {
        volumeSlider.setValue(NotificationBackend.getVolume()*VOLUME_SCALE_FACTOR);
        // Don't think you can add it directly in Scenebuilder.
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // If double is not the supported type in the model, it can easily be changed here I think.
            handleVolumeChanged(newValue.doubleValue());
        });
    }

    private void initToggleButtons() {
        sendNotificationsToggleButton.setSelected(NotificationBackend.areNotificationsOn());
        // Maybe if notificationButtons is an enum map and buttons exist for all Notifications
        // Then this could be done in a nicer way through a for-loop.
        sendLowElectricPriceToggleButton.setSelected((NotificationBackend.isNotificationOn(Notification.LOW_ELECTRICITY_PRICE)));
        toggleNotificationButtonsAvailability();
    }

    // Meant for toggling notifications in general.
    @FXML
    private void handleSendNotificationsOnAction() {
        NotificationBackend.toggleAllNotifications();
        toggleNotificationButtonsAvailability();
    }

    private void toggleNotificationButtonsAvailability() {
        boolean isSelected = sendNotificationsToggleButton.isSelected();

        for (ToggleButton notificationButton : notificationButtons) {
            notificationButton.setDisable(!isSelected);
        }
    }

    // Example of specifically toggling a notification.
    @FXML
    private void handleLowElectricPriceOnAction() {
        NotificationBackend.toggleASpecificNotification(Notification.LOW_ELECTRICITY_PRICE);
    }

    @FXML
    private void handleHighElectricPriceOnAction() {
        NotificationBackend.toggleASpecificNotification(Notification.HIGH_ELECTRICITY_PRICE);
    }

    @FXML
    private void handleSunnyWeatherOnAction() {
        NotificationBackend.toggleASpecificNotification(Notification.SUNNY_WEATHER);
    }

    @FXML
    private void handleColdWeatherPriceOnAction() {
        NotificationBackend.toggleASpecificNotification(Notification.COLD_WEATHER);
    }

    @FXML
    private void handleBackOnAction() {
        Stage currentStage = (Stage) backButton.getScene().getWindow();
        PageStarter.switchToHomePage(currentStage);
    }

    private void handleVolumeChanged(double newVolume) {
        NotificationBackend.setVolume(newVolume/VOLUME_SCALE_FACTOR);
    }
}

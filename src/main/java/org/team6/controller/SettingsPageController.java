package org.team6.controller;

import javafx.event.ActionEvent;
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
import java.util.HashMap;
import java.util.Map;
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

    // Map of specific notification buttons. For instance used for
    // enabling and disabling all buttons if notifications are switched on or off.
    private final Map<ToggleButton, Notification> notificationButtons = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        notificationButtons.put(sendLowElectricPriceToggleButton, Notification.LOW_ELECTRICITY_PRICE);
        notificationButtons.put(sendHighElectricPriceToggleButton, Notification.HIGH_ELECTRICITY_PRICE);
        notificationButtons.put(sendSunnyWeatherToggleButton, Notification.SUNNY_WEATHER);
        notificationButtons.put(sendColdWeatherToggleButton, Notification.COLD_WEATHER);

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

        for (ToggleButton notificationButton : notificationButtons.keySet()) {
            notificationButton.setDisable(!isSelected);
        }
    }

    @FXML
    private void handleSpecificNotificationOnAction(ActionEvent event) {
        ToggleButton sourceButton = (ToggleButton) event.getSource();

        if (notificationButtons.containsKey(sourceButton)) {
            Notification notificationToBeToggled = notificationButtons.get(sourceButton);
            NotificationBackend.toggleASpecificNotification(notificationToBeToggled);
        } else {
            System.err.println("Event button" + sourceButton + "does not exist in Map!");
        }
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

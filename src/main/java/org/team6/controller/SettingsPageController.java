package org.team6.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.team6.model.Notification;
import org.team6.model.NotificationBackend;
import org.team6.view.PageStarter;

import java.io.IOException;
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
    }

    // Meant for toggling notifications in general.
    @FXML
    private void handleSendNotificationsOnAction() {
        NotificationBackend.toggleAllNotifications();

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
    private void handleBackOnAction() {
        Stage currentStage = (Stage) backButton.getScene().getWindow();
        try {
            PageStarter.switchToHomePage(currentStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleVolumeChanged(double newVolume) {
        NotificationBackend.setVolume(newVolume/VOLUME_SCALE_FACTOR);
    }
}

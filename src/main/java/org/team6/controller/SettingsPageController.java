package org.team6.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import org.team6.controller.NotificationPageController;
import org.team6.model.NotificationHistory;
import org.team6.model.Notification;
import org.team6.model.NotificationBackend;

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
    // Nicer to show the user a scale from 0 to 100 rather than 0 to 1.
    private static final int VOLUME_SCALE_FACTOR = 100;

    @FXML
    private Button NotificationButton;

    @FXML
    private Parent NotificationPage;

    @FXML
    private Pane notificationPane;

    // List of specific notification buttons. For instance used for
    // enabling and disabling all buttons if notifications are switched on or off.
    private final List<ToggleButton> notificationButtons = new ArrayList<>();

    /*public SettingsPageController() {
        notificationButtons.add(sendNotificationsToggleButton);
        initVolumeSlider();
        initToggleButtons();
    }*/

    /*private void initVolumeSlider() {
        volumeSlider.setValue(NotificationBackend.getVolume()*VOLUME_SCALE_FACTOR);
        // Don't think you can add it directly in Scenebuilder.
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // If double is not the supported type in the model, it can easily be changed here I think.
            handleVolumeChanged(newValue.doubleValue());
        });
    }*/

    public void setParentPage(Parent notificationPage) {
        this.NotificationPage = notificationPage;
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
        NotificationBackend.toggleASpecificNotification(Notification.LOW_ELECTRICITY_PRICE);
    }

    @FXML
    private void handleBackOnAction() {
        settingsPane.setVisible(false);
        settingsPane.toBack();
    }
//Displays notification page in settingsPane.
    @FXML
    private void handleNotificationPageOnAction()  {
        //Prevents duplication error
        if (!settingsPane.getChildren().contains(NotificationPage)) {
            settingsPane.getChildren().add(NotificationPage);
        }

        NotificationPage.setVisible(true);
        NotificationPage.toFront();

    }

    private void handleVolumeChanged(double newVolume) {
        NotificationBackend.setVolume(newVolume/VOLUME_SCALE_FACTOR);
    }
}

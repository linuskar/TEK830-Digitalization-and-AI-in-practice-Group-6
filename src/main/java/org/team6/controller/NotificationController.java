package org.team6.controller;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import org.team6.model.Notification;

import java.util.EnumMap;
import java.util.Map;

public class NotificationController {

    @FXML
    private Text notificationText;
    @FXML
    private AnchorPane notificationPane;
    
    private boolean isNotificationVisible = false;
    private final Map<Notification, String> notificationStringEnumMap = new EnumMap<>(Notification.class);
    
    @FXML
    public void initialize() {
        notificationPane.setVisible(false);
        notificationPane.setDisable(true);
        notificationStringEnumMap.put(Notification.ONE, "Electricity price under predefined value");
        notificationStringEnumMap.put(Notification.TWO, "Electricity price over predefined value");
        notificationStringEnumMap.put(Notification.THREE, "Sunny weather, feel guilty free to run that load of laundry");
        notificationStringEnumMap.put(Notification.FOUR, "Cold weather, make sure to close all doors and windows");
    }

    public void setNotificationText(String text){
        notificationText.setText(text);
    }

    public String getNotificationText(){
        return notificationText.getText();
    }

    public void setupKeyHandling(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKeyPress);
    }

    public void showNotificationPane() {
        if (isNotificationVisible) {
            return; // Prevent spamming
        }

        isNotificationVisible = true;

        notificationPane.setTranslateY(-notificationPane.getHeight());
        setNotificationText(notificationStringEnumMap.get(Notification.ONE));
        notificationPane.setVisible(true);
        notificationPane.setDisable(false);

        TranslateTransition slideDown = new TranslateTransition(Duration.seconds(0.5), notificationPane);
        slideDown.setToY(0);
        slideDown.setOnFinished(event -> {
            // Pause for 2 seconds before hiding
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> hideNotificationPane());
            pause.play();
        });

        slideDown.play();
    }

    public void hideNotificationPane() {
        TranslateTransition slideUp = new TranslateTransition(Duration.seconds(0.5), notificationPane);
        slideUp.setToY(-notificationPane.getHeight());
        slideUp.setOnFinished(event -> {
            notificationPane.setVisible(false);
            notificationPane.setDisable(true);
            isNotificationVisible = false; 
        });

        slideUp.play();
    }

    private void handleKeyPress(KeyEvent event) {
        String key = event.getText();
        switch (key) {
            case "p" -> showNotificationPane();
            default -> {}
        }
    }
}

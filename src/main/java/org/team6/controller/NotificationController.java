package org.team6.controller;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.team6.model.Notification;
import org.team6.model.NotificationBackend;
import org.team6.soundsystem.SoundPlayer;

public class NotificationController {

    @FXML
    private Text notificationText;
    @FXML
    private AnchorPane notificationPane;
    
    @FXML
    public void initialize() {
        notificationPane.setVisible(false);
        notificationPane.setDisable(true);
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

    public void showNotificationPane(String notificationText) {
        notificationPane.setTranslateY(-notificationPane.getHeight());
        setNotificationText(notificationText);
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
        });

        slideUp.play();
    }

    private void handleKeyPress(KeyEvent event) {
        Notification exampleNotification = Notification.LOW_ELECTRICITY_PRICE;
        boolean isNotificationOn = NotificationBackend.isNotificationOn(exampleNotification) && NotificationBackend.areNotificationsOn();

        String key = event.getText();
        switch (key) {
            case "p" -> {
                // Prevent spamming and check that notification is actually on.
                if (!notificationPane.isVisible() && isNotificationOn) {
                    // TODO: probably make these two observers of notification events or similar
                    // instead of notifications showing up through key presses.
                    showNotificationPane(Notification.getText(exampleNotification));
                    SoundPlayer.playSound(exampleNotification);
                }
            }
            default -> {}
        }
    }
}

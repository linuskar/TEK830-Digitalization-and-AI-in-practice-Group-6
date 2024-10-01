package org.team6.controller;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.util.Duration;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;

public class NotificationController {

    @FXML
    private Text notificationText;
    @FXML
    private AnchorPane notificationPane;
    private boolean isNotificationVisible = false;
    
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
        scene.addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            this.handleKeyPress(event);
        });
    }

    public void showNotificationPane() {
        if (isNotificationVisible) {
            return; // Prevent spamming
        }

        isNotificationVisible = true;

        // Set initial position above the visible area
        notificationPane.setTranslateY(-notificationPane.getHeight());
        notificationPane.setVisible(true);
        notificationPane.setDisable(false);

        // Create TranslateTransition to slide down
        TranslateTransition slideDown = new TranslateTransition(Duration.seconds(0.5), notificationPane);
        slideDown.setToY(0);
        slideDown.setOnFinished(event -> {
            // Pause for 2 seconds before hiding
            PauseTransition pause = new PauseTransition(Duration.seconds(2));
            pause.setOnFinished(e -> hideNotificationPane());
            pause.play();
        });

        // Play the slide down animation
        slideDown.play();
    }

    public void hideNotificationPane() {
        // Create TranslateTransition to slide up
        TranslateTransition slideUp = new TranslateTransition(Duration.seconds(0.5), notificationPane);
        slideUp.setToY(-notificationPane.getHeight());
        slideUp.setOnFinished(event -> {
            notificationPane.setVisible(false);
            notificationPane.setDisable(true);
            isNotificationVisible = false; // Reset the flag
        });

        // Play the slide up animation
        slideUp.play();
    }

    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case P -> {
                showNotificationPane();
            }
            default -> {
                
            }
        }
    }
}

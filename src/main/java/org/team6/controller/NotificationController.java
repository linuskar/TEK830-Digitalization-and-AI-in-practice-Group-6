package org.team6.controller;

import javafx.animation.PauseTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import org.team6.model.Notification;
import org.team6.model.NotificationBackend;
import org.team6.model.NotificationHistory;
import org.team6.model.NotificationListener;
import org.team6.view.ImageUtils;

import java.io.IOException;

public class NotificationController implements NotificationListener {

    @FXML
    private Text appNameText;
    @FXML
    private Text notificationText;
    @FXML
    private AnchorPane notificationPane;
    @FXML
    private ImageView imageView;

    NotificationPageController notificationPageController;
    
    @FXML
    public void initialize() {
        notificationPane.setVisible(false);
        notificationPane.setDisable(true);
        ImageUtils.makeCornersRounded(imageView, 20);
        appNameText.setText("IKEA Home App");
    }

    private NotificationHistory notificationHistory;

    public void setNotificationText(String text){
        notificationText.setText(text);
    }

    public void getNotificationPageController(NotificationPageController notificationPageController){
        this.notificationPageController  = notificationPageController;
    }

    public String getNotificationText(){
        return notificationText.getText();
    }

    public String getAppNameText() {
        return appNameText.getText();
    }

    public void setupKeyHandling(Scene scene) {
        scene.addEventHandler(KeyEvent.KEY_PRESSED, this::handleKeyPress);
    }


    public void setNotificationHistory(NotificationHistory notificationHistory){
        this.notificationHistory = notificationHistory;
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
        // Prevent spamming and check that notification is actually on.
        boolean canSendNotification = !notificationPane.isVisible();

        if (canSendNotification) {
            String key = event.getText();
            switch (key) {
                case "p" -> sendNotification(Notification.LOW_ELECTRICITY_PRICE);
                case "o" -> sendNotification(Notification.HIGH_ELECTRICITY_PRICE);
                case "i" -> sendNotification(Notification.SUNNY_WEATHER);
                case "u" -> sendNotification(Notification.COLD_WEATHER);
                case "y" -> sendNotification(Notification.DAILY_REPORT);
                default -> {}
            }
        }

    }

    private static void sendNotification(Notification notification) {
        NotificationBackend.sendNotification(notification);
    }

    @Override
    public void onNotificationSent(Notification notification) {
        showNotificationPane(Notification.getText(notification));
        addNotificationToList(notification);
        try {
            notificationPageController.addNotificationToVbox();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void addNotificationToList(Notification notification){
            notificationHistory.addNotification(notification);

    }
}

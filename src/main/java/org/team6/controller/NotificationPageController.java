package org.team6.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import org.team6.model.NotificationHistory;

import java.io.IOException;
import java.util.ArrayList;

public class NotificationPageController {


    @FXML
    private AnchorPane notificationPageAnchor;

    @FXML
    private Button notification1;

    @FXML
    private Button notification2;

    @FXML
    private Button notification3;

    @FXML
    private Pane volumePane;

    @FXML
    private Slider notificationVolumeSlider;

    @FXML
    private Button backButton;

    @FXML
    private VBox notificationHistoryVBox;
    @FXML
    private Button testButton;

    //private NotificationHistory notificationHistory;  // Model

    // Setter for injecting the NotificationHistory (model)

    private NotificationHistory notificationHistory;

    public void setNotificationHistory(NotificationHistory history) {
        this.notificationHistory = history;
    }

    @FXML
    private void initialize() {

    }

    @FXML
    private void handleBackOnAction() {
        notificationPageAnchor.setVisible(false);
    }

    @FXML
    public void addNotificationToVbox() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/NotificationTemplate.fxml"));
        AnchorPane notificationPane  = loader.load();
        notificationHistoryVBox.getChildren().addAll(notificationPane);
        notificationPane.setVisible(true);

        /*if (notificationHistoryVBox == null) {
            System.out.println("notificationHistoryVBox is null!");
            return;
        }

        notificationHistoryVBox.getChildren().clear();

        ArrayList<AnchorPane> notificationList = notificationHistory.getNotificationList();
        System.out.println("I am added");

        for (AnchorPane notification : notificationList) {
            notificationHistoryVBox.getChildren().add(notification);
            notification.setVisible(true);
        }*/
    }
}








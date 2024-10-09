package org.team6.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import org.h2.value.Transfer;
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
        notificationPageAnchor.toBack();
    }
// Method adds a notification to a VBox. When a button is pressed the notification is added to the vbox.
    @FXML
    public void addNotificationToVbox() throws IOException {
        if (notificationHistoryVBox == null) {
            System.out.println("notificationHistoryVBox is null!");
            return;
        }
        notificationHistoryVBox.getChildren().clear();
        ArrayList<AnchorPane> notificationList = notificationHistory.getNotificationList();

        System.out.println("I am added");

        for (AnchorPane notification : notificationList) {
                // Load a new instance of the FXML file, could not make it work otherwise.
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/NotificationTemplate.fxml"));
                AnchorPane notificationCopy = loader.load();  // This gives a fresh copy

                 //Transfer the data from the original notification.
                String originalText = notification.getChildren().stream().filter(node -> node instanceof Text).map(node -> ((Text) node).getText()).findFirst().orElse("No information");
                NotificationController notificationController = loader.getController();
                notificationController.setNotificationText(originalText);

                notificationHistoryVBox.getChildren().add(notificationCopy);
                System.out.println("Hello!");
                notificationCopy.setVisible(true);
                notificationCopy.toFront();

            }
        }
    }












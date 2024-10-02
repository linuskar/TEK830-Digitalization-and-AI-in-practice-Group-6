package org.team6.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import org.team6.model.Notification;
import org.team6.model.User;

import java.io.IOException;

public class HomePageController {
    @FXML
    private AnchorPane mainPage;

    @FXML
    private Text homePageTitle;

    @FXML
    private ImageView settingsImage;

    @FXML
    private ImageView accountImage;

    @FXML
    private ImageView menuImage1;

    @FXML
    private Pane barPane;

    @FXML
    private Button settingsButton;

    @FXML
    private Button accountButton;

    @FXML
    private Button searchButton;

    @FXML
    private BorderPane topBar;

    @FXML
    private Button menuButton1;

    @FXML
    private ImageView frontPageImage;

    @FXML
    private Button electricityButton;

    @FXML
    private Button tipsButton;

    @FXML
    private Text electricityText;

    private User currentUser; // Assume you have a user instance

    private Node notificationPane; // Reference to the notification pane


    @FXML
    public void initialize() {
        // Example: Initialize the user (you might want to retrieve this from a login system)
        currentUser = new User("John", "Doe", "john@example.com", "password123");

        // Listen for key events on the main page
        mainPage.setOnKeyPressed(this::handleKeyPress);

        // Request focus on the main page to capture key events
        mainPage.requestFocus();
    }

    private void handleKeyPress(KeyEvent event) {
        String key = event.getText(); // Get the character of the key pressed
        if ("p".equalsIgnoreCase(key)) { // Check if 'p' was pressed (case insensitive)
            toggleNotification("p"); // Call the method to toggle notification
        } else if ("k".equalsIgnoreCase(key)) {
            toggleNotification("k");
        } else if ("o".equalsIgnoreCase(key)) {
            toggleNotification("o");
        } else if ("l".equalsIgnoreCase(key)) {
            toggleNotification("l");
        }
    }

    private void toggleNotification(String type) {
        if (notificationPane == null) {
            try {
                // Load the NotificationView FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/NotificationTemplate.fxml"));
                notificationPane = loader.load(); // Load the notification pane
                NotificationController notificationController = loader.getController();

                String notificationText;

                // Set the notification text
                switch (type) {
                    case "p":
                        notificationText = currentUser.getNotificationText(Notification.ONE); // Get the notification text
                        break;
                    case "k":
                        notificationText = currentUser.getNotificationText(Notification.TWO); // Get the notification text
                        break;
                    case "o":
                        notificationText = currentUser.getNotificationText(Notification.THREE); // Get the notification text
                        break;
                    case "l":
                        notificationText = currentUser.getNotificationText(Notification.FOUR); // Get the notification text
                        break;
                    default:
                        notificationText = currentUser.getNotificationText(Notification.ONE); // Get the notification text;
                }


                notificationController.setNotificationText(notificationText);

                // Add the notification pane to the main page
                mainPage.getChildren().add(notificationPane);
                notificationPane.setLayoutX(10); // Adjust X position
                notificationPane.setLayoutY(100); // Adjust Y position
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception properly in a real application
            }
        } else {
            // Toggle visibility
            notificationPane.setVisible(!notificationPane.isVisible());
            notificationPane = null;
        }
    }
}

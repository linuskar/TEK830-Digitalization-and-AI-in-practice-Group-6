package org.team6.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MainViewController {

    @FXML
    private AnchorPane root;

    private HomePageController homePageController;
    private NotificationController notificationController;

    @FXML
    public void initialize() {
        try {
            // Load HomePage
            FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/org/team6/view/MainPage.fxml"));
            AnchorPane homePage = homePageLoader.load();
            homePageController = homePageLoader.getController();

            // Load NotificationPane
            FXMLLoader notificationLoader = new FXMLLoader(getClass().getResource("/org/team6/view/NotificationTemplate.fxml"));
            AnchorPane notificationPane = notificationLoader.load();
            notificationController = notificationLoader.getController();

            root.getChildren().addAll(homePage);
            homePage.getChildren().add(notificationPane);

            AnchorPane.setTopAnchor(notificationPane, 0.0);
            AnchorPane.setLeftAnchor(notificationPane, (homePage.getWidth() - notificationPane.getWidth()) / 2);
            AnchorPane.setRightAnchor(notificationPane, (homePage.getWidth() - notificationPane.getWidth()) / 2);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setupKeyHandling(Scene scene) {
        notificationController.setupKeyHandling(scene);
    }
}

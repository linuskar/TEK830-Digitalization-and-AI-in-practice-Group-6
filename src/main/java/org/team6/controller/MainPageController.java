package org.team6.controller;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;

public class MainPageController {

    @FXML
    private AnchorPane mainPage;
    @FXML
    private AnchorPane notificationPane;
    @FXML
    private AnchorPane homePage;

    private HomePageController homePageController;
    private NotificationController notificationController;

    @FXML
    public void initialize() {
        try {
            // Load HomePage
            FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/org/team6/view/HomePage.fxml"));
            homePage = homePageLoader.load();
            homePageController = homePageLoader.getController();

            // Load NotificationPane
            FXMLLoader notificationLoader = new FXMLLoader(getClass().getResource("/org/team6/view/NotificationTemplate.fxml"));
            notificationPane = notificationLoader.load();
            notificationController = notificationLoader.getController();

            mainPage.getChildren().addAll(homePage,notificationPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setupKeyHandling(Scene scene) {
        notificationController.setupKeyHandling(scene);
    }
}

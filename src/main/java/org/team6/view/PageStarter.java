package org.team6.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.team6.controller.NotificationController;
import org.team6.model.NotificationBackend;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PageStarter {
    private static AnchorPane homePane;
    private static AnchorPane settingsPane;
    private static final List<AnchorPane> pages = new ArrayList<>();
    
    private static Scene scene;

    private PageStarter() {
        // Private constructor to prevent instantiation
    }

    public static void initialize() {
        try {
            StackPane mainPage = getMainPage();
            scene = new Scene(mainPage);

            homePane = getHomePage();
            pages.add(homePane);

            FXMLLoader notificationLoader = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/NotificationTemplate.fxml"));
            AnchorPane notificationPane = notificationLoader.load();

            NotificationController notificationController = notificationLoader.getController();
            NotificationBackend.addNotificationListener(notificationController);

            settingsPane = getSettingsPage();
            pages.add(settingsPane);

            mainPage.getChildren().addAll(homePane, settingsPane, notificationPane);

            notificationController.setupKeyHandling(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setPageVisible(AnchorPane thePage) {
        for (AnchorPane page : pages) {
            page.setVisible(false);
        }

        thePage.setVisible(true);
    }

    public static void switchToHomePage(Stage primaryStage) {
        setPageVisible(homePane);

        primaryStage.setTitle("Home Page");
        primaryStage.setScene(scene);
    }

    public static void switchToSettingsPage(Stage primaryStage) {
        setPageVisible(settingsPane);
        primaryStage.setTitle("Settings Page");
        primaryStage.setScene(scene);
    }

    private static StackPane getMainPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/MainPage.fxml"));
        return fxmlLoader.load();
    }

    private static AnchorPane getHomePage() throws IOException {
        FXMLLoader homePageLoader = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/HomePage.fxml"));
        return homePageLoader.load();
    }

    private static AnchorPane getSettingsPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/settings_page.fxml"));
        return loader.load();
    }
}

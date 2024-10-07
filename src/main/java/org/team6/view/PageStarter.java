package org.team6.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.team6.Main;
import org.team6.controller.NotificationController;

import java.io.IOException;

public class PageStarter {
    private PageStarter() {}

    public static void switchToHomePage(Stage primaryStage) {
        try {
            StackPane mainPage = getMainPage();
            Scene scene = new Scene(mainPage);

            AnchorPane homePage = getHomePage();

            FXMLLoader notificationLoader = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/NotificationTemplate.fxml"));
            AnchorPane notificationPane = notificationLoader.load();

            NotificationController notificationController = notificationLoader.getController();

            mainPage.getChildren().addAll(homePage, notificationPane);

            notificationController.setupKeyHandling(scene);

            primaryStage.setTitle("Homepage");
            primaryStage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void switchToSettingsPage(Stage primaryStage) {
        try {
            AnchorPane settingsPage = getSettingsPage();
            Scene newScene = new Scene(settingsPage);

            primaryStage.setScene(newScene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private static StackPane getMainPage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/team6/view/MainPage.fxml"));
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

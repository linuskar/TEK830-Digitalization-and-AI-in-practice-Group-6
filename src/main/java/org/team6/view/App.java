package org.team6.view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.team6.Main;

import javafx.scene.layout.AnchorPane;

import org.team6.controller.*;

import java.io.IOException;

import javafx.scene.layout.StackPane;
import org.team6.model.NotificationHistory;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/team6/view/MainPage.fxml"));
        StackPane mainPage = fxmlLoader.load();
        Scene scene = new Scene(mainPage);

        FXMLLoader homePageLoader = new FXMLLoader(getClass().getResource("/org/team6/view/HomePage.fxml"));
        AnchorPane homePage = homePageLoader.load();

        FXMLLoader notificationLoader = new FXMLLoader(getClass().getResource("/org/team6/view/NotificationTemplate.fxml"));
        AnchorPane notificationPane = notificationLoader.load();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/settings_page.fxml"));
        Parent settingsPage = loader.load();

        HomePageController homePageController = homePageLoader.getController();
        homePageController.setSettingsPane(settingsPage);

        FXMLLoader notificationPageLoader = new FXMLLoader(getClass().getResource("/org/team6/view/NotificationPage.fxml"));
        Parent notificationPage = notificationPageLoader.load();

        NotificationPageController notificationPageController = notificationPageLoader.getController();

        SettingsPageController settingsPageController = loader.getController();
        settingsPageController.setParentPage(notificationPage);

        NotificationController notificationController = notificationLoader.getController();

        NotificationHistory history = new NotificationHistory();
        notificationController.setNotificationHistory(history);
        notificationPageController.setNotificationHistory(history);
        notificationController.setupKeyHandling(scene);


        mainPage.getChildren().addAll(homePage,notificationPane);


        primaryStage.setTitle("Homepage");
        primaryStage.setScene(scene);
        primaryStage.show();
    }





    public void startView(String[] args) {
        launch(args);
    }
}

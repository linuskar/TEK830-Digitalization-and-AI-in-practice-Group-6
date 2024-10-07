package org.team6.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


import org.team6.Main;
import org.team6.controller.SettingsPopUpController;

import javafx.scene.layout.AnchorPane;

import org.team6.controller.*;

import java.io.IOException;

import javafx.scene.layout.StackPane;

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

        NotificationController notificationController = notificationLoader.getController();

        FXMLLoader settingsPopUp = new FXMLLoader(getClass().getResource("/org/team6/view/settings_popup.fxml"));
        AnchorPane settingsPopUpPane = settingsPopUp.load();

        mainPage.getChildren().addAll(homePage,notificationPane,settingsPopUpPane);
        
        notificationController.setupKeyHandling(scene);
      
        primaryStage.setTitle("Homepage");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void startView(String[] args) {
        launch(args);
    }
}

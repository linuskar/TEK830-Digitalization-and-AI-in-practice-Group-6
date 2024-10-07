package org.team6.view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import org.team6.Main;

import javafx.scene.layout.AnchorPane;

import org.team6.controller.*;

import java.io.IOException;

import javafx.scene.layout.StackPane;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        PageStarter.switchToHomePage(primaryStage);
        primaryStage.show();
    }

    public void startView(String[] args) {
        launch(args);
    }
}

package org.team6;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import org.team6.controller.MainPageController;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/team6/view/MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        MainPageController mainViewController = fxmlLoader.getController();
        mainViewController.setupKeyHandling(scene);

        primaryStage.setTitle("Homepage");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
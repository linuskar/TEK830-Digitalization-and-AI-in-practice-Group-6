package org.team6;
import controller.HomePageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import controller.HomePageController.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/team6/MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        primaryStage.setTitle("Homepage");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
        controller.HomePageController home1 = new HomePageController();
        home1.test();
        System.out.println("Hello world!");
    }
}
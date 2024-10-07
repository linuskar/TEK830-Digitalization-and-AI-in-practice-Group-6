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

    public static void switchToHomePage(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/team6/view/MainPage.fxml"));
        StackPane mainPage = fxmlLoader.load();
        Scene scene = new Scene(mainPage);

        FXMLLoader homePageLoader = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/HomePage.fxml"));
        AnchorPane homePage = homePageLoader.load();

        FXMLLoader notificationLoader = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/NotificationTemplate.fxml"));
        AnchorPane notificationPane = notificationLoader.load();

        NotificationController notificationController = notificationLoader.getController();

        mainPage.getChildren().addAll(homePage,notificationPane);

        notificationController.setupKeyHandling(scene);

        primaryStage.setTitle("Homepage");
        primaryStage.setScene(scene);
    }

}

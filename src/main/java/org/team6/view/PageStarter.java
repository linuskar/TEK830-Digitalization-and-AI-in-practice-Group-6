package org.team6.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.team6.controller.NotificationController;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import javax.print.attribute.standard.PagesPerMinute;

import org.h2.engine.Setting;
import org.h2.mvstore.Page;

public class PageStarter {
    //private PageStarter() {}

    @FXML
    private static AnchorPane homePane;
    @FXML
    private static AnchorPane energyInsightsPane;
    @FXML
    private static AnchorPane settingsPane;
    @FXML
    private static AnchorPane recommendationsPane;
    private static ArrayList<AnchorPane> pages = new ArrayList<>();

    @FXML
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

            energyInsightsPane = getEnergyInsightsPage();
            pages.add(energyInsightsPane);

            recommendationsPane = getRecommendationsPage();
            pages.add(recommendationsPane);

            settingsPane = getSettingsPage();
            pages.add(settingsPane);

            mainPage.getChildren().addAll(homePane, energyInsightsPane, recommendationsPane, settingsPane, notificationPane);

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

    public static void switchToRecommendationsPage(Stage primaryStage) {
        setPageVisible(recommendationsPane);

        primaryStage.setTitle("Recommendations");
        primaryStage.setScene(scene);
    }

    public static void switchToEnergyInsightsPage(Stage primaryStage) {
        setPageVisible(energyInsightsPane);

        primaryStage.setTitle("Energy Insights");
        primaryStage.setScene(scene);
    }

    public static void switchToHomePage(Stage primaryStage) {
        setPageVisible(homePane);

        primaryStage.setTitle("Home");
        primaryStage.setScene(scene);
    }

    public static void switchToSettingsPage(Stage primaryStage) {
        setPageVisible(settingsPane);
        primaryStage.setTitle("Settings");
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

    private static AnchorPane getEnergyInsightsPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/EnergyInsightsPage.fxml"));
        return loader.load();
    }

    private static AnchorPane getRecommendationsPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/RecommendationsPage.fxml"));
        return loader.load();
    }

    private static AnchorPane getSettingsPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/settings_page.fxml"));
        return loader.load();
    }
}

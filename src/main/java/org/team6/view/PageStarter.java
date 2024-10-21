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
    @FXML
    private static AnchorPane energyInsightsPane;
    @FXML
    private static AnchorPane settingsPane;
    @FXML
    private static AnchorPane recommendationsPane;
    private static AnchorPane settingsPopUpPane;
    private static final List<AnchorPane> pages = new ArrayList<>();

    private static AnchorPane energyPane;
    private static Scene scene;
    private static Stage primaryStage;

    private static AnchorPane systemSettingsPane;

    private PageStarter() {
        // Private constructor to prevent instantiation
    }

    public static void initialize(Stage primaryStage) {
        try {
            PageStarter.primaryStage = primaryStage;

            StackPane mainPage = getMainPage();
            scene = new Scene(mainPage);

            homePane = getHomePage();
            pages.add(homePane);

            FXMLLoader notificationLoader = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/NotificationTemplate.fxml"));
            AnchorPane notificationPane = notificationLoader.load();

            NotificationController notificationController = notificationLoader.getController();
            NotificationBackend.addNotificationListener(notificationController);

            energyInsightsPane = getEnergyInsightsPage();
            pages.add(energyInsightsPane);

            recommendationsPane = getRecommendationsPage();
            pages.add(recommendationsPane);

            settingsPane = getSettingsPage();
            pages.add(settingsPane);

            AnchorPane appTutorialPage = getAppTutorialPage();

            energyPane = getEnergyPane();
            pages.add(energyPane);

            systemSettingsPane = getSystemSettingsPage();
            pages.add(systemSettingsPane);

            AnchorPane settingsPopUpPane = getSettingsPopUpPage();

            mainPage.getChildren().addAll(homePane, settingsPane, energyInsightsPane, recommendationsPane,appTutorialPage, settingsPopUpPane, energyPane,systemSettingsPane,notificationPane);

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

    public static void switchToRecommendationsPage() {
        setPageVisible(recommendationsPane);

        primaryStage.setTitle("Recommendations");
        primaryStage.setScene(scene);
    }

    public static void switchToEnergyInsightsPage() {
        setPageVisible(energyInsightsPane);

        primaryStage.setTitle("Energy Insights");
        primaryStage.setScene(scene);
    }

    public static void switchToHomePage() {
        setPageVisible(homePane);

        primaryStage.setTitle("Home");
        primaryStage.setScene(scene);
    }

    public static void switchToSettingsPage() {
        setPageVisible(settingsPane);

        primaryStage.setTitle("Settings");
        primaryStage.setScene(scene);
    }

    public static void switchToEnergyPage(){
        setPageVisible(energyPane);
        primaryStage.setTitle("Energy overview");
        primaryStage.setScene(scene);
    }

    public static void switchToSystemSettings(){
        setPageVisible(systemSettingsPane);
        primaryStage.setTitle("System Settings");
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

    private static AnchorPane getAppTutorialPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/AppTutorialPage.fxml"));
        return loader.load();
    }
    
    private static AnchorPane getEnergyPane() throws IOException {
        FXMLLoader energyLoader = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/EnergyPage.fxml"));
        return energyLoader.load();
    }

    private static AnchorPane getSettingsPopUpPage() throws IOException {
        FXMLLoader settingsPopUp = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/settings_popup.fxml"));
        return settingsPopUp.load();
    }

    private static AnchorPane getSystemSettingsPage() throws IOException {
        FXMLLoader systemSettingsPage = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/systemSettings.fxml"));
        return systemSettingsPage.load();
    }
}

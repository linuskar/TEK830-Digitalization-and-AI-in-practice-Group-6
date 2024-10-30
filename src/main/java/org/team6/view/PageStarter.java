package org.team6.view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.team6.controller.NotificationController;
import org.team6.controller.NotificationPageController;
import org.team6.model.NotificationBackend;
import org.team6.model.NotificationHistory;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class PageStarter {
    private static AnchorPane homePane;
    private static AnchorPane settingsPane;
    private static AnchorPane recommendationsPane;
    private static final List<AnchorPane> pages = new ArrayList<>();

    private static AnchorPane energyPane;
    private static Scene scene;
    private static Stage primaryStage;
    private static AnchorPane appTutorialPage;

    private static AnchorPane systemSettingsPane;

    private static AnchorPane energyUsagePane;

    private static AnchorPane energyPriceChartPane;

    private static AnchorPane notificationHistoryPane;

    private static NotificationPageController notificationPageController;

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

            recommendationsPane = getRecommendationsPage();
            pages.add(recommendationsPane);

            settingsPane = getSettingsPage();
            pages.add(settingsPane);

            appTutorialPage = getAppTutorialPage();

            energyPane = getEnergyPane();
            pages.add(energyPane);

            systemSettingsPane = getSystemSettingsPage();
            pages.add(systemSettingsPane);

            energyUsagePane = getEnergyUsagePage();
            pages.add(energyUsagePane);

            energyPriceChartPane = getEnergyPriceChartPage();
            pages.add(energyPriceChartPane);

            AnchorPane settingsPopUpPane = getSettingsPopUpPage();

            notificationHistoryPane = getNotificationHistoryPage();
            pages.add(notificationHistoryPane);

            NotificationHistory notificationHistory = new NotificationHistory();
            notificationPageController.setNotificationHistory(notificationHistory);
            notificationController.setNotificationHistory(notificationHistory);
            notificationController.getNotificationPageController(notificationPageController);

            mainPage.getChildren().addAll(homePane, settingsPane, recommendationsPane, appTutorialPage, settingsPopUpPane, energyPane, systemSettingsPane, energyUsagePane, energyPriceChartPane, notificationHistoryPane, notificationPane);
            notificationController.setupKeyHandling(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setAppTutorialVisible(boolean visible) {
        appTutorialPage.setVisible(visible);
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
        primaryStage.setTitle("Energy Insights");
        primaryStage.setScene(scene);
    }

    public static void openAppTutorial() {

        setAppTutorialVisible(true);
        primaryStage.setTitle("App Tutorial");
    }

    public static void switchToSystemSettings(){
        setPageVisible(systemSettingsPane);
        primaryStage.setTitle("System Settings");
        primaryStage.setScene(scene);
    }

    public static void switchToEnergyUsagePane() {
        setPageVisible(energyUsagePane); 
        primaryStage.setTitle("Energy Insights");
        primaryStage.setScene(scene);
    }

    public static void switchToEnergyPriceChartPane() {
        setPageVisible(energyPriceChartPane);
        primaryStage.setTitle("Detailed Energy overview");
        primaryStage.setScene(scene);
    }


    public static void switchToNotificationPage() {
        setPageVisible(notificationHistoryPane);
        primaryStage.setTitle("Notification History");
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

    private static AnchorPane getRecommendationsPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/RecommendationsPage.fxml"));
        return loader.load();
    }

    private static AnchorPane getSettingsPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/advanced_settings_page.fxml"));
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

    private static AnchorPane getEnergyUsagePage() throws IOException {
        FXMLLoader energyUsagePage = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/EnergyUsagePageController.fxml"));
        return energyUsagePage.load();
    }

    private static AnchorPane getEnergyPriceChartPage() throws IOException {
        FXMLLoader energyPriceChartPage = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/EnergyPriceChartPageController.fxml"));
        return energyPriceChartPage.load();
    }

    private static AnchorPane getNotificationHistoryPage() throws IOException {
        FXMLLoader notificationPage = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/Notification_page.fxml"));
        AnchorPane notificationHistoryPane = notificationPage.load();
        notificationPageController = notificationPage.getController();
        return notificationHistoryPane;
    }
}

package org.team6.view;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import org.team6.controller.NotificationController;
import org.team6.controller.NotificationPageController;
import org.team6.model.NotificationBackend;
import org.team6.model.NotificationHistory;

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
    private static AnchorPane appTutorialPage;

    private static AnchorPane systemSettingsPane;

    private static AnchorPane energyPage2;

    private static AnchorPane energyPage3;

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


            energyInsightsPane = getEnergyInsightsPage();
            pages.add(energyInsightsPane);

            recommendationsPane = getRecommendationsPage();
            pages.add(recommendationsPane);

            settingsPane = getSettingsPage();
            pages.add(settingsPane);

            appTutorialPage = getAppTutorialPage();

            energyPane = getEnergyPane();
            pages.add(energyPane);

            systemSettingsPane = getSystemSettingsPage();
            pages.add(systemSettingsPane);

            energyPage2 = getEnergyPage2();
            pages.add(energyPage2);

            energyPage3 = getEnergyPage3();
            pages.add(energyPage3);

            AnchorPane settingsPopUpPane = getSettingsPopUpPage();


            notificationHistoryPane = getNotificationHistoryPage();
            pages.add(notificationHistoryPane);

            NotificationHistory notificationHistory = new NotificationHistory();
            notificationPageController.setNotificationHistory(notificationHistory);
            notificationController.setNotificationHistory(notificationHistory);
            notificationController.getNotificationPageController(notificationPageController);

            mainPage.getChildren().addAll(homePane, settingsPane, energyInsightsPane, recommendationsPane,appTutorialPage, settingsPopUpPane, energyPane,systemSettingsPane,energyPage2, energyPage3, notificationHistoryPane, notificationPane);
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

    public static void switchToEnergyPage2(){
        setPageVisible(energyPage2);
        primaryStage.setTitle("Energy Insights");
        primaryStage.setScene(scene);
    }

    public static void switchToEnergyPage3(){
        setPageVisible(energyPage3);
        primaryStage.setTitle("Detailed Energy overview");
        primaryStage.setScene(scene);
    }


    public static void switchToNotificationPage(){
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

    private static AnchorPane getEnergyInsightsPage() throws IOException {
        FXMLLoader loader = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/EnergyInsightsPage.fxml"));
        return loader.load();
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

    private static AnchorPane getEnergyPage2() throws IOException {
        FXMLLoader energyPage2 = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/EnergyUsagePageController.fxml"));
        return energyPage2.load();
    }

    private static AnchorPane getEnergyPage3() throws IOException {
        FXMLLoader energyPage3 = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/EnergyPriceChartPageController.fxml"));
        return energyPage3.load();
    }




    private static AnchorPane getNotificationHistoryPage() throws IOException {
        FXMLLoader notificationPage = new FXMLLoader(PageStarter.class.getResource("/org/team6/view/Notification_page.fxml"));
        AnchorPane notificationHistoryPane = notificationPage.load();
        notificationPageController = notificationPage.getController();
        return notificationHistoryPane;
    }

}

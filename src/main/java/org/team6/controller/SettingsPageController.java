package org.team6.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import org.team6.model.*;
import org.team6.view.PageStarter;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class SettingsPageController implements Initializable, Observer {
    @FXML
    private ToggleButton sendNotificationsToggleButton;
    @FXML
    private ToggleButton sendLowElectricPriceToggleButton;
    @FXML
    private ToggleButton sendHighElectricPriceToggleButton;
    @FXML
    private ToggleButton sendSunnyWeatherToggleButton;
    @FXML
    private ToggleButton sendColdWeatherToggleButton;
    @FXML
    private ToggleButton sendDailyReportToggleButton;
    @FXML
    private Slider volumeSlider;

    @FXML
    private Spinner<Integer> dailyReportTimeSpinner;
    @FXML
    private Spinner<Double> electricityPriceLimitSpinner;
    @FXML
    private Spinner<Integer> startNotificationTimeSpinner;
    @FXML
    private Spinner<Integer> endNotificationTimeSpinner;

    @FXML
    private ToggleButton recommendationsToggleButton;
    @FXML
    private Button appTutorial;

    @FXML
    private Button backButton;
    @FXML
    private AnchorPane settingsPagePane;

    // Nicer to show the user a scale from 0 to 100 rather than 0 to 1.
    private static final int VOLUME_SCALE_FACTOR = 100;

    // Map of specific notification buttons. For instance used for
    // enabling and disabling all buttons if notifications are switched on or off.
    private final Map<ToggleButton, Notification> notificationButtons = new HashMap<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RecommendationsBackend.addObserver(this);

        notificationButtons.put(sendLowElectricPriceToggleButton, Notification.LOW_ELECTRICITY_PRICE);
        notificationButtons.put(sendHighElectricPriceToggleButton, Notification.HIGH_ELECTRICITY_PRICE);
        notificationButtons.put(sendSunnyWeatherToggleButton, Notification.SUNNY_WEATHER);
        notificationButtons.put(sendColdWeatherToggleButton, Notification.COLD_WEATHER);
        notificationButtons.put(sendDailyReportToggleButton, Notification.DAILY_REPORT);

        initVolumeSlider();
        initToggleButtons();
        initSpinners();
    }

    @Override
    public void update() {
        recommendationsToggleButton.setSelected(RecommendationsBackend.isPersonalRecommendationsOn());
    }

    @FXML
    private void handleRecommendationsToggleButtonAction() {
        RecommendationsBackend.setPersonalRecommendationsOn(!RecommendationsBackend.isPersonalRecommendationsOn());
    }

    private void initVolumeSlider() {
        volumeSlider.setValue(NotificationBackend.getVolume()*VOLUME_SCALE_FACTOR);
        // Don't think you can add it directly in Scenebuilder.
        volumeSlider.valueProperty().addListener((observable, oldValue, newValue) -> {
            // If double is not the supported type in the model, it can easily be changed here I think.
            handleVolumeChanged(newValue.doubleValue());
        });
    }

    private void initToggleButtons() {
        sendNotificationsToggleButton.setSelected(NotificationBackend.areNotificationsOn());
        recommendationsToggleButton.setSelected(RecommendationsBackend.isPersonalRecommendationsOn());
        initToggleButtonsState();
        toggleNotificationButtonsAvailability();
    }

    private void initSpinners() {
        initSpinnerValueFactories();
        initDefaultSpinnerValues();
        initSpinnerHandlers();
    }

    private void initSpinnerValueFactories() {
        dailyReportTimeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
        electricityPriceLimitSpinner.setValueFactory(new SpinnerValueFactory.DoubleSpinnerValueFactory(-999.0, 999.0));
        startNotificationTimeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
        endNotificationTimeSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23));
    }

    private void initDefaultSpinnerValues() {
        int dailyReportTime = NotificationBackend.getDailyReportTime();
        dailyReportTimeSpinner.getValueFactory().setValue(dailyReportTime);

        double electricityPriceLimit = NotificationBackend.getElectricityPriceLimit();
        electricityPriceLimitSpinner.getValueFactory().setValue(electricityPriceLimit);

        int startNotificationTime = NotificationBackend.getStartNotificationTime();
        startNotificationTimeSpinner.getValueFactory().setValue(startNotificationTime);

        int endNotificationTime = NotificationBackend.getEndNotificationTime();
        endNotificationTimeSpinner.getValueFactory().setValue(endNotificationTime);
    }

    private void initSpinnerHandlers() {
        dailyReportTimeSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            handleDailyReportTimeChanged(newValue);
        });
        electricityPriceLimitSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            handleElectricityPriceLimitChanged(newValue);
        });
        startNotificationTimeSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            handleStartNotificationTimeChanged(newValue);
        });
        endNotificationTimeSpinner.valueProperty().addListener((observable, oldValue, newValue) -> {
            handleEndNotificationTimeChanged(newValue);
        });
    }

    private void initToggleButtonsState() {
        for (Map.Entry<ToggleButton, Notification> notificationEntry : notificationButtons.entrySet()) {
            ToggleButton toggleButton = notificationEntry.getKey();
            Notification notification = notificationEntry.getValue();
            toggleButton.setSelected(NotificationBackend.isNotificationOn(notification));
        }
    }

    private void toggleNotificationButtonsAvailability() {
        boolean isSelected = sendNotificationsToggleButton.isSelected();

        for (ToggleButton notificationButton : notificationButtons.keySet()) {
            notificationButton.setDisable(!isSelected);
        }
    }

    // Meant for toggling notifications in general.
    @FXML
    private void handleSendNotificationsOnAction() {
        NotificationBackend.toggleAllNotifications();
        toggleNotificationButtonsAvailability();
    }

    @FXML
    private void handleSpecificNotificationOnAction(ActionEvent event) {
        ToggleButton sourceButton = (ToggleButton) event.getSource();

        if (notificationButtons.containsKey(sourceButton)) {
            Notification notificationToBeToggled = notificationButtons.get(sourceButton);
            NotificationBackend.toggleASpecificNotification(notificationToBeToggled);
        } else {
            System.err.println("Event button" + sourceButton + "does not exist in Map!");
        }
    }

    @FXML
    private void handleBackOnAction() {
        PageStarter.switchToHomePage();
    }

    @FXML
    private void handleEnergyButtonAction(){
        PageStarter.switchToEnergyPage();
    }

    private void handleVolumeChanged(double newVolume) {
        NotificationBackend.setVolume(newVolume/VOLUME_SCALE_FACTOR);
    }

    private void handleDailyReportTimeChanged(int newTime) {
        NotificationBackend.setDailyReportTime(newTime);
    }

    private void handleElectricityPriceLimitChanged(double newLimit) {
        NotificationBackend.setElectricityPriceLimit(newLimit);
    }

    private void handleStartNotificationTimeChanged(int newTime) {
        NotificationBackend.setStartNotificationTime(newTime);
    }

    private void handleEndNotificationTimeChanged(int newTime) {
        NotificationBackend.setEndNotificationTime(newTime);
    }

    public void handleAppTutorialButton(ActionEvent event) {
        PageStarter.openAppTutorial();
    }
}

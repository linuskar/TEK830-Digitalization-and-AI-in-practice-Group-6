package org.team6.controller;

import org.team6.view.PageStarter;

import javafx.fxml.FXML;

public class EnergyPriceChartPageController {
    @FXML
    private void handleHomePageButtonOnAction() {
        PageStarter.switchToHomePage();
    }

    @FXML
    private void handleEnergyButtonOnAction() {
        PageStarter.switchToEnergyPage();
    }

    @FXML
    private void handleSystemSettingsButton() {
        PageStarter.switchToSystemSettings();
    }

    @FXML
    private void handleEnergyPage3Button() {
        PageStarter.switchToEnergyPriceChartPane();
    }
}

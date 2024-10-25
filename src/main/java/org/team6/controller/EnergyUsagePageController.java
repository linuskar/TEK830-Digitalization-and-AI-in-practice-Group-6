package org.team6.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

import org.team6.view.PageStarter;

public class EnergyUsagePageController {

    @FXML
    private void handleHomePageButtonOnAction(){
        PageStarter.switchToHomePage();
    }


    @FXML
    private void handleEnergyButtonOnAction(){
        PageStarter.switchToEnergyPage();
    }

    @FXML
    private void handleSystemSettingsButton(){
    PageStarter.switchToSystemSettings();
    }

    @FXML
    private void handleEnergyPage3Button(){
    PageStarter.switchToEnergyPage3();
    }

    @FXML
    private void handleRecommendationsButtonAction() {
        PageStarter.switchToRecommendationsPage();
    }
}

package org.team6.controller;

import org.team6.view.PageStarter;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;

public class RecommendationsPageController {
    @FXML
    private Button homeButton;

    @FXML
    private Button energyInsightsButton;

    @FXML
    private void handleHomeButtonAction() {
        PageStarter.switchToHomePage();
    }

    @FXML
    private void handleEnergyInsightsButtonAction() {
        PageStarter.switchToEnergyInsightsPage();
    }
}

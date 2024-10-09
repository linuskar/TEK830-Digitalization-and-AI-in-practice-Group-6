package org.team6.controller;

import org.team6.view.PageStarter;

import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class RecommendationsPageController {
    @FXML
    private Button homeButton;

    @FXML
    private Button energyInsightsButton;

    @FXML
    private void handleHomeButtonAction() {
        Stage currentStage = (Stage) homeButton.getScene().getWindow();
        PageStarter.switchToHomePage(currentStage);
    }

    @FXML
    private void handleHomeButtonMouseEntered() {
        homeButton.setCursor(Cursor.HAND);
    }

    @FXML
    private void handleHomeButtonMouseExited() {
        homeButton.setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void handleEnergyInsightsButtonAction() {
        Stage currentStage = (Stage) energyInsightsButton.getScene().getWindow();
        PageStarter.switchToEnergyInsightsPage(currentStage);
    }
}

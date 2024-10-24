package org.team6.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import org.team6.view.PageStarter;

public class EnergyInsightsPageController {
    @FXML
    private Button homeButton;

    @FXML
    private Button recommendationsButton;

    @FXML
    private void handleHomeButtonAction() {
        PageStarter.switchToHomePage();
    }

    @FXML
    private void handleRecommendationsButtonAction() {
        PageStarter.switchToRecommendationsPage();
    }
}

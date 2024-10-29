package org.team6.controller;

import org.team6.model.recommendations.RecommendationsBackend;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class SettingsPopUpController {
    @FXML
    private AnchorPane popUpPane;
    
    private void hidePopUp() {
        popUpPane.setVisible(false);
        popUpPane.setDisable(true);
    }

    @FXML
    private void handleAllowRecommendations() {
        RecommendationsBackend.setPersonalRecommendationsOn(true);
        hidePopUp();
    }

    @FXML
    private void handleRejectRecommendations() {
        RecommendationsBackend.setPersonalRecommendationsOn(false);
        hidePopUp();
    }
}
package org.team6.controller;

import org.team6.model.RecommendationsBackend;

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
    private void handelAllowProductRecommendation() {
        RecommendationsBackend.setPersonalRecommendationsOn(true);
        // NotificationBackend.allowProductRecommendation(true);
        hidePopUp();
    }

    @FXML
    private void handelRejectProductRecommendation() {
        RecommendationsBackend.setPersonalRecommendationsOn(false);
        hidePopUp();
    }
}

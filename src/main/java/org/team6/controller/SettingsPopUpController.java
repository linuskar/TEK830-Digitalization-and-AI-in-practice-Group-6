package org.team6.controller;

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
        // NotificationBackend.allowProductRecommendation(true);
        hidePopUp();
    }

    @FXML
    private void handelRejectProductRecommendation() {
        // NotificationBackend.allowProductRecommendation(false);
        hidePopUp();
    }
}

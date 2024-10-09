package org.team6.controller;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class SettingsPopUpController {
    @FXML
    private AnchorPane popUpPane;

    @FXML
    private void hidePopUp() {
        popUpPane.setVisible(false);
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

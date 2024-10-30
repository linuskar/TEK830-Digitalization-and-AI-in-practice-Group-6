package org.team6.controller;

import org.team6.view.PageStarter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class EnergyPageController {
    @FXML
    private AnchorPane energyPageAnchor;

    @FXML
    private Button energyOverviewButton;

    @FXML
    private Pane readMorePane;

    @FXML
    private Button readMoreEnergyButton;

    @FXML
    private void handleSettingsButtonAction() {
        PageStarter.switchToSystemSettings();
    }

    @FXML
    private void handleHomeButton() {
        PageStarter.switchToHomePage();
    }

    @FXML
    private void  handleEnergyOverviewOnAction() {
        PageStarter.switchToEnergyUsagePane();
    }
}

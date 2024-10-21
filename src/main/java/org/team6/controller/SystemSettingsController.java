package org.team6.controller;

import javafx.fxml.FXML;
import org.team6.view.PageStarter;

public class SystemSettingsController {

    @FXML
    private void handleHomeButtonOnAction(){
        PageStarter.switchToHomePage();
    }

    @FXML
    private void handleEnergyPageButtonOnAction(){
        PageStarter.switchToEnergyPage();
    }

    @FXML
    private void handleSystemSettingsButtonOnAction(){
        PageStarter.switchToSystemSettings();
    }

    @FXML
    private void handleAdvancedSettingsButtonOnAction(){
        PageStarter.switchToSettingsPage();
    }





}

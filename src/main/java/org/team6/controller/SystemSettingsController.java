package org.team6.controller;

import org.team6.model.UrlOpener;
import org.team6.view.PageStarter;

import javafx.fxml.FXML;

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
        PageStarter.switchToAdvancedSettingsPage();
    }

    @FXML
    private void handleHelpAndSupportButtonOnAction(){
        UrlOpener.openUrl("https://www.ikea.com/se/en/customer-service/");
    }
}

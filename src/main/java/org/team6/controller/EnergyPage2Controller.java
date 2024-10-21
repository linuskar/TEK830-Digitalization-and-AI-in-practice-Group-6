package org.team6.controller;

import javafx.fxml.FXML;
import org.h2.mvstore.Page;
import org.team6.view.PageStarter;

public class EnergyPage2Controller {

    @FXML
    private void handleHomePageButtonOnAction(){
        PageStarter.switchToHomePage();
    }


    @FXML
    private void handleEnergyButtonOnAction(){
        PageStarter.switchToEnergyPage();
    }

    @FXML
    private void handleSystemSettingsButton(){
    PageStarter.switchToSystemSettings();

    }


}

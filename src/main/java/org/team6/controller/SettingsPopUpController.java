package org.team6.controller;

import java.net.URL;
import java.util.ResourceBundle;

import org.team6.model.NotificationBackend;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

public class SettingsPopUpController implements Initializable {
    @FXML
    private AnchorPane popUpPane;
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        popUpPane.setVisible(true);
    }

    @FXML
    private void hidePopUp (){
        popUpPane.setVisible(false);
    }

    @FXML
    private void handelAllowProductRecommendation (){
        //NotificationBackend.allowProductRecommendation(true);
        hidePopUp();
    }

    @FXML
    private void handelRejectProductRecommendation (){
        //NotificationBackend.allowProductRecommendation(false);
        hidePopUp();
        
    }


}

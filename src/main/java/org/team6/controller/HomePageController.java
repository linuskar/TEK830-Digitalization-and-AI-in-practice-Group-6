package org.team6.controller;

import org.team6.model.UrlOpener;
import org.team6.view.PageStarter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class HomePageController {
    @FXML
    private AnchorPane mainPage;

    @FXML
    private Text homePageTitle;

    @FXML
    private ImageView settingsImage;

    @FXML
    private ImageView accountImage;

    @FXML
    private ImageView menuImage1;

    @FXML
    private Pane barPane;

    @FXML
    private Button settingsButton;

    @FXML
    private Button energyButton;

    @FXML
    private Button homeButton;

    @FXML
    private Button searchButton;

    @FXML
    private BorderPane topBar;

    @FXML
    private ImageView frontPageImage;

    @FXML
    private Button electricityButton;

    @FXML
    private Button productTutorialButton;

    @FXML
    private Text electricityText;
    
    @FXML
    private Label tipsLabel;

    @FXML
    private Pane informationPane;

    @FXML
    private Button readMoreButton;

    @FXML
    private void handleSettingsButtonAction() {
        PageStarter.switchToSystemSettings();
    }

    @FXML
    private void handleEnergyButtonAction(){
        PageStarter.switchToEnergyPage();
    }

    @FXML
    private void handleProductTutorialButtonAction(){
        UrlOpener.openUrl("https://www.ikea.com/us/en/product-guides/ikea-home-smart-system/");
    }
}

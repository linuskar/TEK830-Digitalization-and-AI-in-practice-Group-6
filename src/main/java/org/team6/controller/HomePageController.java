package org.team6.controller;

import org.team6.view.PageStarter;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.scene.Cursor;

public class HomePageController {
    @FXML
    private AnchorPane mainPage;

    @FXML
    private Text homePageTitle;

    @FXML
    private javafx.scene.image.ImageView settingsImage;

    @FXML
    private javafx.scene.image.ImageView accountImage;

    @FXML
    private javafx.scene.image.ImageView menuImage1;

    @FXML
    private Pane barPane;

    @FXML
    private Button settingsButton;

    @FXML
    private Button accountButton;

    @FXML
    private Button searchButton;

    @FXML
    private BorderPane topBar;

    @FXML
    private Button menuButton1;

    @FXML
    private ImageView frontPageImage;

    @FXML
    private Button electricityButton;

    @FXML
    private Button tipsButton;

    @FXML
    private Text electricityText;

    @FXML
    private void handleSettingsButtonMouseEntered() {
        settingsButton.setCursor(Cursor.HAND);
    }

    @FXML
    private void handleSettingsButtonMouseExited() {
        settingsButton.setCursor(Cursor.DEFAULT);
    }

    @FXML
    private void handleSettingsButtonOnAction() {
        PageStarter.switchToSettingsPage();
    }
}

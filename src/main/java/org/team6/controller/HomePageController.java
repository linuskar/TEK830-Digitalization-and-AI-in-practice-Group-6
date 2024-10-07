package org.team6.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.team6.view.PageStarter;

import java.io.IOException;

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
    private void handleSettingsButtonAction() {
        Stage currentStage = (Stage) settingsButton.getScene().getWindow();
        try {
            PageStarter.switchToSettingsPage(currentStage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
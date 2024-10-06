package org.team6.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.team6.controller.SettingsPageController;
import org.team6.view.App;

import java.io.IOException;

public class HomePageController {
    @FXML
    private AnchorPane mainPage;

    //@FXML
    //private StackPane settingsStack;

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


    //pop up settings after pressing on the settings button.
    @FXML
    private void handleSettingsPageOnAction() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/settings_page.fxml"));
        Parent settingsPage = loader.load();
        mainPage.getChildren().add(settingsPage);
        settingsPage.toFront();
    }




}
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

    private NotificationController notificationController = new NotificationController();
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

    @FXML
    private Pane settingsPane1;

    @FXML
    private Parent settingsPane;

    @FXML
    private void initialize(){
        settingsPane1.setVisible(false);

    }

    @FXML
    public void setSettingsPane(Parent settingsPane){
        this.settingsPane = settingsPane;
    }

    //Displays settingsPane in mainePage.
    @FXML
    public void handleSettingsPageOnAction() {
        //Prevents duplication error, multiple of the same pane being added.
        if (!mainPage.getChildren().contains(settingsPane)) {
            mainPage.getChildren().add(settingsPane);
        }

        settingsPane.setVisible(true);
        settingsPane.toFront();

    }








}
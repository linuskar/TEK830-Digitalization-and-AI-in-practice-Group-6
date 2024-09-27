package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.awt.*;

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

   public void test(){
        if (menuImage1 == null) {
            System.out.println("settingsImage is not injected");
        } else {
            System.out.println("settingsImage is injected");
        }

    }

}

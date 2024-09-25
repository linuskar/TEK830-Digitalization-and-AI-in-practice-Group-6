package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import javax.swing.text.html.ImageView;
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
    private Pane barPane;

    @FXML
    private Button settingsButton;

    @FXML
    private Button accountButton;

    @FXML
    private Button searchButton;

    @FXML
    private Button menuButton;

    @FXML
    private BorderPane topBar;
}

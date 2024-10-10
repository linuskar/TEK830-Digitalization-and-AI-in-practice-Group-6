package org.team6.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AppTutorialController implements Initializable {
    @FXML
    private Label counterLabel;

    @FXML
    private ImageView tutorialImage;

    @FXML
    private Label infoText;

    @FXML
    private Button closeButton;

    @FXML
    private Button forwardButton;
    @FXML
    private Button backButton;

    private int currentPageNumber = 0;
    private final List<String> pageInfoList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCounterLabelText(currentPageNumber);
    }

    private void setCounterLabelText(int currentPageNumber) {
        int totalPageNumber = pageInfoList.size();
        counterLabel.setText(currentPageNumber + "/" + totalPageNumber);
    }
}

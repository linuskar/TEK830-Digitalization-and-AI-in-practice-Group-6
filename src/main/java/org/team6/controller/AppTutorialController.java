package org.team6.controller;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import org.team6.tutorial.TutorialPageItem;
import org.team6.tutorial.TutorialPageItemHandler;

import java.net.URL;
import java.util.ResourceBundle;

public class AppTutorialController implements Initializable {
    @FXML
    private AnchorPane windowPane;

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
    private final TutorialPageItemHandler tutorialPageItemHandler = new TutorialPageItemHandler();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        updatePageContent(currentPageNumber);
    }

    private void updatePageContent(int currentPageNumber) {
        setCounterLabelText(currentPageNumber);
        TutorialPageItem currentItem = tutorialPageItemHandler.getTutorialPageItemAt(currentPageNumber);
        setInfoText(currentItem.infoText());
        setInfoImage(currentItem.infoImage());
    }

    private void setInfoText(String textToSet) {
        infoText.setText(textToSet);
    }

    private void setInfoImage(Image image) {
        tutorialImage.setImage(image);
    }

    private void setCounterLabelText(int currentPageNumber) {
        int totalPageNumber = tutorialPageItemHandler.getTutorialPageItemCount();
        counterLabel.setText(currentPageNumber + "/" + totalPageNumber);
    }

    @FXML
    private void handleForwardOnAction() {
        currentPageNumber++;
        updateButtonState(currentPageNumber);
        updatePageContent(currentPageNumber);
    }

    @FXML
    private void handleBackOnAction() {
        currentPageNumber--;
        updateButtonState(currentPageNumber);
        updatePageContent(currentPageNumber);
    }

    private void updateButtonState(int currentPageNumber) {
        backButton.setDisable(currentPageNumber < 0);
        forwardButton.setDisable(currentPageNumber >= tutorialPageItemHandler.getTutorialPageItemCount());
    }

    @FXML
    private void handleCloseOnAction() {
        currentPageNumber = 0;
        updatePageContent(currentPageNumber);
        windowPane.setVisible(false);
        windowPane.setDisable(true);
    }
}

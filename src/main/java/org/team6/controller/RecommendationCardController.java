package org.team6.controller;

import org.team6.model.UrlOpener;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

public class RecommendationCardController {
    @FXML
    private AnchorPane cardBackground;
    @FXML
    private ImageView cardImage;
    @FXML
    private AnchorPane cardImageContainer;
    @FXML
    private Text cardTitle;
    @FXML
    private Text cardText;
    @FXML
    private AnchorPane cardTextContainer;
    // For products the button will redirect the user to website for the product
    @FXML
    private Button readMoreButton;

    private String readMoreURL;

    public void disableReadMoreButton() {
        readMoreButton.setVisible(false);
        readMoreButton.setDisable(true);
    }

    public void setCardTitle(String title) {
        cardTitle.setText(title);
    }

    public void setCardText(String text) {
        cardText.setText(text);
    }

    public void setCardImage(Image image) {
        cardImage.setImage(image);
    }

    public void setReadMoreURL(String url) {
        readMoreURL = url;
    }

    @FXML
    public void handleReadMoreButtonAction() {
        UrlOpener.openUrl(readMoreURL);
    }
}

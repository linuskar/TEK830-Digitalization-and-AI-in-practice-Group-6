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
import org.team6.view.ImageUtils;

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

    private double startWidth;
    private double startHeight;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        startWidth = tutorialImage.getFitWidth();
        startHeight = tutorialImage.getFitHeight();

        backButton.setDisable(true);
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
        // Reset the ImageView to its original dimensions before setting the new image
        setImageViewSize(tutorialImage, startWidth, startHeight);
        scaleImageViewAccordingToImage(tutorialImage, image, startWidth, startHeight);

        // Set the image and apply rounded corners
        tutorialImage.setImage(image);
        ImageUtils.makeCornersRounded(tutorialImage, 20);
    }

    private static void setImageViewSize(ImageView imageView, double width, double height) {
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
    }

    private static void scaleImageViewAccordingToImage(ImageView imageView, Image image, double startWidth, double startHeight) {
        double imageAspectRatio = image.getWidth() / image.getHeight();
        double viewAspectRatio = startWidth / startHeight;

        // Adjust the ImageView dimensions based on the image's aspect ratio
        if (imageAspectRatio > viewAspectRatio) {
            // If the image is wider relative to the ImageView, scale by width
            setImageViewSize(imageView, startWidth, startWidth / imageAspectRatio);
        } else {
            // If the image is taller relative to the ImageView, scale by height
            setImageViewSize(imageView, startHeight * imageAspectRatio, startHeight);
        }
    }

    private void setCounterLabelText(int currentPageNumber) {
        int totalPageNumber = tutorialPageItemHandler.getTutorialPageItemCount();
        int displayPageNumber = currentPageNumber + 1;
        counterLabel.setText(displayPageNumber + "/" + totalPageNumber);
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
        backButton.setDisable(currentPageNumber < 1);
        forwardButton.setDisable(currentPageNumber >= tutorialPageItemHandler.getTutorialPageItemCount() - 1);
    }

    @FXML
    private void handleCloseOnAction() {
        currentPageNumber = 0;
        updatePageContent(currentPageNumber);
        windowPane.setVisible(false);
        windowPane.setDisable(true);
    }
}

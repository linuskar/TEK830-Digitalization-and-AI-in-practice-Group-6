package org.team6.controller;

import java.io.IOException;

import org.team6.view.PageStarter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class RecommendationsPageController {
    @FXML
    private Button homeButton;
    @FXML
    private Button energyInsightsButton;

    @FXML
    private Button generalButton;

    @FXML
    private Button personalButton;

    @FXML
    private FlowPane generalRecommendationsFlowPane;
    @FXML
    private FlowPane personalRecommendationsFlowPane;

    @FXML
    private VBox generalProductRecommendationsVBox;

    @FXML
    private void initialize() {
        createRecommendationCard();
        createRecommendationCard();
        createRecommendationCard();
        createRecommendationCard();
    }
    
    @FXML
    private void handleHGeneralButtonAction() {
        generalRecommendationsFlowPane.setVisible(true);
        generalRecommendationsFlowPane.setDisable(false);
    
        personalRecommendationsFlowPane.setVisible(false);
        personalRecommendationsFlowPane.setDisable(true);
    }

    @FXML
    private void handleHPersonalButtonAction() {    
        generalRecommendationsFlowPane.setVisible(false);
        generalRecommendationsFlowPane.setDisable(true);

        personalRecommendationsFlowPane.setVisible(true);
        personalRecommendationsFlowPane.setDisable(false);
    }

    @FXML
    private void handleHomeButtonAction() {
        PageStarter.switchToHomePage();
    }

    @FXML
    private void handleEnergyInsightsButtonAction() {
        PageStarter.switchToEnergyInsightsPage();
    }

     private void createRecommendationCard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/RecommendationCard.fxml"));
            AnchorPane card = loader.load();

            /* 
            RecommendationCardController controller = loader.getController();
            controller.setTitle(title);
            controller.setDescription(description);
            controller.setImage(imagePath);
            controller.setActionButtonText("Learn More");
            */
            generalProductRecommendationsVBox.getChildren().add(card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package org.team6.controller;

import java.io.IOException;

import org.team6.view.PageStarter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class RecommendationsPageController {
    @FXML
    private Button homeButton;
    @FXML
    private Button energyInsightsButton;

    @FXML
    private ToggleGroup recommendationPrivacyTypeToggleButtonGroup;
    @FXML
    private ToggleButton generalToggleButton;
    @FXML
    private ToggleButton personalToggleButton;

    @FXML
    private ToggleGroup recommendationTypeToggleButtonGroup;
    @FXML
    private ToggleButton productsToggleButton;
    @FXML
    private ToggleButton tipsToggleButton;

    @FXML
    private AnchorPane recommendationsContentPane;

    // General
    @FXML
    private FlowPane generalRecommendationsFlowPane;
    @FXML
    private VBox generalProductRecommendationsVBox;
    @FXML
    private VBox generalTipsRecommendationsVBox;
    @FXML
    private FlowPane generalProductRecommendationsFlowPane;
    @FXML
    private FlowPane generalTipsRecommendationsFlowPane;

    // Personal
    @FXML
    private FlowPane personalRecommendationsFlowPane;
    @FXML
    private VBox personalProductRecommendationsVBox;
    @FXML
    private VBox personalTipsRecommendationsVBox;
    @FXML
    private FlowPane personalProductRecommendationsFlowPane;
    @FXML
    private FlowPane personalTipsRecommendationsFlowPane;

    @FXML
    private void initialize() {
        initializeRecommendations();

        personalRecommendationsFlowPane.setVisible(false);
        personalRecommendationsFlowPane.setDisable(true);
    }

    private void initializeRecommendations() {
        // Fetch recommendations from the backend
        // For each recommendation, create a card
        // Add the card to the appropriate flow pane

        // For now, we'll just create some dummy cards
        initializeGeneralRecommendations(2,1);
        initializePersonalRecommendations(3,2);
    }

    private void initializeGeneralRecommendations(int products, int tips) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/RecommendationContent.fxml"));
            generalRecommendationsFlowPane = loader.load();
            RecommendationsContentController controller = loader.getController();

            generalProductRecommendationsVBox = controller.getProductRecommendationsVBox();
            generalTipsRecommendationsVBox = controller.getTipsRecommendationsVBox();

            generalProductRecommendationsFlowPane = controller.getProductRecommendationsFlowPane();
            generalTipsRecommendationsFlowPane = controller.getTipsRecommendationsFlowPane();

            // Create and add recommendation cards
            for (int i = 0; i < products; i++) {
                createRecommendationCard(generalProductRecommendationsVBox);
            }

            for (int i = 0; i < tips; i++) {
                createRecommendationCard(generalTipsRecommendationsVBox);
            }

            recommendationsContentPane.getChildren().add(generalRecommendationsFlowPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // products and tips testing values for cards due to lack of backend
    private void initializePersonalRecommendations(int products, int tips) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/RecommendationContent.fxml"));
            personalRecommendationsFlowPane = loader.load();
            RecommendationsContentController controller = loader.getController();

            personalProductRecommendationsVBox = controller.getProductRecommendationsVBox();
            personalTipsRecommendationsVBox = controller.getTipsRecommendationsVBox();

            personalProductRecommendationsFlowPane = controller.getProductRecommendationsFlowPane();
            personalTipsRecommendationsFlowPane = controller.getTipsRecommendationsFlowPane();

            // Create and add recommendation cards
            for (int i = 0; i < products; i++) {
                createRecommendationCard(personalProductRecommendationsVBox);
            }

            for (int i = 0; i < tips; i++) {
                createRecommendationCard(personalTipsRecommendationsVBox);
            }

            recommendationsContentPane.getChildren().add(personalRecommendationsFlowPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleGeneralToggleButtonAction() {
        handleRecommendationPrivacyTypeToggle(generalToggleButton);
    }

    @FXML
    private void handlePersonalToggleButtonAction() {    
        handleRecommendationPrivacyTypeToggle(personalToggleButton);
    }

    private void handleRecommendationPrivacyTypeToggle(ToggleButton selectedToggle) {
        boolean isGeneralSelected = selectedToggle == generalToggleButton;
    
        generalRecommendationsFlowPane.setVisible(isGeneralSelected);
        generalRecommendationsFlowPane.setDisable(!isGeneralSelected);
    
        personalRecommendationsFlowPane.setVisible(!isGeneralSelected);
        personalRecommendationsFlowPane.setDisable(isGeneralSelected);
    
        handleContentToggle();
    }

    @FXML
    private void handleProductsToggleButtonAction() {
        handleContentToggle();
    }

    @FXML
    private void handleTipsToggleButtonAction() {
        handleContentToggle();
    }

    private void handleContentToggle() {
        ToggleButton selectedToggleRecommendationType = (ToggleButton) recommendationTypeToggleButtonGroup.getSelectedToggle();
        ToggleButton selectedToggleRecommendationPrivacyType = (ToggleButton) recommendationPrivacyTypeToggleButtonGroup.getSelectedToggle();

        boolean isGeneralSelected = selectedToggleRecommendationPrivacyType == generalToggleButton;
        boolean isProductsSelected = selectedToggleRecommendationType == productsToggleButton;

        // General
        generalProductRecommendationsFlowPane.setVisible(isGeneralSelected && isProductsSelected);
        generalProductRecommendationsFlowPane.setDisable(!(isGeneralSelected && isProductsSelected));

        generalTipsRecommendationsFlowPane.setVisible(isGeneralSelected && !isProductsSelected);
        generalTipsRecommendationsFlowPane.setDisable(!(isGeneralSelected && !isProductsSelected));

        // Personal
        personalProductRecommendationsFlowPane.setVisible(!isGeneralSelected && isProductsSelected);
        personalProductRecommendationsFlowPane.setVisible(!isGeneralSelected && isProductsSelected);

        personalTipsRecommendationsFlowPane.setVisible(!isGeneralSelected && !isProductsSelected);
        personalTipsRecommendationsFlowPane.setDisable(!(!isGeneralSelected && !isProductsSelected));
    }

    @FXML
    private void handleHomeButtonAction() {
        PageStarter.switchToHomePage();
    }

    @FXML
    private void handleEnergyInsightsButtonAction() {
        PageStarter.switchToEnergyInsightsPage();
    }

     private void createRecommendationCard(VBox recommendationVBox) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/RecommendationCard.fxml"));
            AnchorPane card = loader.load();

            recommendationVBox.getChildren().add(card);

            if(recommendationVBox == personalTipsRecommendationsVBox){
                System.out.println("Personal tips cards added");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
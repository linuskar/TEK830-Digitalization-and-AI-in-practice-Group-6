package org.team6.controller;

import java.io.IOException;

import org.team6.model.Recommendations.ProductRecommendation;
import org.team6.model.Recommendations.RecommendationObserver;
import org.team6.model.Recommendations.RecommendationsBackend;
import org.team6.model.Recommendations.TipRecommendation;
import org.team6.view.PageStarter;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class RecommendationsPageController implements RecommendationObserver {
    @FXML
    private Button homeButton;
    @FXML
    private Button energyInsightsButton;
    @FXML
    private ToggleButton recommendationsToggleButton;

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
        RecommendationsBackend.addObserver(this);
        initializeRecommendations();
        initializeToggleGroups();
        initializeToggleButtons();

        personalRecommendationsFlowPane.setVisible(false);
        personalRecommendationsFlowPane.setDisable(true);
    }

    @Override
    public void update() {
        recommendationsToggleButton.setSelected(RecommendationsBackend.isPersonalRecommendationsOn());
        personalToggleButton.setDisable(!RecommendationsBackend.isPersonalRecommendationsOn());
    }

    private void initializeRecommendations() {
        // Fetch recommendations from the backend
        // For each recommendation, create a card
        // Add the card to the appropriate flow pane

        initializeGeneralRecommendations();
        initializePersonalRecommendations();
    }

    private void initializeToggleGroups() {
        // Prevents already selected toggle button to not be unselected when pressing it again in the toggle group
        recommendationTypeToggleButtonGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle == null) {
                recommendationTypeToggleButtonGroup.selectToggle(oldToggle);
            }
        });

        recommendationPrivacyTypeToggleButtonGroup.selectedToggleProperty().addListener((observable, oldToggle, newToggle) -> {
            if (newToggle == null) {
                recommendationPrivacyTypeToggleButtonGroup.selectToggle(oldToggle);
            }
        });
    }

    private void initializeToggleButtons() {
        recommendationsToggleButton.setSelected(RecommendationsBackend.isPersonalRecommendationsOn());
        personalToggleButton.setDisable(!RecommendationsBackend.isPersonalRecommendationsOn());
    }

    private void initializeGeneralRecommendations() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/RecommendationContent.fxml"));
            generalRecommendationsFlowPane = loader.load();
            RecommendationsContentController controller = loader.getController();

            generalProductRecommendationsVBox = controller.getProductRecommendationsVBox();
            generalTipsRecommendationsVBox = controller.getTipsRecommendationsVBox();

            generalProductRecommendationsFlowPane = controller.getProductRecommendationsFlowPane();
            generalTipsRecommendationsFlowPane = controller.getTipsRecommendationsFlowPane();

            // Create and add recommendation cards
            for (ProductRecommendation recommendation : RecommendationsBackend.getGeneralProductRecommendations()) {
                createProductRecommendationCard(generalProductRecommendationsVBox, recommendation);
            }

            for (TipRecommendation recommendation : RecommendationsBackend.getGeneralTipsRecommendations()) {
                createTipRecommendationCard(generalTipsRecommendationsVBox, recommendation);
            }

            recommendationsContentPane.getChildren().add(generalRecommendationsFlowPane);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initializePersonalRecommendations() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/RecommendationContent.fxml"));
            personalRecommendationsFlowPane = loader.load();
            RecommendationsContentController controller = loader.getController();

            personalProductRecommendationsVBox = controller.getProductRecommendationsVBox();
            personalTipsRecommendationsVBox = controller.getTipsRecommendationsVBox();

            personalProductRecommendationsFlowPane = controller.getProductRecommendationsFlowPane();
            personalTipsRecommendationsFlowPane = controller.getTipsRecommendationsFlowPane();

            // Create and add recommendation cards
            for (ProductRecommendation recommendation : RecommendationsBackend.getPersonalProductRecommendations()) {
                createProductRecommendationCard(personalProductRecommendationsVBox, recommendation);
                
            }

            for (TipRecommendation recommendation : RecommendationsBackend.getPersonalTipsRecommendations()) {
                createTipRecommendationCard(personalTipsRecommendationsVBox, recommendation);
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

    @FXML
    private void handleRecommendationsToggleButtonAction() {
        RecommendationsBackend.setPersonalRecommendationsOn(!RecommendationsBackend.isPersonalRecommendationsOn());
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
    private void handleSettingsButtonAction() {
        PageStarter.switchToSettingsPage();
    }

    @FXML
    private void handleEnergyButtonAction(){
        PageStarter.switchToEnergyPage();
    }

    @FXML
    private void handleEnergyInsightsButtonAction() {
        PageStarter.switchToEnergyInsightsPage();
    }

    private void createProductRecommendationCard(VBox recommendationVBox, ProductRecommendation recommendation) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/RecommendationCard.fxml"));
            AnchorPane card = loader.load();

        RecommendationCardController controller = loader.getController();

        controller.setCardTitle(recommendation.getTitle());
        controller.setCardText(recommendation.getText());
        String imageUrl = recommendation.getRecommendationImage();

        Image image = new Image(getClass().getResourceAsStream(imageUrl));
        controller.setCardImage(image);
        controller.setCardText(recommendation.getText());

        controller.setReadMoreURL(recommendation.getReadMoreUrl());

            recommendationVBox.getChildren().add(card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void createTipRecommendationCard(VBox recommendationVBox) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/RecommendationCard.fxml"));
            AnchorPane card = loader.load();

        RecommendationCardController controller = loader.getController();

        controller.disableReadMoreButton();
    
            recommendationVBox.getChildren().add(card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createTipRecommendationCard(VBox recommendationVBox, TipRecommendation recommendation) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/org/team6/view/RecommendationCard.fxml"));
            AnchorPane card = loader.load();

        RecommendationCardController controller = loader.getController();
        
        controller.disableReadMoreButton();

        controller.setCardTitle(recommendation.getTitle());
        controller.setCardText(recommendation.getText());
        String imageUrl = recommendation.getRecommendationImage();

        Image image = new Image(getClass().getResourceAsStream(imageUrl));
        controller.setCardImage(image);
        controller.setCardText(recommendation.getText());

        // controller.setReadMoreURL(recommendation.getReadMoreUrl());

            recommendationVBox.getChildren().add(card);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
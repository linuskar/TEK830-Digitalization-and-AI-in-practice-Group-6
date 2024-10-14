package org.team6.controller;

import javafx.scene.control.ScrollPane;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;

public class RecommendationsContentController {
    @FXML
    private FlowPane recommendationsFlowPane;

    @FXML
    private AnchorPane recommendationsAnchorPane;

    @FXML
    private ScrollPane recommendationsScrollPane;

    @FXML
    private FlowPane productRecommendationsFlowPane;

    @FXML
    private FlowPane tipsRecommendationsFlowPane;

    @FXML
    private VBox productRecommendationsVBox;

    @FXML
    private VBox tipsRecommendationsVBox;



    public void hideProductsRecommendations() {
        productRecommendationsFlowPane.setVisible(false);
        productRecommendationsVBox.setVisible(false);
    }

    public void showProductsRecommendations() {
        productRecommendationsFlowPane.setVisible(true);
        productRecommendationsVBox.setVisible(true);
    }

    public void hideTipsRecommendations() {
        tipsRecommendationsFlowPane.setVisible(false);
        tipsRecommendationsVBox.setVisible(false);
    }

    public void showTipsRecommendations() {
        tipsRecommendationsFlowPane.setVisible(true);
        tipsRecommendationsVBox.setVisible(true);
    }

    public VBox getProductRecommendationsVBox() {
        return productRecommendationsVBox;
    }

    public VBox getTipsRecommendationsVBox() {
        return tipsRecommendationsVBox;
    }

    public FlowPane getProductRecommendationsFlowPane() {
        return productRecommendationsFlowPane;
    }

    public FlowPane getTipsRecommendationsFlowPane() {
        return tipsRecommendationsFlowPane;
    }
}

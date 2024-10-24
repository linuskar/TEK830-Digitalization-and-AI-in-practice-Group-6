package org.team6.view;

import org.team6.model.recommendations.RecommendationsBackend;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        RecommendationsBackend.initialize();
        PageStarter.initialize(primaryStage);
        PageStarter.switchToHomePage();
        primaryStage.show();
    }

    public void startView(String[] args) {
        launch(args);
    }
}

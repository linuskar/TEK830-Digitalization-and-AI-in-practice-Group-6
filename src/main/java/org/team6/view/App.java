package org.team6.view;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        PageStarter.initialize(primaryStage);
        PageStarter.switchToHomePage();
        primaryStage.show();
    }

    public void startView(String[] args) {
        launch(args);
    }
}

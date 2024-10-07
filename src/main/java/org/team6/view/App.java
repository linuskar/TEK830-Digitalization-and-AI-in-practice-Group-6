package org.team6.view;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {
    @Override
    public void start(Stage primaryStage) {
        PageStarter.switchToHomePage(primaryStage);
        primaryStage.show();
    }

    public void startView(String[] args) {
        launch(args);
    }
}

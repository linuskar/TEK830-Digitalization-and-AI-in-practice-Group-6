package org.team6.view;

import javafx.application.Application;
import javafx.stage.Stage;
import org.team6.model.NotificationBackend;
import org.team6.model.User;

public class App extends Application {
    private User user;
    private NotificationBackend backend;

    @Override
    public void start(Stage primaryStage) {
        PageStarter.setBackend(backend);
        PageStarter.initialize(primaryStage);
        PageStarter.switchToHomePage();
        primaryStage.show();
    }

    public void startView(String[] args) {
        launch(args);
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBackend(NotificationBackend backend) {
        this.backend = backend;
    }
}

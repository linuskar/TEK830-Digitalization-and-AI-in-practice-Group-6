package org.team6;
import org.team6.model.NotificationBackend;
import org.team6.model.User;
import org.team6.view.App;

public class Main {
    public static void main(String[] args) {
        User user = new User("a", "b", "c", "d");
        NotificationBackend.setUser(user);
        App app = new App();
        app.startView(args);
    }
}

package org.team6;
import java.sql.SQLException;

import org.team6.database.DatabaseConnection;
import org.team6.model.NotificationBackend;
import org.team6.model.User;
import org.team6.soundsystem.SoundHandler;
import org.team6.view.App;

// NOTE: We do not use an actual database in this project, instead we use mock data for the products and energy spenders
// This is due to deadlines, time constraints for the project
// But we had plans for it so, there is reason to why foundations for a database is in the code

public class Main {
    public static void main(String[] args) throws SQLException {
        try{
            DatabaseConnection database = new DatabaseConnection();

            User user = new User("a", "b", "c", "d");
            NotificationBackend.setUser(user);
            SoundHandler soundHandler = new SoundHandler();
            NotificationBackend.addNotificationListener(soundHandler);
            App app = new App();
            app.startView(args);

            database.resetDatabase();
            database.closeConnection();
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR!\nYou do not have the H2 JDBC driver (e.g. h2-2.3.232.jar) in your runtime classpath!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

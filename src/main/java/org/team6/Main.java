package org.team6;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.team6.Database.DatabaseConnection;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/org/team6/MainPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        primaryStage.setTitle("Homepage");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);

        // right now this is test code to test the database connection in main (runs after app is closed)
        /* 
        try{
            DatabaseConnection c = new DatabaseConnection();
            c.registerProduct("test");
            c.registerProduct("test2");
            c.registerProduct("test3");
            c.registerProduct("test4");
            c.registerProduct("ikea lampa");
            c.registerProduct("ikea lampa2");

            String products = c.getProducts();
            System.out.println("Products in database: " + products);
            
            // To reset the database run the method once
            //c.resetDatabase();

            c.closeConnection();
    
        } catch (ClassNotFoundException e) {
            System.err.println("ERROR!\nYou do not have the H2 JDBC driver (e.g. h2-2.3.232.jar) in your runtime classpath!");
        } catch (Exception e) {
            e.printStackTrace();
        }   
            */   
    }
}
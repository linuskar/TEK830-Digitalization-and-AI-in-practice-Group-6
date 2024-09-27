package org.team6.Database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseTestSetup {
    private static final String H2_URL = "jdbc:h2:~/TEK830_Group6";  // Connect to default 'postgres' database
    private static final String USER = "sa";  // user  // PostgreSQL username
    private static final String PASSWORD = ""; // password  // PostgreSQL password
    private static final String SETUPSQLFILEPATH = "src\\main\\sql\\setup.sql";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(H2_URL, USER, PASSWORD)) {
            if (conn != null) {
                System.out.println("Connected to H2 server successfully.");
                runSetupSQL(conn, SETUPSQLFILEPATH);
            }
        } catch (Exception e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    //Run setup SQL commands
    private static void runSetupSQL(Connection conn, String sqlFilePath) {
        try {
            // Read the SQL file as a string
            String sql = new String(Files.readAllBytes(Paths.get(sqlFilePath)));
            
            // Execute the SQL statements
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                System.out.println("SQL setup from file executed successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error running SQL from file: " + e.getMessage());
        }
    }
}
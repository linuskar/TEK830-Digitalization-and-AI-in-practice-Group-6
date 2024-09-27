package org.team6.Database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class DatabaseTestSetup {
    private static final String POSTGRES_URL = "jdbc:postgresql://localhost:5432/postgres";  // Connect to default 'postgres' database
    private static final String USER = System.getenv("DB_USER");  // user  // PostgreSQL username
    private static final String PASSWORD = System.getenv("DB_PASSWORD");  // password  // PostgreSQL password
    private static final String SETUPSQLFILEPATH = "src\\main\\sql\\setup.sql";

    public static void main(String[] args) {
        String dbName = "my_new_db";  // New database name
        String newDbURL = "jdbc:postgresql://localhost:5432/" + dbName;
        System.out.println("User: " + USER);
        System.out.println("Password: " + PASSWORD);

        
        // Step 1: Connect to the default 'postgres' database to create the new database
        try (Connection conn = DriverManager.getConnection(POSTGRES_URL, USER, PASSWORD)) {
            if (conn != null) {
                System.out.println("Connected to PostgreSQL server successfully.");
                createUser(conn, "src\\main\\sql\\user.sql");
                // Create the new database
                createDatabase(conn, dbName);
                
                // Step 2: Connect to the newly created database
                try (Connection newDbConn = DriverManager.getConnection(newDbURL, USER, PASSWORD)) {
                    // Step 3: Run setup SQL commands in the new database
                    runSetupSQL(newDbConn, SETUPSQLFILEPATH);
                } catch (SQLException e) {
                    System.out.println("Error connecting to new database: " + e.getMessage());
                }
            }

        } catch (SQLException e) {
            System.out.println("Connection failed: " + e.getMessage());
        }
    }

    // Create the new database if it doesn't exist
    private static void createDatabase(Connection conn, String dbName) {
        String createDatabaseSQL = "CREATE DATABASE " + dbName;
        try (Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(createDatabaseSQL);
            System.out.println("Database '" + dbName + "' created successfully.");
        } catch (SQLException e) {
            // Handle the case where the database already exists
            if (e.getSQLState().equals("42P04")) {  // '42P04' = database already exists
                System.out.println("Database '" + dbName + "' already exists.");
            } else {
                System.out.println("Error creating database: " + e.getMessage());
            }
        }
    }

    //Run setup SQL commands (Create tables, insert initial data)
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

    //Run setup SQL commands (Create tables, insert initial data)
    private static void createUser(Connection conn, String sqlFilePath) {
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
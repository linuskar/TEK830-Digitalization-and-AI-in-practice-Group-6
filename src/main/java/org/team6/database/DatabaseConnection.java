package org.team6.database;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

import org.team6.model.EnergyUsageCategory;
import org.team6.model.products.ConventionalOven;
import org.team6.model.products.ForcedAirOven;
import org.team6.model.products.Freezer;
import org.team6.model.products.Fridge;
import org.team6.model.products.FridgeFreezer;
import org.team6.model.products.Product;
import org.team6.model.products.ProductCategory;

// NOTE: We do not use an actual database in this project, instead we use mock data for the products and energy spenders
// This is due to deadlines, time constraints for the project
// But we had plans for it so, there is reason to why foundations for a database is in the code

// Link to information about the not finished database:
// https://docs.google.com/document/d/1JxDC5xcKDjNiRzDip7-FgzctD0OXAkUr1Vwq7ylGTpA/edit?usp=sharing

// Some template code for database connection, from TDA357 course

public class DatabaseConnection {
    private static final String DBNAME = "TEK830_Group6";
    private static final String DATABASE = "jdbc:h2:~/"+DBNAME;
    // default user and password
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";
    private static final String SETUPSQLFILEPATH = "/org/team6/sql/setup.sql";
    private static final String INSERTSSQLFILEPATH = "/org/team6/sql/inserts.sql";
    private static final String VIEWSSQLFILEPATH = "/org/team6/sql/views.sql";
    private static final String TABLESSQLFILEPATH = "/org/team6/sql/tables.sql";
    private static final String RESETSQLFILEPATH = "/org/team6/sql/reset.sql";


    // This is the JDBC connection object you will be using in your methods.
    private Connection conn;

    // Constructor initializes the connection with default credentials
    public DatabaseConnection() throws SQLException, ClassNotFoundException {
        this(DATABASE, USERNAME, PASSWORD);  
    }

    // Initializes the connection, no need to change anything here
    public DatabaseConnection(String db, String user, String pwd) throws SQLException, ClassNotFoundException {
        Class.forName("org.h2.Driver");
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pwd);
        conn = DriverManager.getConnection(db, props);

        if (!isSetupAlreadyRun(conn)) {
            //System.out.println("Setting up database...");
            runSetupSQL(conn);
        }
    }

    /**
     * Retrieves all unique products (not products connected to the app) from the database.
     *
     * @return a string containing all products
     */
    public static List<Product> getProducts() {
        List<Product> dataBaseProducts = new ArrayList<>();

        Fridge fridge1 = new Fridge("Tynnerås", ProductCategory.FRIDGE, EnergyUsageCategory.REFRIGERATION, 114, 7995,365);
        Fridge fridge2 = new Fridge("Forsnäs", ProductCategory.FRIDGE, EnergyUsageCategory.REFRIGERATION, 91, 7995, 310);    

        Freezer freezer1 = new Freezer("Forsnäs", ProductCategory.FREEZER, EnergyUsageCategory.REFRIGERATION, 189, 8495, 212);
        Freezer freezer2 = new Freezer("Tynnerås", ProductCategory.FREEZER, EnergyUsageCategory.REFRIGERATION, 250, 8995, 286);

        FridgeFreezer fridgeFreezer1 = new FridgeFreezer("Mölnås", ProductCategory.FRIDGE_FREEZER, EnergyUsageCategory.REFRIGERATION, 164, 8995, 249, 106);
        FridgeFreezer fridgeFreezer2 = new FridgeFreezer("Alingsås", ProductCategory.FRIDGE_FREEZER, EnergyUsageCategory.REFRIGERATION, 198, 7995,210,106);

        ForcedAirOven forcedAirOven1 = new ForcedAirOven("Mutebo", ProductCategory.FORCED_AIR_OVEN, EnergyUsageCategory.COOKING, 10995, 1.09, 0.52, 1, 70);
        ForcedAirOven forcedAirOven2 = new ForcedAirOven("Forneby", ProductCategory.FORCED_AIR_OVEN, EnergyUsageCategory.COOKING, 5495, 0.93,0.69, 1, 72);
        ForcedAirOven forcedAirOven3 = new ForcedAirOven("Brändbo", ProductCategory.FORCED_AIR_OVEN, EnergyUsageCategory.COOKING, 4995,0.93,0.69, 1, 72);
        ForcedAirOven forcedAirOven4 = new ForcedAirOven("Mattradition", ProductCategory.FORCED_AIR_OVEN, EnergyUsageCategory.COOKING, 3995, 0.99,0.81, 1, 71);

        ConventionalOven oven1 = new ConventionalOven("Lagan", ProductCategory.OVEN, EnergyUsageCategory.COOKING, 2495, 0.82,74);

        dataBaseProducts.add(fridge1);
        dataBaseProducts.add(fridge2);

        dataBaseProducts.add(freezer1);
        dataBaseProducts.add(freezer2);

        dataBaseProducts.add(fridgeFreezer1);
        dataBaseProducts.add(fridgeFreezer2);
        
        dataBaseProducts.add(oven1);

        dataBaseProducts.add(forcedAirOven1);
        dataBaseProducts.add(forcedAirOven2);
        dataBaseProducts.add(forcedAirOven3);
        dataBaseProducts.add(forcedAirOven4);

        return dataBaseProducts;
    }

    // Mock data for energy spenders for the household 
    // where the integers are the amount of energy spent in kWh
    public static HashMap<EnergyUsageCategory, Integer> getEnergySpenders() {
        // We mock the data so that it is biased towards the products we have in the database
        // which is for the products with refrigeration and cooking energy usage categories
        HashMap<EnergyUsageCategory, Integer> energySpending = new HashMap<>();

        energySpending.put(EnergyUsageCategory.HEATING, 60);
        energySpending.put(EnergyUsageCategory.COOKING, 160);
        energySpending.put(EnergyUsageCategory.HOT_WATER, 10);
        energySpending.put(EnergyUsageCategory.COOLING, 70);
        energySpending.put(EnergyUsageCategory.HOME_ELECTRONICS, 50);
        energySpending.put(EnergyUsageCategory.REFRIGERATION, 275);
        energySpending.put(EnergyUsageCategory.LIGHTING, 25);
        energySpending.put(EnergyUsageCategory.ALWAYS_ON, 11);
        energySpending.put(EnergyUsageCategory.LAUNDRY, 30);
        energySpending.put(EnergyUsageCategory.OTHER, 15);

        return energySpending;
    }

    // This is a hack to turn an SQLException into a JSON string error message. No need to change.
    public static String getError(SQLException e) {
        String message = e.getMessage();
        int ix = message.indexOf('\n');
        if (ix > 0) message = message.substring(0, ix);
        message = message.replace("\"","\\\"");
        return message;
    }

    // Check if the setup has already been run
    private static boolean isSetupAlreadyRun(Connection conn) {
        try (Statement stmt = conn.createStatement()) {
            // Check for the existence of a specific table or record
            ResultSet rs = stmt.executeQuery("SELECT 1 FROM products");
            return rs.next();
        } catch (Exception e) {
            return false;
        }
    }

    // Run setup SQL commands
    private static void runSetupSQL(Connection conn) {
        try {
            // Read the SQL files as Strings
            String tablesSql = readResourceFile(TABLESSQLFILEPATH);
            String insertsSQL = readResourceFile(INSERTSSQLFILEPATH);
            String viewsSQL = readResourceFile(VIEWSSQLFILEPATH);

            // Execute the SQL statements
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(tablesSql);
                stmt.execute(insertsSQL);
                stmt.execute(viewsSQL);
                // System.out.println("SQL from tables, inserts and views executed successfully.");
            }
        } catch (Exception e) {
            System.err.println("Error running SQL from file: " + e.getMessage());
        }
    }

    private static String readResourceFile(String resourcePath) throws Exception {
        try (InputStream inputStream = DatabaseConnection.class.getResourceAsStream(resourcePath);
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
            return reader.lines().collect(Collectors.joining(System.lineSeparator()));
        }
        catch (Exception e) {
            throw new Exception("Error reading resource file: " + e.getMessage());
        }
    }

    // Method to reset the database
    public void resetDatabase() {
        try {
            // Read the SQL file as a string
            String resetSQL = readResourceFile(RESETSQLFILEPATH);

            // Execute the SQL statements
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(resetSQL);
                // System.out.println("SQL reset from file executed successfully.");
            }
        } catch (Exception e) {
            System.err.println("Error running SQL from file: " + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Could not close connection: " + e.getMessage());
        }
    }
}
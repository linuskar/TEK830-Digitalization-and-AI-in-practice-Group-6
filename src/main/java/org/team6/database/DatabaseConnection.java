package org.team6.database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection; // JDBC stuff.
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import org.team6.model.EnergyUsageCategory;
import org.team6.model.Products.ConventionalOven;
import org.team6.model.Products.ForcedAirOven;
import org.team6.model.Products.Fridge;
import org.team6.model.Products.FridgeFreezer;
import org.team6.model.Products.Product;
import org.team6.model.Products.ProductCategory;

// Link to information about the database:
// https://docs.google.com/document/d/1JxDC5xcKDjNiRzDip7-FgzctD0OXAkUr1Vwq7ylGTpA/edit?usp=sharing


// Some template code for database connection, from TDA357 course

public class DatabaseConnection {
    private static final String DBNAME = "TEK830_Group6";
    private static final String DATABASE = "jdbc:h2:~/"+DBNAME;
    // default user and password
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "";
    private static final String SETUPSQLFILEPATH = "src\\main\\resources\\org\\team6\\sql\\setup.sql";
    private static final String INSERTSSQLFILEPATH = "src\\main\\resources\\org\\team6\\sql\\inserts.sql";
    private static final String VIEWSSQLFILEPATH = "src\\main\\resources\\org\\team6\\sql\\views.sql";
    private static final String TABLESSQLFILEPATH = "src\\main\\resources\\org\\team6\\sql\\tables.sql";
    private static final String RESETSQLFILEPATH = "src\\main\\resources\\org\\team6\\sql\\reset.sql";

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
            System.out.println("Setting up database...");
            runSetupSQL(conn);
        }
    }

    /**
     * Registers a product in the database.
     *
     * @param productName the name of the product to register
     * @return a JSON string indicating success or failure
     */
    public String registerProduct(String productName){
      String query = "INSERT INTO Products VALUES(?)";
      
      try (PreparedStatement ps = conn.prepareStatement(query)) {
          ps.setString(1, productName);
          ps.executeUpdate();
          return "{\"success\":true}";
      } catch (SQLException e) {
          return "{\"success\":false, \"error\":\""+getError(e)+"\"}";
      }  
    }

    /**
     * Retrieves all unique products (not products connected to the app) from the database.
     *
     * @return a string containing all products
     */
    public static List<Product> getProducts() {
        /* 
        String query = "SELECT * FROM Products";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            StringBuilder sb = new StringBuilder();
            sb.append("products: ");
            while (rs.next()) {
                sb.append("\"").append(rs.getString(1)).append("\",");
            }
            if (sb.charAt(sb.length()-1) == ',') {
                sb.deleteCharAt(sb.length()-1);
            }
            return sb.toString();
        } catch (SQLException e) {
            return "{\"error\":\""+getError(e)+"\"}";
        }
        */

        List<Product> dataBaseProducts = new ArrayList<>();

        // TEMP: Products and energy usage data are hardcoded for now
        // TODO: Put products in database
        Fridge fridge1 = new Fridge("Tynnerås", ProductCategory.FRIDGE, EnergyUsageCategory.REFRIGERATION, 114, 7995,365);

        FridgeFreezer fridgeFreezer1 = new FridgeFreezer("Mölnås", ProductCategory.FRIDGE_FREEZER, EnergyUsageCategory.REFRIGERATION, 164, 8995, 249, 106);
        FridgeFreezer fridgeFreezer2 = new FridgeFreezer("Alingsås", ProductCategory.FRIDGE_FREEZER, EnergyUsageCategory.REFRIGERATION, 198, 7995,210,106);

        ForcedAirOven forcedAirOven1 = new ForcedAirOven("Mutebo", ProductCategory.FORCED_AIR_OVEN, EnergyUsageCategory.COOKING, 10995, 1.09, 0.52, 1, 70);
        ForcedAirOven forcedAirOven2 = new ForcedAirOven("Forneby", ProductCategory.FORCED_AIR_OVEN, EnergyUsageCategory.COOKING, 5495, 0.93,0.69, 1, 72);
        ForcedAirOven forcedAirOven3 = new ForcedAirOven("Brändbo", ProductCategory.FORCED_AIR_OVEN, EnergyUsageCategory.COOKING, 4995,0.93,0.69, 1, 72);
        ForcedAirOven forcedAirOven4 = new ForcedAirOven("Mattradition", ProductCategory.FORCED_AIR_OVEN, EnergyUsageCategory.COOKING, 3995, 0.99,0.81, 1, 71);

        ConventionalOven oven1 = new ConventionalOven("Lagan", ProductCategory.OVEN, EnergyUsageCategory.COOKING, 2495, 0.82,74);


        dataBaseProducts.add(fridge1);

        dataBaseProducts.add(fridgeFreezer1);
        dataBaseProducts.add(fridgeFreezer2);
        
        dataBaseProducts.add(oven1);

        dataBaseProducts.add(forcedAirOven1);
        dataBaseProducts.add(forcedAirOven2);
        dataBaseProducts.add(forcedAirOven3);
        dataBaseProducts.add(forcedAirOven4);

        return dataBaseProducts;
    }

    public static HashMap<EnergyUsageCategory, Integer> getEnergySpenders() {
        HashMap<EnergyUsageCategory, Integer> energySpending = new HashMap<>();

        energySpending.put(EnergyUsageCategory.LIGHTING, 100);
        energySpending.put(EnergyUsageCategory.HEATING, 200);
        energySpending.put(EnergyUsageCategory.COOKING, 300);
        energySpending.put(EnergyUsageCategory.COOLING, 400);
        energySpending.put(EnergyUsageCategory.REFRIGERATION, 500);
        energySpending.put(EnergyUsageCategory.OTHER, 600);

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
            // Read the SQL file as a string
            String tablesSql = new String(Files.readAllBytes(Paths.get(TABLESSQLFILEPATH)));
            String insertsSQL = new String(Files.readAllBytes(Paths.get(INSERTSSQLFILEPATH)));
            String viewsSQL = new String(Files.readAllBytes(Paths.get(VIEWSSQLFILEPATH)));

            // Execute the SQL statements
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(tablesSql);
                stmt.execute(insertsSQL);
                stmt.execute(viewsSQL);
                System.out.println("SQL from tables, inserst and views executed successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error running SQL from file: " + e.getMessage());
        }
    }

    // TODO: Future methods to interact with database
    // Get all lamp products

    // Get information about a lamp product

    // Get the hourly total power consumption for a specific household

    // Get the daily total power consumption for a specific household

    // Get the Yearly total power consumption for a specific household

    // Get the monthly total power consumption for a specific household

    // Get the owned products for a specific household

    // Get the status of current products for a specific household

    // Get the utility usage for a specific household

    // Method to reset the database
    public void resetDatabase() {
        try {
            // Read the SQL file as a string
            String resetSQL = new String(Files.readAllBytes(Paths.get(RESETSQLFILEPATH)));
            String insertsSQL = new String(Files.readAllBytes(Paths.get(INSERTSSQLFILEPATH)));
            String viewsSQL = new String(Files.readAllBytes(Paths.get(VIEWSSQLFILEPATH)));

            // Execute the SQL statements
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(resetSQL);
                System.out.println("SQL reset from file executed successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error running SQL from file: " + e.getMessage());
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
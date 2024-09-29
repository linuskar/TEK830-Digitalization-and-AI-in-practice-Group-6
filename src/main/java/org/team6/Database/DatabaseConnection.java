package org.team6.Database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*; // JDBC stuff.
import java.util.Properties;

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
            runSetupSQL(conn, SETUPSQLFILEPATH);
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
    public String getProducts() {
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
    private static void runSetupSQL(Connection conn, String sqlFilePath) {
        try {
            // Read the SQL file as a string
            String sql = new String(Files.readAllBytes(Paths.get(sqlFilePath)));
            // göra det för varje individ sql fil i ordning

            // Execute the SQL statements
            try (Statement stmt = conn.createStatement()) {
                stmt.execute(sql);
                System.out.println("SQL setup from file executed successfully.");
            }
        } catch (Exception e) {
            System.out.println("Error running SQL from file: " + e.getMessage());
        }
    }

    // get all lamp products

    // get information about a lamp product

    // Get the hourly total power consumption for a specific household

    

    // Get the daily total power consumption for a specific household

    // Get the Yearly total power consumption for a specific household

    // Get the monthly total power consumption for a specific household

    // Get the owned products for a specific household

    // Get the status of current products for a specific household

    // Get the utility usage for a specific household

    // Method to reset the database
    public void resetDatabase() {
        runSetupSQL(conn, RESETSQLFILEPATH);
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Could not close connection: " + e.getMessage());
        }
    }
}
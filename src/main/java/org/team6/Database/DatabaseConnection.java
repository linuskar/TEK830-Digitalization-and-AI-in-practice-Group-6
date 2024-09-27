package org.team6.Database;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.*; // JDBC stuff.
import java.util.Properties;

// Some template code for database connection, from TDA357 course

public class DatabaseConnection {
    static final String DBNAME = "TEK830_Group6";
    static final String DATABASE = "jdbc:h2:~/"+DBNAME;
    static final String USERNAME = "sa";
    static final String PASSWORD = "";
    private static final String SETUPSQLFILEPATH = "src\\main\\sql\\setup.sql";

    // This is the JDBC connection object you will be using in your methods.
    private Connection conn;

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
    }

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

    // This is a hack to turn an SQLException into a JSON string error message. No need to change.
    public static String getError(SQLException e) {
        String message = e.getMessage();
        int ix = message.indexOf('\n');
        if (ix > 0) message = message.substring(0, ix);
        message = message.replace("\"","\\\"");
        return message;
    }

    public String setupDatabase() {
      try {
          // Read the SQL file as a string
          String sql = new String(Files.readAllBytes(Paths.get(SETUPSQLFILEPATH)));
          
          // Execute the SQL statements
          try (Statement stmt = conn.createStatement()) {
              stmt.execute(sql);
              System.out.println("SQL setup from file executed successfully.");
              return "{\"success\":true}";
          } catch (SQLException e) {
              return "{\"success\":false, \"error\":\"" + getError(e) + "\"}";
          }
      } catch (Exception e) {
          return "{\"success\":false, \"error\":\"" + e.getMessage() + "\"}";
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
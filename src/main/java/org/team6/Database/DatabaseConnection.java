package org.team6.Database;

import java.sql.*; // JDBC stuff.
import java.util.Properties;

// Some template code for database connection, from TDA357 course

public class DatabaseConnection {

    // Set this to e.g. "portal" if you have created a database named portal
    // Leave it blank to use the default database of your database user
    static final String DBNAME = "portal";
    // For connecting to the portal database on your local machine
    //static final String DATABASE = "jdbc:postgresql://localhost/"+DBNAME;
    static final String DATABASE = "jdbc:postgresql://localhost/"+DBNAME;
    static final String USERNAME = "user";
    static final String PASSWORD = "password";


    // This is the JDBC connection object you will be using in your methods.
    private Connection conn;

    public String getUserName(){
      return USERNAME;
    }

    public String getDatabaseName(){
      return DBNAME;
    }

    public String getPassword(){
      return PASSWORD;
    }

    public DatabaseConnection() throws SQLException, ClassNotFoundException {
        this(DATABASE, USERNAME, PASSWORD);  
    }

    // Initializes the connection, no need to change anything here
    public DatabaseConnection(String db, String user, String pwd) throws SQLException, ClassNotFoundException {
      Class.forName("org.postgresql.Driver");
      Properties props = new Properties();
      props.setProperty("user", user);
      props.setProperty("password", pwd);
      conn = DriverManager.getConnection(db, props);
    }

    // This is a hack to turn an SQLException into a JSON string error message. No need to change.
    public static String getError(SQLException e){
       String message = e.getMessage();
       int ix = message.indexOf('\n');
       if (ix > 0) message = message.substring(0, ix);
       message = message.replace("\"","\\\"");
       return message;
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
}
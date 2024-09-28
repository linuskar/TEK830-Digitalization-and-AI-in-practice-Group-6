package org.team6;

import org.team6.Database.DatabaseConnection;

public class Main {
    public static void main(String[] args){
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
    }
}
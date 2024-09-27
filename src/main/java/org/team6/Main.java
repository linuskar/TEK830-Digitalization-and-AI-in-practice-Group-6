package org.team6;

import java.sql.SQLException;

import org.team6.Database.DatabaseConnection;

public class Main {
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        DatabaseConnection databaseConnection = new DatabaseConnection();

        databaseConnection.registerProduct("testing2222");
    }
}
package com.wordquest.database;

import com.wordquest.util.Constants;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC Driver not found. " + e.getMessage());
        }
        return DriverManager.getConnection(Constants.DB_URL);
    }
}

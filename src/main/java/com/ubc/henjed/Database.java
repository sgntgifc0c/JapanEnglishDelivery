package com.ubc.henjed;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    final private static String url = "jdbc:postgresql://localhost/delivery";
    final private static String user = "postgres";
    final private static String password = "root";

    public static Connection init() throws SQLException {
        
        Connection connection = DriverManager.getConnection(url, user, password);
        if (connection != null) {
            System.out.println("Connected to the PostgreSQL server successfully!");
            
        } else {
            System.out.println("Failed to connect to the PostgreSQL server.");
        }

        return connection;
    }
}

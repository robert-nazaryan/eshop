package org.example.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionProvider {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/eshop";
    public static final String DB_USERNAME = "root";
    public static final String DB_PASSWORD = "root";
    private static DBConnectionProvider dbConnectionProvider = new DBConnectionProvider();
    private Connection connection;

    private DBConnectionProvider() {
    }

    public static DBConnectionProvider getInstance() {
        return dbConnectionProvider;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }
}

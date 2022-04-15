package com.example.loginregprototyp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static String database_url = "jdbc:mysql://localhost/userbase";
    private static String user_name = "root";
    private static String password = "";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(database_url, user_name, password);
    }
}

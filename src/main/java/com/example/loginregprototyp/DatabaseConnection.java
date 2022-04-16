package com.example.loginregprototyp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    public static Connection getConnection() throws SQLException {
        String database_url = "jdbc:mysql://localhost/userbase";
        String user_name = "root";
        String password = "";

        return DriverManager.getConnection(database_url, user_name, password);
    }
}

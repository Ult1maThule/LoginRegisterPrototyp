package com.example.loginregprototyp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private String database_url = "jdbc:mysql://localhost/userbase";
    private String user_name = "root";
    private String password = "";

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(this.database_url, user_name, password);
    }
}

package com.example.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnectionFactory implements ConnectionFactory {
    private static final String URL = "jdbc:mysql://localhost:3306/mydatabase";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    
    @Override
    public Connection createConnection() throws SQLException {
        return DriverManager.getConnection(URL , USERNAME, PASSWORD);
    }
}
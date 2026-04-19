package org.example.util;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class DatabaseManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/okno";
    private static final String USER = "window_app_user";
    private static final String PASS = "user_pass";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
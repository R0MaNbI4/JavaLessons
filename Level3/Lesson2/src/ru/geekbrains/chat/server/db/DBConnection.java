package ru.geekbrains.chat.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/chat", "root", "chapa135_");
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}

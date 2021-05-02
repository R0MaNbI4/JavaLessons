package ru.geekbrains.chat.server.auth;

import ru.geekbrains.chat.server.db.DBConnection;

import java.sql.*;
import java.util.Set;

public class AuthenticationService {
    public AuthEntry findUserByCredentials(String login, String password) {
        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM credentials WHERE login = ? AND password = ?;");
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return null;
            }

            return new AuthEntry(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)
            );
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }
}

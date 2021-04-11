package ru.geekbrains.chat.server.auth;

import ru.geekbrains.chat.server.db.DBConnection;

import java.sql.*;
import java.util.Set;

public class AuthenticationService {
//    private static final Set<AuthEntry> entries = Set.of(
//            new AuthEntry("l1", "p1", "Nickname1"),
//            new AuthEntry("l2", "p2", "Nickname2"),
//            new AuthEntry("l3", "p3", "Nickname3")
//    );

//    public AuthEntry findUserByCredentials(String login, String password) {
//        for (AuthEntry entry : entries) {
//            if (entry.getLogin().equals(login) && entry.getPassword().equals(password)) {
//                return entry;
//            }
//        }
//        return null;
//    }

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

            System.out.println(
                    resultSet.getString(1) + " " +
                    resultSet.getString(2) + " " +
                    resultSet.getString(3) + " "
                    );

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

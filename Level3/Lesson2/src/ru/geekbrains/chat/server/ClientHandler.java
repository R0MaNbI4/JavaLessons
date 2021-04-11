package ru.geekbrains.chat.server;

import ru.geekbrains.chat.server.auth.AuthEntry;
import ru.geekbrains.chat.server.db.DBConnection;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static java.lang.Thread.currentThread;

public class ClientHandler {
    private final Server server;
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private String login;
    private String name;
    private String message;
    private final long timer;
    private final int authTimeout;
    private final Thread timeoutThread, listenThread;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.timer = System.currentTimeMillis();
            this.authTimeout = 120000;

            timeoutThread = new Thread(this::checkTimeout);
            listenThread = new Thread(this::listen);
            listenThread.start();
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    private void listen() {
        try {
            timeoutThread.start();
            doAuth();
            timeoutThread.interrupt();
            readMessage();
        } catch (IOException e) {
            disconnect();
            currentThread().interrupt();
            timeoutThread.interrupt();
        }
    }

    private void doAuth() throws IOException {
        while (true) {
            String input = in.readUTF();
            if (input.startsWith("-auth")) {
                String[] credentials = input.split("\\s");
                if (credentials.length == 3) {
                    AuthEntry maybeAuthEntry = server.getAuthenticationService()
                            .findUserByCredentials(credentials[1], credentials[2]);
                    if (maybeAuthEntry != null) {
                        if (server.isNicknameFree(maybeAuthEntry.getNickname())) {
                            sendMessage("CMD: auth is OK");
                            name = maybeAuthEntry.getNickname();
                            login = maybeAuthEntry.getLogin();
                            server.broadcast(name + " logged-in");
                            server.subscribe(this);
                            break;
                        } else {
                            sendMessage("User is already logged-in");
                        }
                    } else {
                        sendMessage("User not found. Incorrect login/password");
                    }
                } else {
                    sendMessage("Missing credentials");
                }
            } else {
                sendMessage("Invalid authentication request. Type -auth login pass");
            }
        }
    }

    public void sendMessage(String message) {
        try {
            out.writeUTF(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void readMessage() throws IOException {
        while (true) {
            message = in.readUTF();
            if (message.startsWith("-w")) {
                whisper();
            } else if (message.startsWith("-cn")) {
                changeNickname();
            } else {
                server.broadcast(name + ": " + message);
            }
        }
    }

    private void changeNickname() {
        String newNickname = message.split("\\s")[1];

        Connection connection = DBConnection.getConnection();
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE credentials SET nickname = ? WHERE login = ?;");
            statement.setString(1, newNickname);
            statement.setString(2, login);
            statement.execute();
            sendMessage(String.format("Nickname changed from \"%s\" to \"%s\"", name, newNickname));
            name = newNickname;
        } catch (SQLException throwables) {
            throw new RuntimeException(throwables);
        }
    }

    private void whisper() {
        String[] parameters = message.split("\\s");
        ClientHandler recipient = server.findUserByNickname(parameters[1]);
        StringBuilder sb = new StringBuilder(parameters[2]);
        for (int i = 3; i < parameters.length ; i++) {
            sb.append(" ").append(parameters[i]);
        }
        String privateMessage = sb.toString();
        if (recipient != null) {
            privateMessage = this.getName() + "->" + recipient.getName() + ": " + privateMessage;
            sendMessage(privateMessage);
            server.sendMessage(recipient, privateMessage);
        } else {
            sendMessage("User not found");
        }
    }

    private void disconnect() {
        server.unsubscribe(this);
        if (this.name != null)
        server.broadcast(this.name + " disconnected");
        System.out.println(this.name + " disconnected");
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        timeoutThread.interrupt();
        listenThread.interrupt();
    }

    private void checkTimeout() {
        while (!currentThread().isInterrupted()) {
            try {
                while (true) {
                    Thread.sleep(1000);
                    if (System.currentTimeMillis() - timer > authTimeout) {
                        sendMessage("Authentication timed out");
                        disconnect();
                        currentThread().interrupt();
                    }
                }
            } catch (InterruptedException e) {
                currentThread().interrupt();
            }
        }
    }

    public String getName() {
        return name;
    }
}

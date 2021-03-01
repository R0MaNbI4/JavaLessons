package ru.geekbrains.chat.server;

import ru.geekbrains.chat.server.auth.AuthEntry;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;

public class ClientHandler {
    private final Server server;
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;
    private String name;
    private String message;

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(this::listen).start();
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    private void listen() {
        try {
            doAuth();
            readMessage();
        } catch (IOException e) {
            server.unsubscribe(this);
            server.broadcast(this.name + " disconnected");
            System.out.println(this.name + " disconnected");
        }
    }

    private void doAuth() throws IOException {
        while (true) {
            String input = in.readUTF();
            if (input.startsWith("-auth")) {
                String[] credentials = input.split("\\s");
                AuthEntry maybeAuthEntry = server.getAuthenticationService()
                        .findUserByCredentials(credentials[1], credentials[2]);
                if (maybeAuthEntry != null) {
                    if (server.isNicknameFree(maybeAuthEntry.getNickname())) {
                        sendMessage("CMD: auth is OK");
                        name = maybeAuthEntry.getNickname();
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
                sendMessage("Invalid authentication request");
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
            } else {
                server.broadcast(name + ": " + message);
            }
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

    public String getName() {
        return name;
    }
}

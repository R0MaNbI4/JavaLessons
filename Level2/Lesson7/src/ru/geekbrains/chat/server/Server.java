package ru.geekbrains.chat.server;

import ru.geekbrains.chat.server.auth.AuthEntry;
import ru.geekbrains.chat.server.auth.AuthenticationService;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server {
    private final ServerSocket serverSocket;
    private final AuthenticationService authenticationService;
    private Set<ClientHandler> handlers;

    public Server () {
        authenticationService = new AuthenticationService();
        handlers = new HashSet<>();
        try {
            serverSocket = new ServerSocket(25565);
            init();
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }

    }

    private void init() throws IOException {
            while (true) {
                System.out.println("Server is waiting for a connection...");
                Socket client = serverSocket.accept();
                System.out.println("Client accepted: " + client);
                new ClientHandler(this, client);
            }
    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public boolean isNicknameFree(String nickname) {
        for (ClientHandler handler : handlers) {
            if (handler.getName().equals(nickname)) {
                return false;
            }
        }
        return true;
    }

    public void broadcast(String message) {
        for (ClientHandler handler : handlers) {
            handler.sendMessage(message);
        }
    }

    public void subscribe(ClientHandler handler) {
        handlers.add(handler);
    }

    public void unsubscribe(ClientHandler handler) {
        handlers.remove(handler);
    }

    public ClientHandler findUserByNickname(String name) {
        for (ClientHandler handler : handlers) {
            if (handler.getName().equals(name)) {
                return handler;
            }
        }
        return null;
    }

    public void sendMessage(ClientHandler handler, String message) {
        handler.sendMessage(message);
    }
}

package ru.geekbrains.server;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Server {
    private ServerSocket serverSocket;
    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;

    public Server() {
        try {
            serverSocket = new ServerSocket(25565);
            System.out.println("Server started");
            clientSocket = serverSocket.accept();
            System.out.println("Client connected");

            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());

            new Thread(() -> {
                while (true) {
                    try {
                        Scanner scanner = new Scanner(System.in);
                            out.writeUTF(scanner.next());
                    } catch (IOException e) {
                        System.out.println("Client disconnected");
                        break;
                    }
                }
            })
                    .start();

            while (true) {
                try {
                    System.out.println(in.readUTF());
                } catch (SocketException e) {
                    System.out.println("Client disconnected");
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }
}
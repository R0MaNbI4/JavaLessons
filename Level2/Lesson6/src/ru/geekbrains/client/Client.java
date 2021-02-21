package ru.geekbrains.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
    Socket clientSocket;
    DataOutputStream out;
    DataInputStream in;

    Client() {
        try {
            try {
                clientSocket = new Socket("localhost", 25565);
            } catch (IOException e) {
                System.out.println("Failed to connect to server");
                return;
            }
            System.out.println("Client started");
            in = new DataInputStream((clientSocket.getInputStream()));
            out = new DataOutputStream(clientSocket.getOutputStream());

            new Thread(() -> {
                while (true) {
                    try {
                        Scanner scanner = new Scanner(System.in);
                        out.writeUTF(scanner.next());
                    } catch (IOException e) {
                        System.out.println("Transmission closed");
                        break;
                    }
                }
            })
                    .start();

            while (true) {
                try {
                    System.out.println(in.readUTF());
                } catch (SocketException e) {
                    System.out.println("Server closed");
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }
}
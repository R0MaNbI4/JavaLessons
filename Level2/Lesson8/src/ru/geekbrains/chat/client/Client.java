package ru.geekbrains.chat.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketException;
import java.util.Scanner;

public class Client {
    private final Socket socket;
    private final DataInputStream in;
    private final DataOutputStream out;

    public Client() {
        try {
            socket = new Socket("localhost", 25565);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(() -> {
                Scanner scanner = new Scanner(System.in);
                while (true) {
                    try {
                        sendMessage(scanner.nextLine());
                    } catch (IOException e) {
                        System.out.println("Server is not available");
                        break;
                    }
                }
            })
                    .start();

            while (true) {
                try {
                    System.out.println(in.readUTF());
                } catch (SocketException e) {
                    System.out.println("Server is not available");
                    break;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException("SWW", e);
        }
    }

    private void sendMessage(String message) throws IOException {
            out.writeUTF(message);
    }
}

package ru.geekbrains.chat.client;

public class ClientOne {
    public static void main(String[] args) {
        new ClientChatAdapter("localhost", 25565);
    }
}

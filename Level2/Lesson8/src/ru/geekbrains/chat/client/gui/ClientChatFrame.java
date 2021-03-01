package ru.geekbrains.chat.client.gui;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ClientChatFrame extends JFrame implements ChatFrameInteraction {
    private final ChatFrame chatFrame;

    public ClientChatFrame(Consumer<String> messageConsumer, Callback onCloseCallback) {
        this.chatFrame = new ChatFrame("Chat", messageConsumer, onCloseCallback);
    }

    @Override
    public void append(String message) {
        chatFrame.getChatArea().append(message);
        chatFrame.getChatArea().append("\n");
    }
}
package ru.geekbrains.chat.client.gui;

import ru.geekbrains.chat.client.data.MessageLog;

import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;

public class ClientChatFrame extends JFrame implements ChatFrameInteraction {
    private final ChatFrame chatFrame;

    public ClientChatFrame(Consumer<String> messageConsumer) {
        this.chatFrame = new ChatFrame("Chat", messageConsumer);
    }

    @Override
    public void append(String message) {
        chatFrame.getChatArea().append(message);
        chatFrame.getChatArea().append("\n");
    }
}
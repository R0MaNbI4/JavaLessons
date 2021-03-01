package ru.geekbrains.chat;

import javax.swing.*;
import java.awt.*;

public class ChatFrame extends JFrame {
    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private final int WIDTH = 300;
    private final int HEIGHT = 400;
    private final int X_CENTER = (int) (dimension.getWidth() / 2) - (WIDTH / 2);
    private final int Y_CENTER = (int) (dimension.getHeight() / 2) - (HEIGHT / 2);

    public ChatFrame() {
        setTitle("Chat");
        setBounds(X_CENTER, Y_CENTER, WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        JPanel messageHistoryPanel = new JPanel();
        add(messageHistoryPanel, BorderLayout.CENTER);
        messageHistoryPanel.setLayout(new BorderLayout());

        JTextArea messageHistory = new JTextArea();
        messageHistory.setEditable(false);
        JScrollPane messageHistoryScroll = new JScrollPane(messageHistory);
        messageHistoryPanel.add(messageHistoryScroll, BorderLayout.CENTER);

        JPanel input = new JPanel();
        add(input, BorderLayout.SOUTH);
        input.setLayout(new BorderLayout());

        JTextField inputField = new JTextField();
        inputField.addKeyListener(new InputFieldKeyListener(messageHistory));
        input.add(inputField, BorderLayout.CENTER);

        JButton sendButton = new JButton();
        sendButton.setText("Send");
        sendButton.addActionListener(new SendButtonActionListener(inputField, messageHistory));
        input.add(sendButton, BorderLayout.EAST);

        setVisible(true);
    }
}
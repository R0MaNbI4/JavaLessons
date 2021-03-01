package ru.geekbrains.chat.client.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.function.Consumer;

public class ChatFrame extends JFrame {
    private final Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
    private final int WIDTH = 300;
    private final int HEIGHT = 400;
    private final int X_CENTER = (int) (dimension.getWidth() / 2) - (WIDTH / 2);
    private final int Y_CENTER = (int) (dimension.getHeight() / 2) - (HEIGHT / 2);
    private final JPanel top;
    private final JPanel bottom;
    private final JTextArea chatArea;

    public ChatFrame(String title, Consumer<String> consumer) {
        setTitle(title);
        setBounds(X_CENTER, Y_CENTER, WIDTH, HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        setLayout(new BorderLayout());

        chatArea = new JTextArea();
        top = createTop();
        bottom = createBottom(consumer);

        setVisible(true);
    }

    private JPanel createTop() {
        JPanel top = new JPanel();
        add(top, BorderLayout.CENTER);
        top.setLayout(new BorderLayout());

        chatArea.setEditable(false);
        JScrollPane chatAreaScroll = new JScrollPane(chatArea);
        top.add(chatAreaScroll, BorderLayout.CENTER);
        return top;
    }

    public JTextArea getChatArea() {
        return chatArea;
    }

    private JPanel createBottom(Consumer<String> consumer) {
        JPanel bottom = new JPanel();
        add(bottom, BorderLayout.SOUTH);
        bottom.setLayout(new BorderLayout());

        JTextField inputField = new JTextField();
        inputField.addKeyListener(new KeyListener() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    send(inputField, chatArea, consumer);
                }
            }
            @Override
            public void keyReleased(KeyEvent e) {}
            @Override
            public void keyTyped(KeyEvent e) {}
        });
        bottom.add(inputField, BorderLayout.CENTER);

        JButton sendButton = new JButton();
        sendButton.setText("Send");
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                send(inputField, chatArea, consumer);
            }
        });
        bottom.add(sendButton, BorderLayout.EAST);
        return bottom;
    }

    private void send(JTextField inputField, JTextArea chatArea, Consumer<String> consumer) {
        if (!inputField.getText().isBlank()) {
            String newMessage = inputField.getText();

            StringBuilder sb = new StringBuilder(chatArea.getText());
            sb.append(newMessage + "\n");
            chatArea.setText(sb.toString());
            inputField.setText("");

            consumer.accept(newMessage);
        }
    }
}

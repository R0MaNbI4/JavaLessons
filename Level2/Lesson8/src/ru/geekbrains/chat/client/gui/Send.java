package ru.geekbrains.chat;

import javax.swing.*;

public class Send {
    static void send(JTextField textField, JTextArea textArea) {
        if (!textField.getText().equals("")) {
            StringBuilder sb = new StringBuilder(textArea.getText());
            sb.append(textField.getText() + "\n");
            textArea.setText(sb.toString());

            textField.setText("");
        }
    }
}

package ru.geekbrains.chat;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SendButtonActionListener implements ActionListener {
    JTextArea textArea;
    JTextField textField;

    SendButtonActionListener(JTextField textField, JTextArea textArea) {
        this.textArea = textArea;
        this.textField = textField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Send.send(textField, textArea);
    }
}

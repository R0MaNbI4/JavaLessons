package ru.geekbrains.chat;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputFieldKeyListener implements KeyListener {
    JTextArea textArea;

    InputFieldKeyListener(JTextArea textArea) {
        this.textArea = textArea;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 10) {
            Send.send((JTextField) e.getComponent(), textArea);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

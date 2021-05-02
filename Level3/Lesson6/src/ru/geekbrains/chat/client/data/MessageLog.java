package ru.geekbrains.chat.client.data;

import ru.geekbrains.chat.client.gui.ClientChatFrame;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MessageLog {
    private static File log;

    static {
        log = new File("messageLog.txt");
        try {
            if (!log.exists()) {
                log.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error while creating new file " + log.getPath());
        }
    }

    public static void addMessage(String message) {
        try (FileWriter writer = new FileWriter(log, true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            throw new RuntimeException("Error while writing to file " + log.getPath());
        }
    }

    public static void loadMessages(ClientChatFrame frame, int count) {
        try (BufferedReader reader = new BufferedReader(new FileReader(log))) {
            List<String> lines = new ArrayList<>();
            String line;

            do {
                line = reader.readLine();
                if (line != null) {
                    lines.add(line);
                }
            } while (line != null);

            if (lines.size() < count) {
                for (String line2 : lines) {
                    frame.append(line2);
                }
            } else {
                for (int i = lines.size() - count; i < lines.size(); i++) {
                    frame.append(lines.get(i));
                }
            }

        } catch (FileNotFoundException e) {
            throw new RuntimeException("File not found: " + log.getPath());
        } catch (IOException e) {
            throw new RuntimeException("Error while reading from file " + log.getPath());
        }
    }

    public static void loadMessages(ClientChatFrame frame) {
        loadMessages(frame, 100);
    }
}
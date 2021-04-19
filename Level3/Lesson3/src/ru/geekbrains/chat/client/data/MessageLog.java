package ru.geekbrains.chat.client.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

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
        } catch (NullPointerException e) {
            System.out.println("Файла не существует");
        }
    }
}

package ru.geekbrains.chat.client;

import ru.geekbrains.chat.client.data.MessageLog;
import ru.geekbrains.chat.client.gui.ClientChatFrame;

import java.util.function.Consumer;

public class ClientChatAdapter {
    private final BasicChatNetwork network;
    private final ClientChatFrame frame;

    public ClientChatAdapter(String host, int port) {
        this.network = new BasicChatNetwork(host, port);
        this.frame = new ClientChatFrame(new Consumer<String>() {
            @Override
            public void accept(String message) {
                network.send(message);
            }
        });

        loadMessages();
        receive();
    }

    public void receive() {
        new Thread(() -> {
            while (true) {
                String message = network.receive();
                MessageLog.addMessage(message);
                frame.append(message);
            }
        })
                .start();
    }

    private void loadMessages() {
        MessageLog.loadMessages(frame);
    }

    private void onClose() {
        network.close();
    }
}

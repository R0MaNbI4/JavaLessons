package ru.geekbrains.chat.client;

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

        receive();
    }

    public void receive() {
        new Thread(() -> {
            while (true) {
                frame.append(network.receive());
            }
        })
                .start();
    }

    private void onClose() {
        network.close();
    }
}

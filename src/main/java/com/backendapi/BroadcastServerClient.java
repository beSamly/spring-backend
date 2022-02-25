package com.backendapi;

import com.backendapi.appconfig.ConfigService;
import com.backendapi.constants.EVENT_TYPE;
import com.backendapi.event.Event;
import com.backendapi.event.EventListener;
import com.backendapi.manager.EventManager;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.function.Consumer;

@Service
public class BroadcastServerClient {

    private final EventManager eventManager;
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;

    public BroadcastServerClient(EventManager eventManager) {
        this.eventManager = eventManager;
        this.eventManager.addListener(new EventListener(EVENT_TYPE.MESSAGE_TO_BROADCAST_SERVER, this.sendToBroadcastServer));
    }

    public void startListening() {
        int Port = ConfigService.getBroadcastServerConfig().getPort();
        String ip = "127.0.0.1";

        try (Socket clientSocket = new Socket(ip, Port)) {
            try {
                this.writer = new PrintWriter(clientSocket.getOutputStream(), true);
                BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
//                    String dataReceived = reader.readLine();
                String dataReceived;

                while ((dataReceived = reader.readLine()) != null) {
                    this.onDataFromDeliveryServer(dataReceived);
                }


            } catch (SocketException socketException) {

            }

        } catch (IOException ex) {
            System.out.println("Delivery Server connection exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void onDataFromDeliveryServer(String data) {
        System.out.println("[From DeliveryServer] msg : " + data);
        this.eventManager.emit(new Event(EVENT_TYPE.MESSAGE_FROM_BROADCAST_SERVER, data));
//        this.broadcastMessageContainer.onMessage(data);
//        this.socketServer.onDataFromDeliveryServer(data);
    }


    public final Consumer<String> sendToBroadcastServer = (String data) -> {
        this.writer.println(data);
    };

    public void sendToBroadcastServer(String data) {
        this.writer.println(data);
    }
}


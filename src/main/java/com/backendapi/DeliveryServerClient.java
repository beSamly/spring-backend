package com.backendapi;

import com.backendapi.appconfig.ConfigService;
import com.backendapi.packet.IPacketHandler;
import com.backendapi.packet.LoginHandler;
import com.backendapi.packet.MessageHandler;
import org.springframework.stereotype.Service;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.Date;
import java.util.HashMap;

@Service
public class DeliveryServerClient {

    private final SocketServer socketServer;
    private Socket clientSocket;
    private BufferedReader reader;
    private PrintWriter writer;

    public DeliveryServerClient(SocketServer socketServer) {
        this.socketServer = socketServer;
    }

    public void startListening() {
        int deliveryServerPort = ConfigService.getDeliveryServerConfig().getPort();
        String deliveryServerIp = "127.0.0.1";

        try (Socket clientSocket = new Socket(deliveryServerIp, deliveryServerPort)) {
            while (true) {
                try {
                    this.writer = new PrintWriter(clientSocket.getOutputStream(), true);
                    BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    String dataReceived = reader.readLine();
                    this.onDataFromDeliveryServer(dataReceived);
                } catch (SocketException socketException) {

                }
            }

        } catch (IOException ex) {
            System.out.println("Delivery Server connection exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void onDataFromDeliveryServer(String data) {
        System.out.println("[From DeliveryServer] msg : " + data);
        this.socketServer.onDataFromDeliveryServer(data);
    }

    public void sendDataToDeliveryServer(String data) {
        this.writer.println(data);
    }
}


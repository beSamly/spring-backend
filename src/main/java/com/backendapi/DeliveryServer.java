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
public class DeliveryServer {

    private int port = ConfigService.getServerConfig().getPort();

    public void startListening() {
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            System.out.println("Delivery Server is listening on port " + this.port);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("[ " + clientSocket.getInetAddress() + " ] client connected");
                Thread t = new Thread() {
                    public void run() {
                        onEachClientSocket(clientSocket);
                    }
                };
                t.start();
            }

        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void onEachClientSocket(Socket clientSocket) {
        try {
            while (true) {
                OutputStream output = clientSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);

                InputStream input = clientSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String dataReceived = reader.readLine();
                System.out.println("[DeliveryServer] received data : " + dataReceived);

                sendData(writer, dataReceived);
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    private void sendData(PrintWriter writer, String data) {
        writer.println(data);
    }
}


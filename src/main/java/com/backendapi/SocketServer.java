package com.backendapi;

import com.backendapi.appconfig.ConfigService;
import com.backendapi.entity.Employee;
import com.backendapi.packet.IPacketHandler;
import com.backendapi.packet.LoginHandler;
import com.backendapi.packet.MessageHandler;
import com.backendapi.repository.EmployeeRepository;
import org.aspectj.bridge.Message;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

@Service
public class SocketServer {

    private HashMap<String, IPacketHandler> packetHandler = new HashMap<String, IPacketHandler>();
    private HashMap<Integer, Socket> clientSocketHashMap = new HashMap<Integer, Socket>();
    private int port = ConfigService.getSocketServerConfig().getPort();
    private int count = 0;

    public SocketServer(LoginHandler loginHandler, MessageHandler messageHandler) {
        this.packetHandler.put("login", loginHandler);
        this.packetHandler.put("message", messageHandler);
    }

    public void startListening() {
        try (ServerSocket serverSocket = new ServerSocket(this.port)) {
            System.out.println("Server is listening on port " + this.port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                System.out.println("[ " + clientSocket.getInetAddress() + " ] client connected");

                clientSocketHashMap.put(count, clientSocket);
                this.count++;
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
                //TODO 소켓서버는 웹 클라이언트로 부터 아주 메세지도 받지 않을거다. 메세지만 줄거다
                InputStream input = clientSocket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                String dataReceived = reader.readLine();
                System.out.println("###### msg : " + dataReceived);
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    public void onDataFromDeliveryServer(String data) {

        this.clientSocketHashMap.forEach((accountId, clientSocket) -> {
            try {

                OutputStream output = clientSocket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println(data);
            } catch (IOException ex) {
                System.out.println("Server exception: " + ex.getMessage());
                ex.printStackTrace();
            }
        });

//        String packetName = "login";
//        this.packetHandler.get(packetName).handle();
    }
}


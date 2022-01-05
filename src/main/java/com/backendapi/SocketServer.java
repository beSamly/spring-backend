package com.backendapi;

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

    public SocketServer(LoginHandler loginHandler, MessageHandler messageHandler) {
        this.packetHandler.put("login", loginHandler);
        this.packetHandler.put("message", messageHandler);
    }


//        this.packetHandler = handler;
//    }

    public void startListening() {
        int port = 13000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);
            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("[ " + socket.getInetAddress() + " ] client connected");
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);


                this.packetHandler.get("login").handle();

                writer.println(new Date().toString());
                InputStream input = socket.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                System.out.println("###### msg : " + reader.readLine());
            }
        } catch (IOException ex) {
            System.out.println("Server exception: " + ex.getMessage());
            ex.printStackTrace();
        }
    }
}


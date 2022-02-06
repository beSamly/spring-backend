package com.backendapi;

import com.backendapi.appconfig.ConfigService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBackendApplication {

    public static void main(String[] args) {

        String group = args[0];
        String env = args[1];
        String serverType = args[2];
        ConfigService.init(group, env, serverType);

        if (serverType.equals("mainserver")) {

            ApplicationContext context = SpringApplication.run(SpringBackendApplication.class, args);
            SocketServer socketServer = context.getBean(SocketServer.class);
            DeliveryServerClient deliveryServerClient = context.getBean(DeliveryServerClient.class);

            new Thread() {
                public void run() {
                    deliveryServerClient.startListening();
                }
            }.start();

            new Thread() {
                public void run() {
                    socketServer.startListening();
                }
            }.start();


        } else if (serverType.equals("deliveryserver")) {
            DeliveryServer deliveryServer = new DeliveryServer();
            deliveryServer.startListening();
        }
    }
}

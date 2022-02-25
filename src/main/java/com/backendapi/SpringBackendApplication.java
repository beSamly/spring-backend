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
            //SocketServer socketServer = context.getBean(SocketServer.class);
            //BroadcastServerClient broadcastServerClient = context.getBean(BroadcastServerClient.class);

            //브로드케스트 서버와 소켓 통신을 할 필요가 있을 때 사용하는 코드.
            //new Thread() {
            //    public void run() {
            //        broadcastServerClient.startListening();
            //    }
            //}.start();

        }
        //else if (serverType.equals("eventserver")) {
        //    BroadcastServer broadcastServer = new BroadcastServer();
        //    broadcastServer.startListening();
        //}
    }
}

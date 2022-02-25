package com.backendapi.classes;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;

@Getter
@Setter
public class UserSocketSession {

    private final WebSocketSession webSocketSession;
    private int userId;

    public UserSocketSession(WebSocketSession webSocketSession) {
        this.webSocketSession = webSocketSession;
    }

    public void sendMessage(String payload){
        try {
            this.webSocketSession.sendMessage(new TextMessage(payload));
        } catch (IOException ex) {

        }
    }
}

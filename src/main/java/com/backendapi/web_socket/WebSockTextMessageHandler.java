package com.backendapi.web_socket;

import com.backendapi.classes.CustomJSONObject;
import com.backendapi.classes.JsonNodeWrapper;
import com.backendapi.classes.UserSocketSession;
import com.backendapi.helper.JWTHelper;
import com.backendapi.helper.JsonParser;
import com.backendapi.manager.WebSocketSessionManager;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class WebSockTextMessageHandler extends TextWebSocketHandler {

    private final WebSocketSessionManager webSocketSessionManager;

    public WebSockTextMessageHandler(WebSocketSessionManager webSocketSessionManager) {
        this.webSocketSessionManager = webSocketSessionManager;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        try {
            String payload = message.getPayload();
            System.out.println("[From WebSocket Client] Received data : " + payload);

            CustomJSONObject json = JsonParser.parseStringToJsonObject(payload);
            //TODO 로그인 시도일 경우
            if (json == null) {
                return;
            }

            String jwt = json.getString("jwt");

            CustomJSONObject tokenData = JWTHelper.verifyJWT(jwt);

            int userId = tokenData.getInt("userId");

            UserSocketSession userSocketSession = new UserSocketSession(session);
            userSocketSession.setUserId(userId);

            this.webSocketSessionManager.addUserSocketSession(userSocketSession);

            userSocketSession.sendMessage("connected to webSocket server");

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
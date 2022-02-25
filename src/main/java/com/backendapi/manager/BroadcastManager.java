package com.backendapi.manager;

import com.backendapi.classes.BroadcastMessageContainer;
import com.backendapi.classes.CustomJSONObject;
import com.backendapi.classes.JsonNodeWrapper;
import com.backendapi.classes.UserSocketSession;
import com.backendapi.constants.EVENT_TYPE;
import com.backendapi.event.EventListener;
import com.backendapi.helper.JsonParser;
import org.springframework.stereotype.Component;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

@Component
public class BroadcastManager {

    private WebSocketSessionManager webSocketSessionManager = null;

    public BroadcastManager(WebSocketSessionManager webSocketSessionManager, EventManager eventManager) {
//        broadcastMessageContainer.addListener(this.onMessage);
        this.webSocketSessionManager = webSocketSessionManager;
        eventManager.addListener(new EventListener(EVENT_TYPE.MESSAGE_FROM_BROADCAST_SERVER, this.onMessage));
    }

    public final Consumer<String> onMessage = (String data) -> {
        try {


            CustomJSONObject object = JsonParser.parseStringToJsonObject(data);


            List<Integer> receiverIds = object.getIntArray("receiverIds");

            object.remove("receiverIds");

            if (this.webSocketSessionManager != null) {
                HashMap<Integer, UserSocketSession> userSocketSessions = this.webSocketSessionManager.getUserSocketSessions();
                receiverIds.stream().forEach(receiverId -> {
                    UserSocketSession userSocketSession = userSocketSessions.get(receiverId);
                    if (userSocketSession != null) {
                        String payload = object.toString();
                        userSocketSession.sendMessage(payload);
                    }
                });
            }
        } catch (RuntimeException ex) {
            System.out.println("log.error + " + ex.getMessage());
        }

        JsonNodeWrapper json = JsonParser.toJson(data);
        System.out.println("run some logic" + data);
    };

}

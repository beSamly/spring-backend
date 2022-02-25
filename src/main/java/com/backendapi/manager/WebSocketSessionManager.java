package com.backendapi.manager;

import com.backendapi.classes.BroadcastMessageContainer;
import com.backendapi.classes.CustomJSONObject;
import com.backendapi.classes.JsonNodeWrapper;
import com.backendapi.classes.UserSocketSession;
import com.backendapi.helper.JsonParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

@Component
public class WebSocketSessionManager {
    private HashMap<Integer, UserSocketSession> UserSocketSessionHashMap = new HashMap<Integer, UserSocketSession>();

    public WebSocketSessionManager() {
    }


    public void addUserSocketSession(UserSocketSession userSocketSession) {
        this.UserSocketSessionHashMap.put(userSocketSession.getUserId(), userSocketSession);
    }

    public HashMap<Integer, UserSocketSession> getUserSocketSessions(){
        return this.UserSocketSessionHashMap;
    }
}

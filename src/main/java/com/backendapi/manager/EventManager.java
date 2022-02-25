package com.backendapi.manager;

import com.backendapi.BroadcastServerClient;
import com.backendapi.constants.EVENT_TYPE;
import com.backendapi.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class EventManager extends BaseEventManager {

    public EventManager() {
    }
}

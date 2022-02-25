package com.backendapi.manager;

import com.backendapi.event.EventListener;
import com.backendapi.event.IEvent;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class BaseEventManager {

    private HashMap<String, List<Consumer<String>>> eventHandlers = new HashMap<String, List<Consumer<String>>>();

    public BaseEventManager() {
    }

    public void addListener(EventListener eventListener) {
        String eventType = eventListener.getEventType();
        Consumer<String> consumer = eventListener.getConsumer();
        List<Consumer<String>> listeners = this.eventHandlers.getOrDefault(eventType, new ArrayList<Consumer<String>>());
        listeners.add(consumer);
        this.eventHandlers.put(eventType, listeners);
    }

    public void emit(IEvent event) {
        String eventType = event.getEventType();
        Object data = event.getData();

        List<Consumer<String>> listeners = this.eventHandlers.getOrDefault(eventType, new ArrayList<Consumer<String>>());
        listeners.stream().forEach(listener -> {
            listener.accept("awef");
//            listener.accept(data);
        });
    }
}

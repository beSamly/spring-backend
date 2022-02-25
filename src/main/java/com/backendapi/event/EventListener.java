package com.backendapi.event;

import com.backendapi.constants.EVENT_TYPE;
import lombok.AllArgsConstructor;
import java.util.function.Consumer;

@AllArgsConstructor
public class EventListener implements IEventListener {

    private String eventType;
    private Consumer<String> consumer;

    @Override
    public String getEventType() {
        return this.eventType;
    }

    @Override
    public Consumer<String> getConsumer() {
        return this.consumer;
    }
}

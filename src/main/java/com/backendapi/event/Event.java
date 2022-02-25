package com.backendapi.event;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Event implements IEvent {

    private String eventType;
    private Object data;

    @Override
    public String getEventType() {
        return this.eventType;
    }

    @Override
    public Object getData() {
        return this.getData();
    }
}

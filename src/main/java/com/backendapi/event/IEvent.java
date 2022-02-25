package com.backendapi.event;

public interface IEvent {
    public String getEventType();
    public Object getData();
}

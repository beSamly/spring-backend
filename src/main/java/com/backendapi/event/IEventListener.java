package com.backendapi.event;

import java.util.function.Consumer;

public interface IEventListener {
    public String getEventType();
    public Consumer<String> getConsumer();
}

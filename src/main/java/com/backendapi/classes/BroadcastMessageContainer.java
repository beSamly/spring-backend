package com.backendapi.classes;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@Component
public class BroadcastMessageContainer {

    private List<Consumer<String>> callbacks = new ArrayList<Consumer<String>>();

    public BroadcastMessageContainer() {
    }

    public void onMessage(String data) {
        this.callbacks.stream().forEach(func -> {
            func.accept(data);
        });
    }

    public void addListener(Consumer<String> func) {
        this.callbacks.add(func);
    }
}

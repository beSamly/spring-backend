package com.backendapi.controller;

import com.backendapi.redis.ChannelTopics;
import com.backendapi.redis.RedisMessagePublisher;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pubsub")
public class PubSubTestController {


    private final RedisMessagePublisher messagePublisher;

    public PubSubTestController(RedisMessagePublisher messagePublisher) {
        this.messagePublisher = messagePublisher;
    }

    @GetMapping("/publish")
    public String publishMessage() {
        this.messagePublisher.publish(ChannelTopics.topicEventServer,"random message");
        return "Hello";
    }

}

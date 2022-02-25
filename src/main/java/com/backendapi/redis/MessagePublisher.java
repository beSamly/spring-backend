package com.backendapi.redis;

import org.springframework.data.redis.listener.ChannelTopic;

public interface MessagePublisher {
    void publish(ChannelTopic channelTopic, String message);
}
package com.backendapi.redis;

import org.springframework.data.redis.listener.ChannelTopic;

public class ChannelTopics {
    public static ChannelTopic topicEventServer = new ChannelTopic("EVENT_SERVER");
}

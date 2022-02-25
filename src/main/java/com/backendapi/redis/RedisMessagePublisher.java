package com.backendapi.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Component;

@Component
public class RedisMessagePublisher implements MessagePublisher {

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;


    public RedisMessagePublisher(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public RedisMessagePublisher() {
    }

    @Override
    public void publish(ChannelTopic topic ,String message) {
        redisTemplate.convertAndSend(topic.getTopic(),message);
    }
}
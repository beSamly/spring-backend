package com.backendapi.redis;

import com.backendapi.appconfig.ConfigService;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericToStringSerializer;
import org.springframework.stereotype.Component;

@Component
public class RedisConfiguration {

    //    @Autowired
    private RedisConnectionFactory redisConnectionFactory = this.redisConnectionFactory();

    //
    public RedisConnectionFactory redisConnectionFactory() {
        String ip = ConfigService.pubSubRedisConfig.getIp();
        int port = ConfigService.pubSubRedisConfig.getPort();
        JedisConnectionFactory cf = new JedisConnectionFactory();
        cf.setHostName(ip);
        cf.setPort(port);
        cf.afterPropertiesSet();
        return cf;
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory redisConnectionFactory) {
        RedisMessageListenerContainer redisMessageListenerContainer = new RedisMessageListenerContainer();
        redisMessageListenerContainer.setConnectionFactory(redisConnectionFactory);

        MessageListenerAdapter topicEventServerListener = new MessageListenerAdapter(new RedisMessageSubscriber(), "onMessage");
        redisMessageListenerContainer.addMessageListener(topicEventServerListener, ChannelTopics.topicEventServer);
        return redisMessageListenerContainer;
    }

    //@Bean
    //MessageListenerAdapter messageListenerAdapter() {
    //    return new MessageListenerAdapter(new RedisMessageSubscriber(), "onMessage");
    //}

    @Bean
    RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setValueSerializer(new GenericToStringSerializer<Object>(Object.class));
        return redisTemplate;
    }

    @Bean
    MessagePublisher messagePublisher() {
        return new RedisMessagePublisher(redisTemplate(redisConnectionFactory));
    }

}
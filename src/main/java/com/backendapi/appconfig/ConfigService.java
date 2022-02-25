package com.backendapi.appconfig;

import com.backendapi.json.DatabaseConfigJson;
import com.backendapi.json.RedisConfig;
import com.backendapi.json.ServerConfigJson;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import org.springframework.core.io.ClassPathResource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Getter
public class ConfigService {

    static String env;
    static String group;
    static String serverType;
    static DatabaseConfigJson mainDbConfig;
    static DatabaseConfigJson commonDbConfig;
    static ServerConfigJson mainServerConfig;
    static ServerConfigJson broadcastServerConfig;
    static ServerConfigJson socketServerConfig;
    public static ServerConfigJson eventServerConfig;
    static String jwtKey;
    public static RedisConfig pubSubRedisConfig;

    public static void init(String groupParam, String envParam, String serverTypeParam) {

        group = groupParam;
        env = envParam;
        serverType = serverTypeParam;

        if (env == null) {
            System.out.println("Please provide env");
            System.exit(-1);
        }

        if (group == null) {
            System.out.println("Please provide group");
            System.exit(-1);
        }

        if (serverType == null) {
            System.out.println("Please provide type");
            System.exit(-1);
        }

        try {
            File jsonFile = new ClassPathResource(String.format("config/%s/config.json", env)).getFile();
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(jsonFile);
            mainDbConfig = objectMapper.convertValue(node.get("database").get("mainDb"), DatabaseConfigJson.class);
            commonDbConfig = objectMapper.convertValue(node.get("database").get("mainDb"), DatabaseConfigJson.class);
            mainServerConfig = objectMapper.convertValue(node.get("server").get("mainServer"), ServerConfigJson.class);
            broadcastServerConfig = objectMapper.convertValue(node.get("server").get("broadcastServer"), ServerConfigJson.class);
            socketServerConfig = objectMapper.convertValue(node.get("server").get("socketServer"), ServerConfigJson.class);
            eventServerConfig = objectMapper.convertValue(node.get("server").get("eventServer"), ServerConfigJson.class);
            jwtKey = objectMapper.convertValue(node.get("jwt").get("key"), String.class);
            pubSubRedisConfig = objectMapper.convertValue(node.get("redis").get("redisPubSub"), RedisConfig.class);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

    public static ServerConfigJson getServerConfig() {
        if (serverType.equals("mainserver")) {
            return mainServerConfig;
        } else if (serverType.equals("broadcastserver")) {
            return broadcastServerConfig;
        } else {
            return null;
        }
    }

    public static ServerConfigJson getSocketServerConfig() {
        return socketServerConfig;
    }

    public static ServerConfigJson getBroadcastServerConfig() {
        return broadcastServerConfig;
    }

    public static String getJwtKey(){
        return jwtKey;
    }
}

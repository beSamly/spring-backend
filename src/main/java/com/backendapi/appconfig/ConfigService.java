package com.backendapi.appconfig;

import com.backendapi.json.DatabaseConfigJson;
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
    static ServerConfigJson deliveryServerConfig;
    static ServerConfigJson socketServerConfig;

    public static void init(String groupParam, String envParam, String serverTypeParam) {

        env = envParam;
        group = groupParam;
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
            deliveryServerConfig = objectMapper.convertValue(node.get("server").get("deliveryServer"), ServerConfigJson.class);
            socketServerConfig = objectMapper.convertValue(node.get("server").get("socketServer"), ServerConfigJson.class);
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
        } else if (serverType.equals("deliveryserver")) {
            return deliveryServerConfig;
        } else {
            return null;
        }
    }

    public static ServerConfigJson getSocketServerConfig(){
        return socketServerConfig;
    }

    public static ServerConfigJson getDeliveryServerConfig(){
        return deliveryServerConfig;
    }
}

package com.backendapi.appconfig;

import com.backendapi.dto.DatabaseConfig;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class ConfigService {

    public String env;
    public String group;
    public DatabaseConfig mainDbConfig;
    public DatabaseConfig commonDbConfig;

    public ConfigService(Environment enviroment) {
        this.env = enviroment.getProperty("env");
        this.group = enviroment.getProperty("group");

        if (this.env == null || this.group == null) {
            System.out.println("Please provide following program arguments [env, group]");
            System.exit(-1);
            return;
        }
        this.init();
    }

    private void init() {

        try {
            File jsonFile = new ClassPathResource(String.format("config/%s/config.json", this.env)).getFile();
// or
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode node = objectMapper.readTree(jsonFile);
//            this.mainDbConfig = (DatabaseConfig) node.get("database").get("maindb");
            this.mainDbConfig = objectMapper.convertValue(node.get("database").get("maindb"), DatabaseConfig.class);
            this.commonDbConfig = objectMapper.convertValue(node.get("database").get("maindb"), DatabaseConfig.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
    }

}

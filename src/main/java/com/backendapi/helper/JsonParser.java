package com.backendapi.helper;

import com.backendapi.classes.CustomJSONObject;
import com.backendapi.classes.JsonNodeWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class JsonParser {

    static private final ObjectMapper mapper = new ObjectMapper();

    public static JsonNodeWrapper toJson(String s) {
        try {
            JsonNode jsonNode = mapper.readTree(s);
            return new JsonNodeWrapper(jsonNode);
        } catch (JsonProcessingException ex) {
            return null;
        }
    }

    public static String toString(Object javaObject) {
        try {
            // Converting the Java object into a JSON string
            String jsonStr = mapper.writeValueAsString(javaObject);
            // Displaying Java object into a JSON string
            return jsonStr;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }

    public static CustomJSONObject parseStringToJsonObject(String s) {
        try {

            JSONParser parser = new JSONParser();
            JSONObject jsonObject = (JSONObject) parser.parse(s);
            return new CustomJSONObject(jsonObject);
        } catch (ParseException ex) {
            return new CustomJSONObject(new JSONObject());
        }
    }
}

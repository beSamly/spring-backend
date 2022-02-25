package com.backendapi.classes;

import com.fasterxml.jackson.databind.JsonNode;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

public class JsonNodeWrapper {

    private final JsonNode jsonNode;

    public JsonNodeWrapper(JsonNode jsonNode) {
        this.jsonNode = jsonNode;
    }

    public String getString(String fieldName){
        JsonNode fieldValue = this.jsonNode.get(fieldName);
        return String.valueOf(fieldValue);
    }

    public int getInt(String fieldName){
        String fieldValue = this.getString(fieldName);
        return Integer.parseInt(fieldValue);
    }

//    public List<Integer> getArray(String fieldName){
//        String fieldValue = this.getString(fieldName);
//        return Integer.parseInt(fieldValue);
//    }

//    public List<?> getArray(String fieldName){
//
//        try {
//            JSONParser parser = new JSONParser();
//
//            JSONObject univ = (JSONObject) parser.parse(this.jsonNode);
//
//            //테스트출력
//            //System.out.println(univ.toJSONString());
//
//            JSONArray arr = (JSONArray) univ.get("receivers");
//
//            //테스트출력
//            //System.out.println(arr.toJSONString());
//            List<Integer> array = new ArrayList<Integer>();
//            for (int i = 0; i < arr.size(); i++) {
//                array.add(arr.get(i));
//            }
//            return array;
//        } catch (ParseException ex) {
//
//        }
//    }
}

package com.backendapi.classes;

import com.backendapi.helper.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.Getter;
import lombok.Setter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class CustomJSONObject {

    private final JSONObject jsonObject;

    public CustomJSONObject(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getString(String fieldName) {
        String fieldValue = (String) this.jsonObject.get(fieldName);
        return fieldValue;
    }

    public int getInt(String fieldName) {
        return (Integer) this.jsonObject.get(fieldName);
    }

//    public List<Integer> getArray(String fieldName){
//        String fieldValue = this.getString(fieldName);
//        return Integer.parseInt(fieldValue);
//    }

    public void remove(String fieldName) {
        this.jsonObject.remove(fieldName);
    }

    public List<Integer> getIntArray(String fieldName) {

        //테스트출력
        //System.out.println(univ.toJSONString());

        JSONArray arr = (JSONArray) jsonObject.get(fieldName);

        //테스트출력
        //System.out.println(arr.toJSONString());
        List<Integer> array = new ArrayList<Integer>();
        for (int i = 0; i < arr.size(); i++) {
            Long value = (Long) arr.get(i);
            array.add(value.intValue());
        }
        return array;
    }

    public String toString() {
        return JsonParser.toString(this.jsonObject);
    }
}

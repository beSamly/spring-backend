package com.backendapi.socket_message;

import com.backendapi.helper.JsonParser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MessageAlert extends BaseSocketPayload {

    private String payloadCode = MessageAlert.class.getSimpleName();

    private String content;

    private Long channelId;

    @Override
    public String toString() {
//        JSONObject object = new JSONObject();
//
//        for(Field field : MessageAlert.class.getDeclaredFields()){
//            String name = field.getName();
//            try {
//                Object value = field.get(this);
//                object.put(name, value);
//
//            } catch (IllegalAccessException e) {
//                e.printStackTrace();
//            }
//            Annotation[] annotations = field.getDeclaredAnnotations();
//        }

        return JsonParser.toString(this);
    }
}

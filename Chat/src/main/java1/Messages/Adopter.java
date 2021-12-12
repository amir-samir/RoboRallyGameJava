package Messages;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Adopter {

    public static Gson gson;

    /**
     * java Object to json String
     * @param message
     * @return Json String
     */
    public static String javabeanToJson(Message message) {
        gson = new Gson();
        String jsonType = gson.toJson(message);
        if(message.getMessageBody() != null) {
            Object[] keys = message.messageBody.getkeys();
            if (keys != null) {
                for (Object k : keys) {
                    String key = (String) k;
                    jsonType = jsonType.replaceFirst("content", key);
                }
            }
            int index = jsonType.indexOf("\"messageBody");
            jsonType = jsonType.substring(0, index);
            String jsonBody;
            if (!getJsonBody(message).equals("{}")) {
                String messageType = message.getMessageType();
                jsonBody = getJsonBody(message);
                jsonBody = jsonBody.replaceFirst(messageType, "messageBody");
                jsonBody = jsonBody.substring(1, jsonBody.length());
            } else {
                jsonBody = "{}";
            }
            String final_json = new StringBuilder(jsonType).append(jsonBody).toString();
            return final_json;
        } else return new StringBuilder(jsonType).append("{}").toString();
    }

    public static String getJsonBody(Message message) {
        if (message.getMessageBody() != null) {
            Map<String, Map> jsonMap = new HashMap<>();
            Map<String, Object> bodyMap = new HashMap<>();
            String messageType = message.getMessageType();
            Object[] keys = message.messageBody.getkeys();
            Object[] values = message.messageBody.getContent();
            for (int i = 0; i < keys.length; i++) {
                Object value = values[i];
                String key = (String) keys[i];
                bodyMap.put(key, value);
            }
            jsonMap.put(messageType, bodyMap);
            gson = new Gson();
            String json = gson.toJson(jsonMap);
            return json;
        } else return "{}";
    }

    /**
     *
     * @param json
     * @return message
     */
    public static Message jsonToJavaBean(String json) {
        Gson gson = new Gson();
        Message message = gson.fromJson(json, Message.class);
        return message;
    }

    public static Message getMessage(String json) {
        Gson gson = new Gson();
        Map map = gson.fromJson(json, Map.class);
        Message message = new Message();
        try {
            String messageType = map.get("messageType").toString();
            Map messageBody = (Map) map.get("messageBody");
            Object[] content = new Object[messageBody.size()];
            int i = 0;

            for (Object value : messageBody.values()) {
                    content[i] = value;
                    i++;
            }
//            Object[] contenKeys = new String[messageBody.size()];
//            Set keys = messageBody.keySet();
//            int j = 0;
//            for(Object key: keys){
//                contenKeys[j] = messageBody.get(key);
//                j++;
//            }
            MessageBody mbody = new MessageBody(content);
//            mbody.setKeys(contenKeys);
            message.setMessageBody(mbody);
            message.setMessageType(messageType);
            return message;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }


    }


    /**
     *
     * @param message
     * @return M
     */
    public static Message adopterMessage(Message message) {
        return new Gson().fromJson(new Gson().toJson((message.getMessageBody().toString())), Message.class);
    }

}

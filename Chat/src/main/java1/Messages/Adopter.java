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
        String jsonBody = getJsonBody(message);
        String final_json = "";
        if (jsonBody.equals("MessageBody ist leer!")){
            final_json = "{ \"messageType\": \"" + message.getMessageType() + "\", \"messageBody\": { }}";
        }
        else {
            gson = new Gson();
            String jsonType = gson.toJson(message);
            Object[] keys = message.messageBody.getkeys();
            for (Object k: keys){
                String key = (String) k;
                jsonType = jsonType.replaceFirst("content", key);
            }
            int index = jsonType.indexOf("\"messageBody");
            jsonType = jsonType.substring(0,index);
            String messageType = message.getMessageType();
            jsonBody = jsonBody.replaceFirst(messageType, "messageBody");
            jsonBody = jsonBody.substring(1);
            final_json = new StringBuilder(jsonType).append(jsonBody).toString();
        }

        return final_json;
    }

    public static String getJsonBody(Message message) {
        Map<String, Object> jsonMap = new HashMap<>();
        String json = "";
        Map<String, Object> bodyMap = new HashMap<>();
        String messageType = message.getMessageType();
        Object[] keys = message.messageBody.getkeys();
        Object[] values = message.messageBody.getContent();
        if (keys != null && values != null) {
            for (int i = 0; i < keys.length; i++) {
                Object value = values[i];
                String key = (String) keys[i];
                bodyMap.put(key, value);
            }
            jsonMap.put(messageType, bodyMap);

            gson = new Gson();
            json = gson.toJson(jsonMap);
        }
        else {json = "MessageBody ist leer!";}
        return json;
    }

    public static String insertString(
            String originalString,
            String stringToBeInserted,
            int index)
    {

        // Create a new string
        String newString = originalString.substring(0, index + 1)
                + stringToBeInserted
                + originalString.substring(index + 1);

        // return the modified String
        return newString;
    }

    private static int countOccurences(
            String json, char searchedChar) {
        int count = 0;
        for (int i = 0; i < json.length(); i++) {
            Character currentChar = json.charAt(i);
            if (currentChar.equals(searchedChar) ) {
                count++;
            }
        }
        return count;
    }


    private static String replaceFirstOccurences(
            String json, char originalChar, String replacement) {
        StringBuilder sb = new StringBuilder(json);
        int index = json.indexOf(originalChar);
        for (int i = 0; i < json.length(); i++) {
            Character currentChar = json.charAt(i);
            if (currentChar.equals(originalChar) ) {
                json = sb.deleteCharAt(index).toString();
                json = insertString(json, replacement, index-1);
                break;
            }
        }
        return json;
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

    public static String jsonWithBrackets(String json) {
        StringBuilder sb = new StringBuilder(json);
        int count = countOccurences(json, '[');


        for (int i = 0; i < count; i++) {
            if (json.contains("[")) {
                int indexAuf = json.indexOf("[");
                int indexZu = json.indexOf("]");
                sb = new StringBuilder(json);
                json = sb.deleteCharAt(indexAuf).toString();
                json = insertString(json, "[", indexAuf);
                json = replaceFirstOccurences(json, '[', "%");
                sb = new StringBuilder(json);
                json = sb.deleteCharAt(indexZu).toString();
                json = insertString(json, "]", indexZu -2);
                json = replaceFirstOccurences(json, ']', "§");
            }
        }
        json = json.replace("%", "[");
        json = json.replace("§", "]");
        return json;
    }

    public static Message getMessage(String json) {
        json = jsonWithBrackets(json);
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

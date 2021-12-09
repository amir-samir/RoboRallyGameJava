package Messages;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.Map;

public class Adopter {

    public static Gson gson;

    /**
     * java Object to json String
     * @param message
     * @return Json String
     */
    public static String javabeanToJson(Message message) {
        gson = new Gson();
        String json = gson.toJson(message);
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
            MessageBody mbody = new MessageBody(content);
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

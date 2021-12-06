package Messages;
import com.google.gson.Gson;

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

    /**
     *
     * @param message
     * @return M
     */
    public static Message adopterMessage(Message message) {
        return new Gson().fromJson(new Gson().toJson((message.getMessageBody().toString())), Message.class);
    }

}

package Messages;
import com.google.gson.Gson;

import java.util.Map;
/**
 * Die Adopter Klasse wird benötigt, um Java Klassen in JSON Strings zu konvertieren und JSON Strings zu Message Klassen zu Konvertieren.
 * Uns ist bewusst das in Adopter ien Schreibfehler ist, dieser hat aber einen moralischen Wert für unser Team und wurde daher nicht ausgebessert.
 * @author Dairen Gonschior
 */
public class Adopter {


    /**
     * Konvertiert eine Message Class zu einem JSON String
     * @param message Zu konvertierende Message Class
     * @return JSON der aus der Message Class konvertiert wurde
     */
    public static String javabeanToJson(Message message) {
        String jsonBody = getJsonBody(message);
        String final_json = "";
        if (jsonBody.equals("MessageBody ist leer!")){
            final_json = "{ \"messageType\": \"" + message.getMessageType() + "\", \"messageBody\": { }}";
        }
        else {
            final_json = "{ \"messageType\": \"" + message.getMessageType() + "\", \"messageBody\": " + getJsonBody(message);
        }

        return final_json;
    }
    /**
     * Diese Funktion schaut, ob in einem String Buchstaben vorkommen.
     * @param value String der überprüft werden soll.
     * @return Gibt True or False zurück, je nachdem ob sich ein Buchstabe im String befindet.
     */
    public static boolean checkForLetter(String value) {
        boolean hasLetters = false;
        for (char ch : value.toCharArray()) {
            if (Character.isLetter(ch)) {
                hasLetters = true;
                break;
            }
        }
        return hasLetters;
    }
    /**
     * Diese Funktion zieht den MessageBody aus einer Message und wandelt diese in einen JSON String um.
     * @param message ist die zu konvertierende Message.
     * @return Wenn es einen MessageBody gibt wird dieser als JSON zurückgegeben.
     */
    public static String getJsonBody(Message message) {
        String json = "{ ";
        Gson gson = new Gson();
        Object[] keys = null;
        Object[] values = null;
        try {
            keys = message.messageBody.getkeys();
            values = message.messageBody.getContent();
        } catch (NullPointerException e) {
            return "MessageBody ist leer!";
        }
        for (int i = 0; i < keys.length; i++) {
            if (values[i] == null){
                values[i] = "null";
            }
            Object value = values[i];


            String valueString = value.toString();

            if (value.getClass().isArray()) {
                valueString = gson.toJson(values[i]);
            }


            String key = (String) keys[i];
            if (values[i].getClass().isArray() || value.toString().equals("true") || value.toString().equals("false") || !checkForLetter(value.toString())) {
                json = json + "\"" + key + "\": "  + valueString + ", ";
            } else {

                json = json + "\"" + key + "\": \""  + valueString + "\"" + ", ";
            }}
        StringBuilder sb = new StringBuilder(json);
        json = sb.deleteCharAt(json.length()-1).toString();
        sb = new StringBuilder(json);
        json = sb.deleteCharAt(json.length()-1).toString();
        json = json + " }}";

        return json.trim();
    }


    /**
     * Diese Funktion wandelt einen JSON String in eine Message Klasse um.
     * @param json JSON String der konvertiert werden soll.
     * @return Gibt die aus dem JSON String konvertierte Message zurück.
     */
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


}

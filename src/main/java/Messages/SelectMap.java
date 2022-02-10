package Messages;
/**
 * this class implements the specific messageType "MapSelected".
 * all available Maps will be sent from the server to the client
 * @author Luca Weyhofen
 */
public class SelectMap extends Message{
    String availableMaps;

    public SelectMap(String[] maps){
        Object[] daten = new Object[1];
        daten[0] = maps;

        messageType = "SelectMap";
        messageBody = new MessageBody(daten);
    }

}

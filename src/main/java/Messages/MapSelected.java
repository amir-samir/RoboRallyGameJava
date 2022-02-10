package Messages;
/**
 * this class implements the specific messageType "MapSelected".
 * it is used to notify players which map was chosen.
 * @author Luca Weyhofen
 */
public class MapSelected extends Message {

    public MapSelected(String map){
        Object[] daten = new Object[1];
        daten[0] = map;

        messageType = "MapSelected";
        messageBody = new MessageBody(daten);
    }

}

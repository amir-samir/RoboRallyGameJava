package game.Messages;

public class MapSelected extends Message {

    public MapSelected(String map){
        Object[] daten = new Object[1];
        daten[0] = map;

        messageType = "MapSelected";
        messageBody = new MessageBody(daten);
    }

}

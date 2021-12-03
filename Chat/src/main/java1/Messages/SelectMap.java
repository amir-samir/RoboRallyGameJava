package Messages;

public class SelectMap extends Message{

    public SelectMap(String[] maps){
        Object[] daten = new Object[1];
        daten[1] = maps;

        messageType = "SelectMap";
        messageBody = new MessageBody(maps);
    }

}

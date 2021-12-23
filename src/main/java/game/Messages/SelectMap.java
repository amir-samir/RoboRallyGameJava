package game.Messages;

public class SelectMap extends Message{
    String availableMaps;

    public SelectMap(String[] maps){
        Object[] daten = new Object[1];
        daten[0] = maps;

        messageType = "SelectMap";
        messageBody = new MessageBody(maps);
    }

}

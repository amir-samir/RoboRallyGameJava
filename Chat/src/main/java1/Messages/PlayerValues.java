package Messages;

public class PlayerValues extends Message {

    public PlayerValues(String name, int figur){
        Object[] daten = new Object[2];
        daten[0] = name;
        daten[1] = figur;

        messageType = "PlayerValues";
        messageBody = new MessageBody(daten);
    }
}

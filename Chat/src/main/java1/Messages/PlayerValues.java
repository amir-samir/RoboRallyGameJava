package Messages;

public class PlayerValues extends Message {

    public PlayerValues(String name, int figur){
        Object[] daten = new Object[3];
        daten[1] = name;
        daten[2] = figur;

        messageType = "PlayerValues";
        messageBody = new MessageBody(daten);
    }
}

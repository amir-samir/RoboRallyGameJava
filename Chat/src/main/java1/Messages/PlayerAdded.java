package Messages;

public class PlayerAdded extends Message{

    public PlayerAdded(int id, String name, int figur){
        Object[] daten = new Object[3];
        daten[0] = id;
        daten[1] = name;
        daten[2] = figur;

        messageType = "PlayerAdded";
        messageBody = new MessageBody(daten);
    }
}

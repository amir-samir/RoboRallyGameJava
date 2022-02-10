package Messages;
/**
 * this class implements the specific messageType "PlayerAdded".
 * it is used to notify clients when a new client connects to the game.
 * @author Luca Weyhofen
 */
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

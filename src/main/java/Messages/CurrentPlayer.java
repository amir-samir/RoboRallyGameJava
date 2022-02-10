package Messages;

/**
 * this class implements the specific messageType CurrentPlayer
 * about all the current player will be informed.
 * @author Luca Weyhofen
 */
public class CurrentPlayer extends Message {

    public CurrentPlayer(int id){
        Integer[] daten = new Integer[1];
        daten[0] = id;

        messageType = "CurrentPlayer";
        messageBody = new MessageBody(daten);
    }
}

package Messages.Actions;

import Messages.Message;
import Messages.MessageBody;

/**
 * Diese Klasse verkörpert die Nachricht, wenn ein Spiel beendet wurde
 *
 * @author Mateo Pranjic
 */
public class GameFinished extends Message {

    public GameFinished(int id){
        Integer[] daten = new Integer[1];
        daten[0] = id;

        setMessageType("GameFinished");
        setMessageBody(new MessageBody(daten));
    }
}

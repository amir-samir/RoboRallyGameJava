package Messages.Actions;

import Messages.Message;
import Messages.MessageBody;

/**
 * Diese Klasse verkörpert die CheckpointMove Nachricht
 *
 * @author Luca Weyhofen
 *
 * @version 2.1
 */
public class CheckpointMoved extends Message {
    /**
     * Dies ist der Konstruktor
     * @param id Die Nummer des Checkpoints
     * @param x Die neue x-Koordinate
     * @param y Die neue y-Koordinate
     */
    public CheckpointMoved(int id, int x, int y){
        Integer[] daten = new Integer[3];
        daten[0] = id;
        daten[1] = x;
        daten[2] = y;

        setMessageType("CheckpointMoved");
        setMessageBody(new MessageBody(daten));
    }
}

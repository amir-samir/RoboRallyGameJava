package Messages.Actions;

import Messages.Message;
import Messages.MessageBody;

/**
 * this class implements the specific MessageType "Energy".
 * a message will be sent if a player played a Power Up card.
 * @author Luca Weyhofen
 */
public class Energy extends Message {

    public Energy(int id, int count, String source){
        Object[] daten = new Object[3];
        daten[0] = id;
        daten[1] = count;
        daten[2] = source;

        setMessageType("Energy");
        setMessageBody(new MessageBody(daten));
    }
}

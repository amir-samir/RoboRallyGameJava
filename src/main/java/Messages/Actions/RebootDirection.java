package Messages.Actions;

import Messages.Message;
import Messages.MessageBody;

/**
 * Diese Klasse ist die Reboot-Direction Nachricht.
 *
 * @author Luca Weyhofen
 */
public class RebootDirection extends Message {

    public RebootDirection(String direction){
        String[] daten = new String[1];
        daten[0] = direction;

        setMessageType("RebootDirection");
        setMessageBody(new MessageBody(daten));
    }
}

package Messages.Phase;

import Messages.Message;
import Messages.MessageBody;

/**
 * SetStartingPoint Nachricht
 * @author Luca Weyhofen
 */
public class SetStartingPoint extends Message {

    public SetStartingPoint(int x, int y){
        Integer[] daten = new Integer[2];
        daten[0] = x;
        daten[1] = y;

        setMessageType("SetStartingPoint");
        setMessageBody(new MessageBody(daten));
    }
}

package Messages.Phase;

import Messages.Message;
import Messages.MessageBody;

/**
 * SelectionFinished Nachricht
 * @author Mateo Pranjic
 */
public class SelectionFinished extends Message {

    public SelectionFinished(int id){
        Integer[] daten = new Integer[1];
        daten[0] = id;

        setMessageType("SelectionFinished");
        setMessageBody(new MessageBody(daten));
    }
}

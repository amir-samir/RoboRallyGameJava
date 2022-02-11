package Messages.Phase;

import Messages.Message;
import Messages.MessageBody;

/**
 * ReplaceCard Nachricht
 * @author MateoPranjic
 */
public class ReplaceCard extends Message {

    public ReplaceCard(int register, String newCard, int id){
        Object[] daten = new Object[3];
        daten[0] = register;
        daten[1] = newCard;
        daten[2] = id;

        setMessageType("ReplaceCard");
        setMessageBody(new MessageBody(daten));
    }
}

package Messages.Actions;

import Messages.Message;
import Messages.MessageBody;

/**
 * SelectedDamage Nachricht
 * @author Mateo Pranjic
 */
public class SelectedDamage extends Message {

    public SelectedDamage(String[] cards){
        Object[] daten = new Object[1];
        daten[0] = cards;

        setMessageType("SelectedDamage");
        setMessageBody(new MessageBody(daten));
    }
}

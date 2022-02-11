package Messages.Phase;

import Messages.Message;
import Messages.MessageBody;

/**
 * YourCard Nachricht
 * @author Mateo Pranjic
 */
public class YourCards extends Message {

    public YourCards(String[] cards){
        super();
        Object[] daten = new Object[1];
        daten[0] = cards;

        setMessageType("YourCards");
        setMessageBody(new MessageBody(daten));
    }

}

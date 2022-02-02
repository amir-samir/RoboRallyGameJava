package Messages.Actions;

import Messages.Message;
import Messages.MessageBody;

public class ReturnCards extends Message {

    public ReturnCards(String[] cards){
        Object[] daten = new Object[1];
        daten[0] = cards;

        setMessageType("ReturnCards");
        setMessageBody(new MessageBody(daten));
    }
}

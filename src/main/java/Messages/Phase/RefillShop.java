package Messages.Phase;

import Messages.Message;
import Messages.MessageBody;

public class RefillShop extends Message {
    public RefillShop(String[] cards){
        Object[] daten = new Object[1];
        daten[0] = cards;

        setMessageType("ReplaceCard");
        setMessageBody(new MessageBody(daten));
    }
}

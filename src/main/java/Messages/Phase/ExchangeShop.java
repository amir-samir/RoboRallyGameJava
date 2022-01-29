package Messages.Phase;

import Messages.Message;
import Messages.MessageBody;

public class ExchangeShop extends Message {
    public ExchangeShop(String[] cards){
        Object[] daten = new Object[1];
        daten[0] = cards;
        setMessageType("ReplaceCard");
        setMessageBody(new MessageBody(daten));
    }
}

package Messages.Phase;

import Messages.Message;
import Messages.MessageBody;

/**
 * ExchangeShop Nachricht
 * @author Luca Weyhofen
 */
public class ExchangeShop extends Message {
    public ExchangeShop(String[] cards){
        Object[] daten = new Object[1];
        daten[0] = cards;
        setMessageType("ExchangeShop");
        setMessageBody(new MessageBody(daten));
    }
}

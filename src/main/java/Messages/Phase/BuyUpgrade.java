package Messages.Phase;

import Messages.Message;
import Messages.MessageBody;

public class BuyUpgrade extends Message {
    public BuyUpgrade(boolean isBuying, String card){
        Object[] daten = new Object[2];
        daten[0] = isBuying;
        daten[1] = card;

        setMessageType("ReplaceCard");
        setMessageBody(new MessageBody(daten));
    }
}

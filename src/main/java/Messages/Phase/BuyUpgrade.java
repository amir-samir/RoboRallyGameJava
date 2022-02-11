package Messages.Phase;

import Messages.Message;
import Messages.MessageBody;

/**
 * Buy-Upgrade Nachricht
 * @author Luca Weyhofen
 */
public class BuyUpgrade extends Message {
    public BuyUpgrade(boolean isBuying, String card){
        Object[] daten = new Object[2];
        daten[0] = isBuying;
        daten[1] = card;

        setMessageType("BuyUpgrade");
        setMessageBody(new MessageBody(daten));
    }
}

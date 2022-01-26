package Messages.Phase;

import Messages.Message;
import Messages.MessageBody;

public class UpgradeBought extends Message {
    public UpgradeBought(int clientID, String card){
        Object[] daten = new Object[2];
        daten[0] = clientID;
        daten[1] = card;

        setMessageType("ReplaceCard");
        setMessageBody(new MessageBody(daten));
    }
}

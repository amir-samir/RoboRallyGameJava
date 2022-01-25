package Messages.Actions;

import Messages.Message;
import Messages.MessageBody;

public class PickDamage extends Message {
    public PickDamage(int count, String[] cards){
        Object[] daten = new Object[2];
        daten[0] = count;
        daten[1] = cards;

        setMessageType("PickDamage");
        setMessageBody(new MessageBody(daten));
    }
}

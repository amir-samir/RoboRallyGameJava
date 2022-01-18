package Messages.Actions;

import Messages.Message;
import Messages.MessageBody;

public class DrawDamage extends Message {

    public DrawDamage(int id, String[] cards){
        Object[] daten = new Object[2];
        daten[0] = id;
        daten[1] = cards;

        setMessageType("DrawDamage");
        setMessageBody(new MessageBody(daten));
    }
}

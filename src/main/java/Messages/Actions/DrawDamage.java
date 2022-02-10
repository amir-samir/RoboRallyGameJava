package Messages.Actions;

import Messages.Message;
import Messages.MessageBody;
/**
 * this class implements the specific MessageType "DrawDamage".
 * a message will be sent if a player draw a damage card.
 * @author Luca Weyhofen
 */
public class DrawDamage extends Message {

    public DrawDamage(int id, String[] cards){
        Object[] daten = new Object[2];
        daten[0] = id;
        daten[1] = cards;

        setMessageType("DrawDamage");
        setMessageBody(new MessageBody(daten));
    }
}

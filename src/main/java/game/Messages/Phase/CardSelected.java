package game.Messages.Phase;

import game.Messages.Message;
import game.Messages.MessageBody;

public class CardSelected extends Message {

    public CardSelected(int clientID, int register, boolean filled){
        Object[] daten = new Object[3];
        daten[0] = clientID;
        daten[1] = register;
        daten[2] = filled;

        setMessageType("CardSelected");
        setMessageBody(new MessageBody(daten));
    }
}

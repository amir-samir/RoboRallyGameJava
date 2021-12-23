package game.Messages.Actions;

import game.Messages.Message;
import game.Messages.MessageBody;

public class RebootDirection extends Message {

    public RebootDirection(String direction){
        String[] daten = new String[1];
        daten[0] = direction;

        setMessageType("RebootDirection");
        setMessageBody(new MessageBody(daten));
    }
}

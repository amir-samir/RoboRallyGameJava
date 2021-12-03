package Messages.Actions;

import Messages.Message;
import Messages.MessageBody;

public class Energy extends Message {

    public Energy(int id, int count, String source){
        Object[] daten = new Object[3];
        daten[0] = id;
        daten[1] = count;
        daten[2] = source;

        setMessageType("Energy");
        setMessageBody(new MessageBody(daten));
    }
}

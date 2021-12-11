package Messages.Actions;

import Messages.Message;
import Messages.MessageBody;

public class Movement extends Message {

    public Movement(int id, int x, int y){
        Integer[] daten = new Integer[3];
        daten[0] = id;
        daten[1] = x;
        daten[2] = y;

        setMessageType("Movement");
        setMessageBody(new MessageBody(daten));
    }
}
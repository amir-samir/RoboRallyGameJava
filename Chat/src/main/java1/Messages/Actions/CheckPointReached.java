package Messages.Actions;

import Messages.Message;
import Messages.MessageBody;

public class CheckPointReached extends Message {

    public CheckPointReached(int id, int number){
        Integer[] daten = new Integer[2];
        daten[0] = id;
        daten[1] = number;

        setMessageType("CheckPointReached");
        setMessageBody(new MessageBody(daten));
    }
}

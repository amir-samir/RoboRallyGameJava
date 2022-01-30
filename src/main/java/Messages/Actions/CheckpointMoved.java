package Messages.Actions;

import Messages.Message;
import Messages.MessageBody;

public class CheckpointMoved extends Message {
    public CheckpointMoved(int id, int x, int y){
        Integer[] daten = new Integer[3];
        daten[0] = id;
        daten[1] = x;
        daten[2] = y;

        setMessageType("CheckpointMoved");
        setMessageBody(new MessageBody(daten));
    }
}

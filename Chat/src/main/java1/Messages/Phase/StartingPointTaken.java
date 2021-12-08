package Messages.Phase;

import Messages.Message;
import Messages.MessageBody;

public class StartingPointTaken extends Message {

    public StartingPointTaken(int x, int y, int id){
        super();
        Integer[] daten = new Integer[3];
        daten[0] = x;
        daten[1] = y;
        daten[2] = id;

        setMessageType("StartingPointTaken");
        setMessageBody(new MessageBody(daten));
    }
}

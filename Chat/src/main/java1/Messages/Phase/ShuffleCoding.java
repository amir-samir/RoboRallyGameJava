package Messages.Phase;

import Messages.Message;
import Messages.MessageBody;

public class ShuffleCoding extends Message {

    public ShuffleCoding(int id){
        super();
        Integer[] daten = new Integer[1];
        daten[0] = id;

        setMessageType("ShuffleCoding");
        setMessageBody(new MessageBody(daten));
    }
}

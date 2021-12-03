package Messages.Actions;

import Messages.Message;
import Messages.MessageBody;

public class Reboot extends Message {

    public Reboot(int id){
        Integer[] daten = new Integer[1];
        daten[0] = id;

        setMessageType("Reboot");
        setMessageBody(new MessageBody(daten));
    }

}

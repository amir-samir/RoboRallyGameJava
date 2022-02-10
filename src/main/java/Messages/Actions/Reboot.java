package Messages.Actions;

import Messages.Message;
import Messages.MessageBody;
/**
 * this class implements the specific MessageType "Reboot".
 * a message will be sent if a robot has to reboot.
 * @author Luca Weyhofen
 */
public class Reboot extends Message {

    public Reboot(int id){
        Integer[] daten = new Integer[1];
        daten[0] = id;

        setMessageType("Reboot");
        setMessageBody(new MessageBody(daten));
    }

}

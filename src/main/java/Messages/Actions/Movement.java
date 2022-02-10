package Messages.Actions;

import Messages.Message;
import Messages.MessageBody;
/**
 * this class implements the specific messageType "Movement".
 * it is used to send the action of movement from a robot.
 * @author Luca Weyhofen
 */
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

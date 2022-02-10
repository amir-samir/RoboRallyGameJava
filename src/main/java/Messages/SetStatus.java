package Messages;
/**
 * this class implements the specific messageType "SetStatus".
 * It is used to send player status(ready/not ready)messages from the server to the client.
 * @author Luca Weyhofen
 */
public class SetStatus extends Message {

    public SetStatus(boolean ready){
        Boolean[] daten = {ready};
        String[] keys = {"ready"};

        MessageBody messagebody = new MessageBody(daten);
        messagebody.setKeys(keys);

        setMessageType("SetStatus");
        setMessageBody(messagebody);
    }
}

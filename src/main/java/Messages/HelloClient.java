package Messages;
/**
 * this class implements the specific messageType "HelloClient".
 * it is used to send chat messages from the client to the server and from server to client.
 * @author Luca Weyhofen
 */
public class HelloClient extends Message{

    public HelloClient(String protocol){

        String[] daten = new String[1];
        daten[0] = protocol;
        String keys[] = {"protocol"};

        messageType = "HelloClient";

        MessageBody b = new MessageBody(daten);
        messageBody  = b;
        b.setKeys(keys);
    }
}

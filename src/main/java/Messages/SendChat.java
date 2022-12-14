package Messages;

/**
 * this class implements the specific messageType "SendChat".
 * it is used to send chat messages from the client to the server.
 * @author Luca Weyhofen
 */
public class SendChat extends Message{

    public SendChat(String message, int id){
        Object[] daten = new Object[2];
        daten[0] = message;
        daten[1] = id;

        this.setMessageType("SendChat");
        this.setMessageBody(new MessageBody(daten));
    }
}

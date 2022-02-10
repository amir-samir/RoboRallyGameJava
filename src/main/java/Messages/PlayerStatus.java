package Messages;
/**
 * this implements the specific messageType "PlayerStatus".
 * it is used to
 * @author Luca Weyhofen
 */
public class PlayerStatus extends Message{
    public PlayerStatus(int clientID, boolean ready){
        super();

        Object[] inhalt = new Object[2];
        inhalt[0] = clientID;
        inhalt[1] = ready;

        this.setMessageType("PlayerStatus");
        this.setMessageBody(new MessageBody(inhalt));
    }
}

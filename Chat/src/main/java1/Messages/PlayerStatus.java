package Messages;

public class PlayerStatus extends Message{
    public PlayerStatus(int clientID, boolean ready){
        super();

        Object[] inhalt = new Object[2];
        inhalt[0] = clientID;
        inhalt[1] = ready;

        messageType = "PlayerStatus";
        messageBody = new MessageBody(inhalt);
    }
}

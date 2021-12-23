package game.Messages;

public class ConfirmPlayerStatus extends Message{
    ConfirmPlayerStatus(int clientID, boolean ready){
        super();

        Object[] inhalt = new Object[2];
        inhalt[1] = clientID;
        inhalt[2] = ready;


        messageType = "PlayerStatus";
        messageBody = new MessageBody(inhalt);
    }
}

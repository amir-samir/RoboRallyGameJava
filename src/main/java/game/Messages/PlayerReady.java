package game.Messages;

public class PlayerReady extends Message{
    PlayerReady(boolean ready){
        super();

        Boolean[] inhalt = new Boolean[1];
        inhalt[1] = true;


        messageType = "SetStatus";
        messageBody = new MessageBody(inhalt);
    }
}

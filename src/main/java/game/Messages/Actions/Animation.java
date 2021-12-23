package game.Messages.Actions;
import game.Messages.Message;
import game.Messages.MessageBody;

public class Animation extends Message {
    public Animation(String type){
        Object[] daten = new Object[1];
        daten[0] = type;

        setMessageType("Animation");
        setMessageBody(new MessageBody(daten));
    }
}

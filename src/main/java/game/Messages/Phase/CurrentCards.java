package game.Messages.Phase;

import game.Messages.Message;
import game.Messages.MessageBody;

public class CurrentCards extends Message {

    public CurrentCards(Object[] cards){
        setMessageType("CurrentCards");
        setMessageBody(new MessageBody(cards));
    }
}

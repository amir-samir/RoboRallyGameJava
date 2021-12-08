package Messages.Phase;

import Messages.Message;
import Messages.MessageBody;

public class CurrentCards extends Message {

    public CurrentCards(Object[] cards){
        setMessageType("CurrentCards");
        setMessageBody(new MessageBody(cards));
    }
}

package Messages.Phase;

import Messages.Message;
import Messages.MessageBody;

public class CardsYouGotNow extends Message {

    public CardsYouGotNow(String[] cards){
        setMessageType("CardsYouGotNow");
        setMessageBody(new MessageBody(cards));
    }
}

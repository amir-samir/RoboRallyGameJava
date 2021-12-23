package game.Messages.Phase;

import game.Messages.Message;
import game.Messages.MessageBody;

public class CardsYouGotNow extends Message {

    public CardsYouGotNow(String[] cards){
        setMessageType("CardsYouGotNow");
        setMessageBody(new MessageBody(cards));
    }
}

package Messages.Phase;

import Messages.Message;
import Messages.MessageBody;

/**
 * CardsYouGotNow Nachricht
 * @author Mateo Pranjic
 */
public class CardsYouGotNow extends Message {

    public CardsYouGotNow(String[] cards){
        setMessageType("CardsYouGotNow");
        setMessageBody(new MessageBody(new Object[]{cards}));
    }
}

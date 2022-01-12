package Messages.Phase;

import Messages.ActiveCards;
import Messages.Message;
import Messages.MessageBody;

public class CurrentCards extends Message {

    public CurrentCards(ActiveCards[] cards){

        Object[] inhalt = new Object[1];
        inhalt[0] = cards;

        this.setMessageType("CurrentCards");
        MessageBody mb = new MessageBody(inhalt);
        Object[] keys = {"ActiveCards"};
        mb.setKeys(keys);
        this.setMessageBody(mb);
    }
}

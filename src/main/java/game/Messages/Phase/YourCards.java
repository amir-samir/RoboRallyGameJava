package game.Messages.Phase;

import game.Messages.Message;
import game.Messages.MessageBody;

public class YourCards extends Message {

    public YourCards(String[] cards){
        super();
        Object[] daten = new Object[1];
        daten[0] = cards;

        setMessageType("YourCards");
        setMessageBody(new MessageBody(daten));
    }

}

package game.Messages.Phase;

import game.Messages.Message;
import game.Messages.MessageBody;

public class NotYourCards extends Message {

    public NotYourCards(int id, int numberOfCards){
        super();
        Integer[] daten = new Integer[2];
        daten[0] = id;
        daten[1] = numberOfCards;

        setMessageType("NotYourCards");
        setMessageBody(new MessageBody(daten));
    }
}

package game.Messages.Actions;

import game.Messages.Message;
import game.Messages.MessageBody;

public class GameFinished extends Message {

    public GameFinished(int id){
        Integer[] daten = new Integer[1];
        daten[0] = id;

        setMessageType("GameFinished");
        setMessageBody(new MessageBody(daten));
    }
}

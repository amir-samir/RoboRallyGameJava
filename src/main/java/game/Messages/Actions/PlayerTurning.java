package game.Messages.Actions;

import game.Messages.Message;
import game.Messages.MessageBody;

public class PlayerTurning extends Message {

    public PlayerTurning(int id, String rotation){
        Object[] daten = new Object[2];
        daten[0] = id;
        daten[1] = rotation;

        setMessageType("PlayerTurning");
        setMessageBody(new MessageBody(daten));
    }
}

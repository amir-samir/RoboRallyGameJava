package game.Messages.Actions;

import game.Messages.Message;
import game.Messages.MessageBody;

public class Reboot extends Message {

    public Reboot(int id){
        Integer[] daten = new Integer[1];
        daten[0] = id;

        setMessageType("Reboot");
        setMessageBody(new MessageBody(daten));
    }

}

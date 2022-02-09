package Messages.Actions;

import Messages.Message;
import Messages.MessageBody;

public class RegisterChosen extends Message {
    public RegisterChosen(int id, int register){
        Integer[] daten = new Integer[2];
        daten[0] = id;
        daten[1] = register;

        setMessageType("RegisterChosen");
        setMessageBody(new MessageBody(daten));
    }
}

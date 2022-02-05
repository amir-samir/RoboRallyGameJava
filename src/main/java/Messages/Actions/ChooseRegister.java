package Messages.Actions;

import Messages.Message;
import Messages.MessageBody;

public class ChooseRegister extends Message {
    public ChooseRegister(int register){
        Object[] daten = new Object[1];
        daten[0] = register;

        setMessageType("ChooseRegister");
        setMessageBody(new MessageBody(daten));
    }
}

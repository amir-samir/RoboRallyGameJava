package game.Messages;

public class SetStatus extends Message {

    public SetStatus(boolean ready){
        Boolean[] daten = {ready};
        String[] keys = {"ready"};

        MessageBody messagebody = new MessageBody(daten);
        messagebody.setKeys(keys);

        setMessageType("SetStatus");
        setMessageBody(messagebody);
    }
}

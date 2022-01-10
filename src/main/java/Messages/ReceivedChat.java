package Messages;

public class ReceivedChat extends Message{

    public ReceivedChat(String message, int from, boolean isPrivate){
        super();

        Object[] inhalt = new Object[3];
        inhalt[0] = message;
        inhalt[1] = from;
        inhalt[2] = isPrivate;

        messageType = "ReceivedChat";
        messageBody = new MessageBody(inhalt);
    }
}

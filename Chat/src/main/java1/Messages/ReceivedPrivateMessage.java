package Messages;

public class ReceivedPrivateMessage extends Message{
    ReceivedPrivateMessage(String message, int from, boolean isPrivate ){
        super();

        Object[] inhalt = new Object[3];
        inhalt[1] = message;
        inhalt[2] = from;
        inhalt[3] = isPrivate;


        messageType = "ReceivedChat";
        messageBody = new MessageBody(inhalt);
    }
}

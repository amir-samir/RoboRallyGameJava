package Messages;

public class PrivateMessage extends Message{
    PrivateMessage(String message,int to){
        super();

        Object[] inhalt = new Object[2];
        inhalt[1] = message;
        inhalt[2] = to;

        messageType = "SendChat";
        messageBody = new MessageBody(inhalt);
    }
}

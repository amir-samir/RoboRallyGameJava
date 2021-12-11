package Messages;

public class HelloServer extends Message{

   public HelloServer(String group, boolean isAI, String protocol){
        super();

        Object[] inhalt = new Object[3];
        inhalt[0] = group;
        inhalt[1] = isAI;
        inhalt[2] = protocol;

        messageType = "HelloServer";
        messageBody = new MessageBody(inhalt);
    }
}

package Messages;

public class HelloServer extends Message{

    HelloServer(String group, boolean isAI, String protocol){
        super();

        Object[] inhalt = new Object[3];
        inhalt[1] = group;
        inhalt[2] = isAI;
        inhalt[3] = protocol;

        messageType = "Hello Server";
        messageBody = new MessageBody(inhalt);
    }
}

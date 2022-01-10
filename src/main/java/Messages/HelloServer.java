package Messages;

public class HelloServer extends Message{

   public HelloServer(String group, boolean isAI, String protocol) {
       super();

       Object[] inhalt = new Object[3];
       inhalt[0] = group;
       inhalt[1] = isAI;
       inhalt[2] = protocol;
       String[] keys = {"group", "isAI", "protocol", "clientID"};

       messageType = "HelloServer";
       MessageBody b = new MessageBody(inhalt);
       messageBody = b;
       b.setKeys(keys);
   }
}

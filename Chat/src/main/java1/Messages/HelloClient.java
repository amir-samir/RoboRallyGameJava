package Messages;

public class HelloClient extends Message{

    public HelloClient(String protocol){

        String[] daten = new String[1];
        daten[0] = protocol;
        String keys[] = {"protocol"};

        messageType = "HelloClient";

        MessageBody b = new MessageBody(daten);
        messageBody  = b;
        b.setKeys(keys);

    }


}

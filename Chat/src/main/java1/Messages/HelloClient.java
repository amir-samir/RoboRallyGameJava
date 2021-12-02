package Messages;

public class HelloClient extends Message{

    String protocol;

    public HelloClient(String protocol){

        String[] daten = new String[1];
        daten[0] = protocol;

        messageType = "HelloClient";
        messageBody = new MessageBody(daten);
    }


}

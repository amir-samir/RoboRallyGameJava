package Messages;

public class Error1 extends Message{

    public Error1(String error){
        String[] daten = new String[1];
        daten[0] = error;

        messageType = "Error1";
        messageBody = new MessageBody(daten);
    }
}

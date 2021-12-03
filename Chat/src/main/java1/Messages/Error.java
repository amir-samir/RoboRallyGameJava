package Messages;

public class Error extends Message{

    public Error(String error){
        String[] daten = new String[1];
        daten[0] = error;

        messageType = "Error";
        messageBody = new MessageBody(daten);
    }
}

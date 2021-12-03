package Messages;

public class Welcome extends Message {

    public Welcome(int id){

        Object[] daten = new Object[1];
        daten[0] = id;

        messageType = "Welcome";
        messageBody = new MessageBody(daten);
    }
}

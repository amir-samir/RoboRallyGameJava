package Messages;

public class Alive extends Message {

    public Alive(){
        super();
        messageType = "Alive";
        Object[] daten = new Object[1];
        daten[0] = "{}" ;
        messageBody = new MessageBody(daten);
    }

}

package Messages;

public class CurrentPlayer extends Message {

    public CurrentPlayer(int id){
        Integer[] daten = new Integer[1];
        daten[0] = id;

        messageType = "CurrentPlayer";
        messageBody = new MessageBody(daten);
    }
}

package Messages;

public class ActivePhase extends Message {

    public ActivePhase(int phase){
        Integer[] daten = new Integer[1];
        daten[0] = phase;

        messageType = "ActivePhase";
        messageBody = new MessageBody(daten);
    }

}

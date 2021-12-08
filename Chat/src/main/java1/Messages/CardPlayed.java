package Messages;

public class CardPlayed extends Message{

    public CardPlayed(int id, String card){
        Object[] daten = new Object[2];
        daten[0] = id;
        daten[1] = card;

        messageType = "CardPlayed";
        messageBody = new MessageBody(daten);
    }
}

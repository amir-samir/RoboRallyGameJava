package game.Messages;

public class PlayCard extends Message {

    public PlayCard(String card){
        String[] daten = new String[1];
        daten[0] = card;

        messageType = "PlayCard";
        messageBody = new MessageBody(daten);
    }
}

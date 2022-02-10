package Messages;
/**
 * this implements the specific messageType "PlayCard".
 * it is used to notify the server of cards that a client wants to play.
 * @author Luca Weyhofen
 */
public class PlayCard extends Message {

    public PlayCard(String card){
        String[] daten = new String[1];
        daten[0] = card;

        messageType = "PlayCard";
        messageBody = new MessageBody(daten);
    }
}

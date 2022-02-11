package Messages;

/**
 * Diese Klasse stellt eine Verbindung zwischen einem Spieler und einer einzelnen gelegten Karte dar.
 * Sie wird verwendet, um einzelne Nachrichten zu verschicken.
 *
 * @author Dairen Gonschior
 */
public class CurrentCardObject {

    int clientID;
    String card;

    public CurrentCardObject(int id, String card){
        this.clientID = id;
        this.card = card;
    }
}

package Messages;

/**
 * ActiveCards Nachricht
 * @author Mateo Pranjic
 */
public class ActiveCards {
    int clientID;
    String card;

    public ActiveCards(int clientID, String card){
        this.clientID = clientID;
        this.card = card;

    }
}

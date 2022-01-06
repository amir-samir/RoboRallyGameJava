import game.Card.DamageCards;
import lombok.Data;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author:Yilu
 *
 */


public class Game {

    private final Server SERVER;
    private HashMap<Integer, ClientHandler> users;
    //private Board board? Map map?

    private List<DamageCards> damageCards;


    public Game(Server server, HashMap<Integer, ClientHandler> hashMap, ){
        SERVER = server;
        users = hashMap;
    }

    public List<DamageCards> getTwoDamageCards() {
        List<DamageCards> damageCards = new ArrayList<>();
        return damageCards;
    }
}


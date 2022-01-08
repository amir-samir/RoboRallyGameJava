import game.Board.Board;
import game.Card.DamageCards;
import game.Messages.ActivePhase;
import game.Messages.CurrentPlayer;
import game.Messages.Phase.SetStartingPoint;
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
    private List<ClientHandler> verbindungen;

    int activePhase;
    int activePlayer;
    String activeMap;

    //private Board board? Map map?

    private List<DamageCards> damageCards;


    public Game(Server server, HashMap<Integer, ClientHandler> hashMap, List<ClientHandler> verbindungen, String activeMap){
        this.SERVER = server;
        this.users = hashMap;
        this.verbindungen = verbindungen;
        this.activePhase = 0;
        this.activePlayer = 0;
        this.activeMap = activeMap;
        startGame();
    }

    public void startGame(){

        CurrentPlayer currentPlayer = new CurrentPlayer(verbindungen.get(activePlayer).ID);
        currentPlayer.getMessageBody().setKeys(new String[]{"clientID"});
        SERVER.sendMessageForAllUsers(currentPlayer);

        if(this.activePhase == 0){
            aufbauPhase();
        } else if (this.activePhase == 1){
            upgradePhase();
        } else if (this.activePhase == 2){
            programmierPhase();
        } else if (this.activePhase == 3){
            aktivierungsPhase();
        } else return;
    }

    public void aufbauPhase(){
        ActivePhase activePhase = new ActivePhase(this.activePhase);
        activePhase.getMessageBody().setKeys(new String[]{"phase"});
        SERVER.sendMessageForSingleClient(activePhase, verbindungen.get(activePlayer));
    }

    public void upgradePhase(){

    }

    public void programmierPhase(){

    }

    public void aktivierungsPhase(){

    }

    public void setStartingPoint(int x, int y){

    }

    public ClientHandler getFirstPlayer(){
        return verbindungen.get(0);
    }

    public List<DamageCards> getTwoDamageCards() {
        List<DamageCards> damageCards = new ArrayList<>();
        return damageCards;
    }
}


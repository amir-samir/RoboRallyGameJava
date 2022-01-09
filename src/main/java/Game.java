import game.Board.Board;
import game.Card.DamageCards;
import game.Maps.DizzyHighway;
import game.Messages.ActivePhase;
import game.Messages.CurrentPlayer;
import game.Messages.Error1;
import game.Robot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Game {

    private final Server SERVER;
    private HashMap<Integer, ClientHandler> users;
    private List<ClientHandler> verbindungen;

    int activePhase;
    int activePlayer;
    String activeMap;
    Robot[] figuren;
    Board board = new DizzyHighway();


    private List<DamageCards> damageCards;


    public Game(Server server, HashMap<Integer, ClientHandler> hashMap, List<ClientHandler> verbindungen, String activeMap, Robot[] figuren){
        this.SERVER = server;
        this.users = hashMap;
        this.verbindungen = verbindungen;
        this.activePhase = 0;
        this.activePlayer = 0;
        this.activeMap = activeMap;
        this.figuren = figuren;

        startGame();
    }

    public void startGame(){
        if(this.activePhase == 0){
            if (!aufbauPhaseFertig()) {
                sendActivePlayer();
                aufbauPhase();
            } else {
                activePhase = 2;
                startGame();
            }
        } else if (this.activePhase == 1){
            sendActivePlayer();
            upgradePhase();
        } else if (this.activePhase == 2){
            sendActivePlayer();
            programmierPhase();
        } else if (this.activePhase == 3){
            sendActivePlayer();
            aktivierungsPhase();
        }
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

    public void setStartingPoint(int x, int y, ClientHandler clientHandler) {
        if (!verbindungen.get(activePlayer).equals(clientHandler) || activePhase != 0){
            sendError("Du kannst aktuell keine Startposition wählen.", clientHandler);
            return;
        }
        if (board.getMap()[x][y].get(0).getType() == "StartPoint") {
            for (int i = 0; i < figuren.length; i++) {
                if (figuren[i] != null) {
                    if (feldBelegt(x, y, i)) {
                        sendError("Der gewählte Startpunkt ist ungültig.", clientHandler);
                        return;
                    }
                }
            }
            figuren[clientHandler.figure].setX(x);
            figuren[clientHandler.figure].setY(y);
            SERVER.validStartingPoint(x, y, clientHandler);
            nextPlayerAufbauPhase();
            startGame();
        } else {
            sendError("Der gewählte Startpunkt ist ungültig.", clientHandler);
        }
    }

    public boolean feldBelegt(int x, int y, int i){
        if (figuren[i].getX() == x && figuren[i].getY() == y){
            return true;
        } else return false;
    }

    public boolean aufbauPhaseFertig(){
        for (int i = 0; i < figuren.length; i++){
            if (figuren[0].getX() == -1 && figuren[i] != null){
                return false;
            }
        }
        return true;
    }

    public void sendActivePlayer(){
        CurrentPlayer currentPlayer = new CurrentPlayer(verbindungen.get(activePlayer).ID);
        currentPlayer.getMessageBody().setKeys(new String[]{"clientID"});
        SERVER.sendMessageForAllUsers(currentPlayer);
    }

    public void nextPlayerAufbauPhase(){
        activePlayer += 1;
        if (verbindungen.size() == activePlayer){
            activePlayer = 0;
        }
    }

    public void sendError(String nachricht, ClientHandler clientHandler){
        Error1 error1 = new Error1(nachricht);
        error1.getMessageBody().setKeys(new String[]{"error"});
        SERVER.sendMessageForSingleClient(error1, clientHandler);
    }

    public ClientHandler getFirstPlayer(){
        return verbindungen.get(0);
    }

    public List<DamageCards> getTwoDamageCards() {
        List<DamageCards> damageCards = new ArrayList<>();
        return damageCards;
    }
}


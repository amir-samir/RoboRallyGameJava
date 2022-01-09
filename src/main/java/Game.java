import game.Board.Board;
import game.Card.Cards;
import game.Card.DamageCards;
import game.Maps.DizzyHighway;
import game.Messages.ActivePhase;
import game.Messages.CurrentPlayer;
import game.Messages.Error1;
import game.Messages.Phase.*;
import game.Robot;

import java.nio.file.AccessDeniedException;
import java.util.*;

public class Game {

    private final Server SERVER;
    private HashMap<Integer, ClientHandler> users;
    private List<ClientHandler> verbindungen;

    int activePhase;
    int activePlayer;
    String activeMap;
    Robot[] figuren;
    boolean timerActivated;
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
        this.timerActivated = false;

        initializeDeck();
        startGame();
    }

    public void initializeDeck(){

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
        ActivePhase activePhase = new ActivePhase(this.activePhase);
        activePhase.getMessageBody().setKeys(new String[]{"phase"});
        SERVER.sendMessageForAllUsers(activePhase);

        for (Robot robot: figuren){
            if (robot != null) {

                robot.drawHandCards();

                YourCards yourCards = new YourCards(convertListToArray(robot.getHandCards()));
                yourCards.getMessageBody().setKeys(new String[]{"cardsInHand"});
                SERVER.sendMessageForSingleClient(yourCards, users.get(robot.getGamerID()));

                NotYourCards notYourCards = new NotYourCards(robot.getGamerID(), robot.getHandCards().size());
                notYourCards.getMessageBody().setKeys(new String[]{"clientID", "cardsInHand"});
                SERVER.sendMessageForAllUsers(notYourCards);
            }
        }
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
            if (figuren[i] != null) {
                if (figuren[i].getX() == -1 && figuren[i] != null) {
                    return false;
                }
            }
        }
        return true;
    }

    public void handleSelectedCard(String card, int register, ClientHandler clientHandler){
        if (figuren[clientHandler.figure].cardIntoRegister(card, register)){
            System.out.println("Hier bin ich angekommen!!!");
            boolean filled = true;
            if(card == null) filled = false;
            CardSelected cardSelected = new CardSelected(clientHandler.ID, register, filled);
            cardSelected.getMessageBody().setKeys(new String[]{"clientID", "register", "filled"});
            SERVER.sendMessageForAllUsers(cardSelected);
            if (figuren[clientHandler.figure].allRegistersFilled() && !timerActivated){
                sendSelectionFinished(clientHandler);
                startTimer();
                timerActivated = true;
            }
        } else {
            sendError("Dies ist nicht möglich.", clientHandler);
        }
    }

    public void startTimer(){
        TimerStarted timerStarted = new TimerStarted();
        SERVER.sendMessageForAllUsers(timerStarted);

        OurTimer ourTimer = new OurTimer(30, this);
    }

    public void timerEnded(){
        System.out.println("Timer Ended");
        ArrayList<Integer> schlafmützen = checkWhoIsntDone();
        Integer[] zuLangsameSpieler  = new Integer[schlafmützen.size()];
        for (int i = 0; i < schlafmützen.size(); i++){
            zuLangsameSpieler[i] = schlafmützen.get(i);
        }

        TimerEnded timerEnded = new TimerEnded(zuLangsameSpieler);
        timerEnded.getMessageBody().setKeys(new String[]{"clientIDs"});
        SERVER.sendMessageForAllUsers(timerEnded);

        fillRegisters(zuLangsameSpieler);
    }

    public void fillRegisters(Integer[] integers){
        for (Integer integer: integers){
            for (int i = 0; i < figuren.length; i++){
                if (figuren[i].getGamerID() == (int) integer){
                    figuren[i].fillRegisters();
                    String[] karten = new String[5];
                    for(int u = 0; u < karten.length; i++){
                        karten[u] = figuren[i].getRegister()[u].getName();
                    }
                    CardsYouGotNow cardsYouGotNow = new CardsYouGotNow(karten);
                    cardsYouGotNow.getMessageBody().setKeys(new String[]{"cards"});
                    SERVER.sendMessageForSingleClient(cardsYouGotNow, users.get(integer));
                }
            }
        }
    }

    public void sendSelectionFinished(ClientHandler clientHandler){
        SelectionFinished selectionFinished = new SelectionFinished(clientHandler.ID);
        selectionFinished.getMessageBody().setKeys(new String[] {"clientID"});
        SERVER.sendMessageForAllUsers(selectionFinished);
    }

    public ArrayList<Integer> checkWhoIsntDone(){
        ArrayList<Integer> schlafmützen = new ArrayList<Integer>();
        for (Robot robot: figuren){
            if (robot.getAbleToFillRegisters()){
                schlafmützen.add(robot.getGamerID());
                robot.setAbleToFillRegisters(false);
            }
        }
        return schlafmützen;
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

    public String[] convertListToArray(ArrayList cards){
        String[] newCards = new String[cards.size()];
        for (int i = 0; i < newCards.length; i++){
            Cards card = (Cards) cards.get(i);
            newCards[i] = card.getName();
        }
        return newCards;
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


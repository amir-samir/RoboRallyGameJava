import Messages.*;
import Messages.Phase.*;

import java.util.*;
import java.util.List;

import static java.lang.Math.abs;

public class Game {

    private final Server SERVER;
    private HashMap<Integer, ClientHandler> users;
    private List<ClientHandler> verbindungen;

    int activePhase;
    int activePlayer;
    int activeRegister;
    String activeMap;
    Robot[] figuren;
    boolean timerActivated;

    Board board;


    private CardsForGame cardsForGame;


    public Game(Server server, HashMap<Integer, ClientHandler> hashMap, List<ClientHandler> verbindungen, String activeMap, Robot[] figuren){
        this.SERVER = server;
        this.users = hashMap;
        this.verbindungen = verbindungen;
        this.activePhase = 0;
        this.activePlayer = 0;
        this.activeRegister = 0;
        this.activeMap = activeMap;
        this.figuren = figuren;
        this.timerActivated = false;
        this.cardsForGame = new CardsForGame();

        switch (activeMap){
            case "DizzyHighway":
                board = new DizzyHighway();
                break;
            case "LostBearings":
                board = new LostBearings();
                break;
            case "Extra Crispy":
                board = new ExtraCrispy();
                break;
            case "DeathTrap":
                board = new DeathTrap();
                break;
        }
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
        ArrayList<ClientHandler> reihenfolge = reihenfolgeBestimmen();
        for (int i = 0; i < 5; i++) {
            currentCardVerschicken();
            for (ClientHandler clientHandler : reihenfolge) {
                Robot robot = figuren[clientHandler.figure];

                Cards card = robot.getRegister()[activeRegister];
                card.effect(robot, SERVER);
            }
            aktiviereMapElemente();
            activeRegister += 1;
        }
        beendeAktivierungsPhase();
    }

    public ArrayList<ClientHandler> reihenfolgeBestimmen(){
        ArrayList<ClientHandler> reihenfolge = new ArrayList<ClientHandler>();
        ArrayList<Robot> falscheReihenfolge = new ArrayList<Robot>();

        int antennaX = board.searchX("Antenna");
        int antennaY = board.searchY("Antenna");
        for (Robot robot: figuren){
            if (robot != null) {
                int robotX = robot.getX();
                int robotY = robot.getY();
                int entfernungX = abs(robotX - antennaX);
                int entfernungY = abs(robotY - antennaY);
                int entfernungGesamt = entfernungX + entfernungY;
                robot.setEntfernungZurAntenne(entfernungGesamt);
                falscheReihenfolge.add(robot);
            }
        }

        int zähler = falscheReihenfolge.size();
        for (int i = 0; i < zähler; i++){
            Robot niedrigsteEntfernung = falscheReihenfolge.get(0);
            for (Robot robot : falscheReihenfolge) {
                if (niedrigsteEntfernung.getEntfernungZurAntenne() > robot.getEntfernungZurAntenne()){
                    niedrigsteEntfernung = robot;
                }
            }
            for (int u = 0; u < falscheReihenfolge.size(); u++){
                if (falscheReihenfolge.get(u).equals(niedrigsteEntfernung)){
                    Robot r =  falscheReihenfolge.remove(u);
                    reihenfolge.add(users.get(r.getGamerID()));
                }
            }
        }

        return reihenfolge;
    }

    public void aktiviereMapElemente(){
        int i = 0;
        activateBlueConveyor();
        activateGreenConveyor();
        activatePushPanel();
        activateGear();
        activateBoardLaser();
        activateRobotLaser();
        activateCheckpoint();
    }

    public void activateBlueConveyor(){
        for (Robot robot: figuren){
            if (robot != null){
                for (BoardElement list: board.getMap()[robot.getX()][robot.getY()]){
                    if (list.getType().equals("ConveyorBelt")){
                        if (list.getSpeed() == 2){
                            list.effect(robot, SERVER);
                        }
                    }
                }
            }
        }
    }

    public void activateGreenConveyor(){
        for (Robot robot: figuren){
            if (robot != null){
                for (BoardElement list: board.getMap()[robot.getX()][robot.getY()]){
                    if (list.getType().equals("ConveyorBelt")){
                        if (list.getSpeed() == 1){
                            list.effect(robot, SERVER);
                        }
                    }
                }
            }
        }
    }

    public void activatePushPanel(){

    }

    public void activateGear(){

    }

    public void activateBoardLaser(){

    }

    public void activateRobotLaser(){

    }

    public void activateCheckpoint(){

    }

    public void beendeAktivierungsPhase(){
        for (Robot robot: figuren){
            if (robot != null){
                robot.clearRegister();
                robot.clearHandcards();
                robot.setAbleToFillRegisters(true);
            }
        }
        timerActivated = false;
        activePhase = 2;
        activeRegister = 0;
        startGame();

    }

    public void currentCardVerschicken(){
        int anzahlSpieler = 0;
        for (Robot robot: figuren){
            if (robot != null){
                anzahlSpieler += 1;
            }
        }
        ActiveCards[] activeCards = new ActiveCards[anzahlSpieler];
        int u = 0;
        for (int i = 0; i < figuren.length; i++){
            if (figuren[i] != null){
                activeCards[u] = new ActiveCards(figuren[i].getGamerID(), figuren[i].getRegister()[activeRegister].getName());
                u++;
            }
        }
        CurrentCards currentCards = new CurrentCards(activeCards);
        currentCards.getMessageBody().setKeys(new String[]{"activeCards"});
        SERVER.sendMessageForAllUsers(currentCards);
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
            if (activeMap.equals("Death Trap")){
                figuren[clientHandler.figure].setDirection("left");
            } else figuren[clientHandler.figure].setDirection("right");
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
        ArrayList<Integer> schlafmützen = checkWhoIsntDone();
        Integer[] zuLangsameSpieler  = new Integer[schlafmützen.size()];
        for (int i = 0; i < zuLangsameSpieler.length; i++){
            zuLangsameSpieler[i] = schlafmützen.get(i);
        }
        TimerEnded timerEnded = new TimerEnded(zuLangsameSpieler);
        timerEnded.getMessageBody().setKeys(new String[]{"clientIDs"});
        SERVER.sendMessageForAllUsers(timerEnded);
        fillRegisters(zuLangsameSpieler);
        activePhase = 3;
        startGame();
    }

    public void fillRegisters(Integer[] integers) {
        for (Integer integer : integers) {
            if (integer != null) {
                for (int i = 0; i < figuren.length; i++) {
                    if (figuren[i] != null) {
                        if (figuren[i].getGamerID() == integer) {
                            figuren[i].fillRegisters();
                            String[] karten = new String[5];
                            for (int u = 0; u < karten.length; u++) {
                                karten[u] = figuren[i].getRegister()[u].getName();
                            }
                            CardsYouGotNow cardsYouGotNow = new CardsYouGotNow(karten);
                            cardsYouGotNow.getMessageBody().setKeys(new String[]{"cards"});
                            System.out.println(Adopter.javabeanToJson(cardsYouGotNow));
                            SERVER.sendMessageForSingleClient(cardsYouGotNow, users.get(integer));
                        }
                    }
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
        for (int i = 0; i < figuren.length; i++) {
            if (figuren[i] != null) {
                if (figuren[i].getAbleToFillRegisters()) {
                    schlafmützen.add(figuren[i].getGamerID());
                    figuren[i].setAbleToFillRegisters(false);
                } else {
                }
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


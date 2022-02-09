import Messages.*;
import Messages.Actions.*;
import Messages.Phase.*;

import java.util.*;
import java.util.List;

import static java.lang.Math.abs;

public class Game {

    private final Server SERVER;
    private UpgradeShop upgradeShop;
    private HashMap<Integer, ClientHandler> users;
    private List<ClientHandler> verbindungen;
    private List<ClientHandler> upgradeReihenfolge;
    private List<ClientHandler> adminPrivilege;

    private int activePhase;
    private int activePlayer;
    private int activePlayerID;
    private int activeRegister;
    private String activeMap;
    private Robot[] figuren;
    private boolean timerActivated;
    private int neededCheckpoints;
    private String currentDamageCard = null;
    private boolean chooseDamageCard = true;

    Board board;


    private CardsForGame cardsForGame;


    public Game(Server server, HashMap<Integer, ClientHandler> hashMap, List<ClientHandler> verbindungen, String activeMap, Robot[] figuren){
        this.SERVER = server;
        this.users = hashMap;
        this.verbindungen = verbindungen;
        this.upgradeShop = new UpgradeShop(verbindungen.size());
        this.adminPrivilege = new ArrayList<>();
        this.upgradeReihenfolge = null;
        this.activePhase = 0;
        this.activePlayer = 0;
        this.activePlayerID = 0;
        this.activeRegister = 0;
        this.activeMap = activeMap;
        this.figuren = figuren;
        this.timerActivated = false;
        this.cardsForGame = new CardsForGame();

        if (activeMap.equals("DizzyHighway")){
            neededCheckpoints = 1;
        } else if (activeMap.equals("LostBearings") || activeMap.equals("ExtraCrispy") || activeMap.equals("Twister")){
            neededCheckpoints = 4;
        } else if (activeMap.equals("DeathTrap")){
            neededCheckpoints = 5;
        }

        switch (activeMap){
            case "DizzyHighway":
                board = new DizzyHighway();
                break;
            case "LostBearings":
                board = new LostBearings();
                break;
            case "ExtraCrispy":
                board = new ExtraCrispy();
                break;
            case "DeathTrap":
                board = new DeathTrap();
                break;
            case "Twister":
                //board = new Twister();
                //break;
        }
        startGame();
    }

    public void startGame(){
        try {
            if (this.activePhase == 0) {
                if (!aufbauPhaseFertig()) {
                    sendActivePlayer();
                    aufbauPhase();
                } else {
                    activePhase = 1;
                    startGame();
                }
            } else if (this.activePhase == 1) {
                sendActivePhase();
                prepareUpgradeShop();
                this.upgradeReihenfolge = reihenfolgeBestimmen();
                upgradePhase();
            } else if (this.activePhase == 2) {
                programmierPhase();
            } else if (this.activePhase == 3) {
                aktivierungsPhase();
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendActivePhase(){
        ActivePhase activePhase = new ActivePhase(this.activePhase);
        activePhase.getMessageBody().setKeys(new String[]{"phase"});
        SERVER.sendMessageForAllUsers(activePhase);
    }

    public void aufbauPhase(){
        ActivePhase activePhase = new ActivePhase(this.activePhase);
        activePhase.getMessageBody().setKeys(new String[]{"phase"});
        SERVER.sendMessageForSingleClient(activePhase, verbindungen.get(activePlayer));
    }

    public void prepareUpgradeShop(){
        if (this.upgradeShop.isSomebodyBoughtOne()){
            ArrayList<String> upgradeCards = new ArrayList<>();
            String[] karten;
            for (int i = 0; i < upgradeShop.getUpgradeCards().length; i++){
                if (upgradeShop.getUpgradeCards()[i] == null){
                     upgradeShop.getUpgradeCards()[i] = cardsForGame.upgradeCards.remove(0);
                     upgradeCards.add(upgradeShop.getUpgradeCards()[i].getName());
                }
            }
            karten = new String[upgradeCards.size()];
            for (int i = 0; i < karten.length; i++){
                karten[i] = upgradeCards.remove(0);
            }
            RefillShop refillShop = new RefillShop(karten);
            refillShop.getMessageBody().setKeys(new String[]{"cards"});
            SERVER.sendMessageForAllUsers(refillShop);
        } else {
            cardsForGame.upgradeCards = this.upgradeShop.exchangeShop(cardsForGame.upgradeCards);
            String[] karten = new String[upgradeShop.getUpgradeCards().length];
            for (int i = 0; i < karten.length; i++){
                karten[i] = upgradeShop.getUpgradeCards()[i].getName();
            }
            ExchangeShop exchangeShop = new ExchangeShop(karten);
            exchangeShop.getMessageBody().setKeys(new String[]{"cards"});
            SERVER.sendMessageForAllUsers(exchangeShop);
        }
        upgradeShop.setSomebodyBoughtOne(false);
    }

    public void upgradePhase(){
        this.activePlayerID = 0;
        if (this.upgradeReihenfolge.size() != 0){
            ClientHandler activePlayer = upgradeReihenfolge.remove(0);
            Robot activeRobot = figuren[activePlayer.figure];
            if (activeRobot.getEnergyCube() == 0){
                upgradePhase();
            } else {
                this.activePlayerID = activePlayer.ID;
                CurrentPlayer currentPlayer = new CurrentPlayer(activePlayer.ID);
                currentPlayer.getMessageBody().setKeys(new String[] {"clientID"});
                SERVER.sendMessageForAllUsers(currentPlayer);
            }
        } else {
            activePhase = 2;
            startGame();
        }
    }

    public void handleBuyUpgrade(boolean isBuying, String card, ClientHandler clientHandler){
        boolean bought = false;
        if (this.activePhase == 1 && this.activePlayerID == clientHandler.ID){
            if (isBuying){
                UpgradeCards karte = null;
                for (UpgradeCards upgradeCard: upgradeShop.getUpgradeCards()){
                    if (upgradeCard.getName().equals(card)){
                        karte = upgradeCard;
                        Robot robot = null;
                        for (Robot rob: figuren){
                            if (rob != null){
                                if (rob.getGamerID() == clientHandler.ID){
                                    robot = rob;
                                    break;
                                }
                            }
                        }
                        if (robot.getEnergyCube() >= karte.getCost()){
                            robot.setEnergyCube(robot.getEnergyCube() - karte.getCost());
                            if (card.equals("AdminPrivilege") || card.equals("RearLaser")){
                                bought = robot.addPermUpgrade(upgradeCard);
                                break;
                            } else {
                                bought = robot.addTempUpgrade(upgradeCard);
                                break;
                            }
                        }
                    }
                }
            } else {
                upgradePhase();
                return;
            }
        }

        if (bought){
            sendUpgradeBought(clientHandler.ID, card);
            upgradeShop.setSomebodyBoughtOne(true);
            upgradePhase();
        } else {
            Error1 error1 = new Error1("Der Kauf konnte nicht getätigt werden");
            error1.getMessageBody().setKeys(new String[]{"error"});
            SERVER.sendMessageForSingleClient(error1, clientHandler);
        }
    }

    public void sendUpgradeBought(int clientID, String card){
        UpgradeBought upgradeBought = new UpgradeBought(clientID, card);
        upgradeBought.getMessageBody().setKeys(new String[] {"clientID", "card"});
        SERVER.sendMessageForAllUsers(upgradeBought);
    }

    public void programmierPhase(){
        ActivePhase activePhase = new ActivePhase(this.activePhase);
        activePhase.getMessageBody().setKeys(new String[]{"phase"});
        SERVER.sendMessageForAllUsers(activePhase);

        for (Robot robot: figuren){
            if (robot != null) {

                int anzahlSpam = robot.drawHandCards();

                YourCards yourCards = new YourCards(convertListToArray(robot.getHandCards()));
                yourCards.getMessageBody().setKeys(new String[]{"cardsInHand"});
                SERVER.sendMessageForSingleClient(yourCards, users.get(robot.getGamerID()));

                NotYourCards notYourCards = new NotYourCards(robot.getGamerID(), robot.getHandCards().size());
                notYourCards.getMessageBody().setKeys(new String[]{"clientID", "cardsInHand"});
                SERVER.sendMessageForAllUsers(notYourCards);

                newSpamCards(anzahlSpam);
            }
        }
    }

    public void handleReturnCards(ArrayList<String> cards, ClientHandler clientHandler) {
        ArrayList<Cards> newDeck = arrayToList(cards);
        Robot robot = figuren[clientHandler.figure];
        if (robot.isAbleToReturnCard()) {
            int zähler = robot.getHandCards().size();
            while (robot.getHandCards().size() != 0) {
                Cards karte = robot.getHandCards().remove(0);
                newDeck.add(karte);
            }
            robot.setAbleToReturnCard(false);
            robot.setHandCards(newDeck);
        } else {
            Error1 error1 = new Error1("Du kannst aktuell keine Karten zurücklegen");
            error1.getMessageBody().setKeys(new String[]{"error"});
            SERVER.sendMessageForSingleClient(error1, clientHandler);
        }
    }

    public ArrayList<Cards> arrayToList (ArrayList<String> array){
        ArrayList<Cards> handcards= new ArrayList<Cards>();
        for (String s: array) {
            switch (s){
                case "MoveI":
                    handcards.add(new Move1Card());
                    break;
                case "MoveII":
                    handcards.add(new Move2Card());
                    break;
                case "MoveIII":
                    handcards.add(new Move3Card());
                    break;
                case "TurnLeft":
                    handcards.add(new LeftTurnCard());
                    break;
                case "TurnRight":
                    handcards.add(new RightTurnCard());
                    break;
                case "UTurn":
                    handcards.add(new UTurnCard());
                    break;
                case "BackUp":
                    handcards.add(new BackUpCard());
                    break;
                case "PowerUp":
                    handcards.add(new PowerUpCard());
                    break;
                case "Again":
                    handcards.add(new AgainCard());
                    break;
                case "Spam":
                    handcards.add(new Spam());
                    break;
                case "Trojan":
                    handcards.add(new Trojan());
                    break;
                case "Worm":
                    handcards.add(new Worm());
                    break;
                case "Virus":
                    handcards.add(new VirusCard());
                    break;
                default:
                    break;
            }
        }
        return handcards;
    }

    public void newSpamCards(int i){
        while (i > 0){
            cardsForGame.spamCards.add(new Spam());
            i--;
        }
    }

    public void aktivierungsPhase(){
        for (int i = 0; i < 5; i++) {
            ArrayList<ClientHandler> reihenfolge = reihenfolgeBestimmen();
            currentCardVerschicken();
            for (ClientHandler clientHandler : reihenfolge) {
                Robot robot = figuren[clientHandler.figure];

                if (!robot.getDead()) {
                    Cards card = robot.getRegister()[activeRegister];
                    card.effect(robot, SERVER);
                }
            }
            aktiviereMapElemente();
            activeRegister += 1;
        }
        beendeAktivierungsPhase();
    }

    public void handleChooseRegister(ClientHandler clientHandler){
        adminPrivilege.add(clientHandler);
        RegisterChosen registerChosen = new RegisterChosen(clientHandler.ID, clientHandler.chosenRegister);
        registerChosen.getMessageBody().setKeys(new String[]{"clientID", "register"});
        SERVER.sendMessageForAllUsers(registerChosen);
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

        for(ClientHandler clientHandler: adminPrivilege){
            if (clientHandler.chosenRegister == activeRegister){
                reihenfolge.add(clientHandler);
                falscheReihenfolge.remove(figuren[clientHandler.figure]);
                adminPrivilege.remove(clientHandler);
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
        activateBlueConveyor();
        activateGreenConveyor();
        activatePushPanel();
        activateGear();
        activateBoardLaser();
        activateRobotLaser();
        activateEnergySpace();
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
        if (activeMap == "Twister"){
            for (int i = 0; i < board.getMap().length; i++) {
                for (int u = 0; u < board.getMap()[i].length; u++) {
                    for (BoardElement boardElement : board.getMap()[i][u]) {
                        if (boardElement.getType().equals("CheckPoint")) {
                            moveCheckpoint(i, u);
                        }
                    }
                }
            }
        }
    }

    public void moveCheckpoint(int i, int u){
        BoardElement conveyor = null;
        BoardElement checkPoint = null;
        for (int p = 0; p < board.getMap()[i][u].size(); p++){
           if (board.getMap()[i][u].get(p).getType().equals("CheckPoint")){
               checkPoint = board.getMap()[i][u].remove(p);
               break;
           }
        }
        for (int p = 0; p < board.getMap()[i][u].size(); p++){
            if (board.getMap()[i][u].get(p).getType().equals("ConveyorBelt")){
                conveyor = board.getMap()[i][u].get(p);
                break;
            }
        }
        for (int z = 0; z < 2; z++) {
            switch (conveyor.getOrientations()[0]) {
                case "top":
                    i -= 1;
                    break;
                case "bottom":
                    i += 1;
                    break;
                case "left":
                    u -= 1;
                    break;
                case "right":
                    u += 1;
                    break;
            }
        }
        board.getMap()[i][u].add(checkPoint);
        CheckpointMoved checkpointMoved = new CheckpointMoved(checkPoint.getCount(), i, u);
        checkpointMoved.getMessageBody().setKeys(new String[]{"checkpointID", "x", "y"});
        SERVER.sendMessageForAllUsers(checkpointMoved);
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
        for (Robot robot: figuren){
            if (robot != null){
                for (BoardElement list: board.getMap()[robot.getX()][robot.getY()]){
                    if (list.getType().equals("PushPanel")){
                        for (int register: list.getRegisters()){
                            if (register == this.activeRegister){
                                list.effect(robot, SERVER);
                            }
                        }
                    }
                }
            }
        }
    }

    public void activateGear(){
        for (Robot robot: figuren){
            if (robot != null){
                for (BoardElement list: board.getMap()[robot.getX()][robot.getY()]){
                    if (list.getType().equals("Gear")){
                        list.effect(robot, SERVER);
                    }
                }
            }
        }
    }

    public void activateBoardLaser(){
        for (int i = 0; i < board.getMap().length; i++) {
            for (int u = 0; u < board.getMap()[i].length; u++) {
                for (BoardElement boardElement: board.getMap()[i][u]) {
                    if (boardElement.getType().equals("Laser")){
                        Robot hit = laserFired(i, u, boardElement.getOrientations()[0], null);
                        if (hit != null){
                            boardElement.effect(hit, SERVER);
                        }
                    }
                }
            }
        }
    }

    public void activateRobotLaser(){
        for (Robot robot: figuren){
            if (robot != null){
                if (!robot.getDead()) {
                    Robot hit = laserFired(robot.getX(), robot.getY(), robot.getDirection(), robot);
                    if (hit != null) {
                        drawDamageSpam(hit, 1);
                    }
                    for (UpgradeCards card: robot.getPermanentCards()) {
                        if (card != null) {
                            if (card.getName() == "RearLaser") {
                                String directionBack = null;
                                switch (robot.getDirection()) {
                                    case "top":
                                        directionBack = "bottom";
                                        break;
                                    case "bottom":
                                        directionBack = "top";
                                        break;
                                    case "right":
                                        directionBack = "left";
                                        break;
                                    case "left":
                                        directionBack = "right";
                                        break;
                                    default:
                                        break;
                                }
                                Robot hit2 = laserFired(robot.getX(), robot.getY(), directionBack, robot);
                                if (hit2 != null) {
                                    drawDamageSpam(hit2, 1);
                                }
                                return;
                            }
                        }
                    }
                }
            }
        }
    }

    public void activateEnergySpace(){
        for (Robot robot: figuren){
            if (robot != null){
                for (BoardElement list: board.getMap()[robot.getX()][robot.getY()]){
                    if (list.getType().equals("EnergySpace")){
                        list.effect(robot, SERVER);
                    }
                }
            }
        }
    }

    public void activateCheckpoint(){
        for (Robot robot: figuren){
            if (robot != null){
                for (BoardElement list: board.getMap()[robot.getX()][robot.getY()]){
                    if (list.getType().equals("CheckPoint")){
                        list.effect(robot, SERVER);
                    }
                }
            }
        }
    }


    public void beendeAktivierungsPhase(){
        try {
            for (Robot robot : figuren) {
                if (robot != null) {
                    robot.clearRegister();
                    robot.clearHandcards();
                    robot.setAbleToFillRegisters(true);
                    robot.setDead(false);
                    robot.setAbleToChooseRestartDirection(false);
                }
            }
            timerActivated = false;
            activePhase = 1;
            activeRegister = 0;
            adminPrivilege = new ArrayList<>();
            startGame();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void handleRebootDirection(String direction, ClientHandler clientHandler){
        Robot rob = null;
        for (Robot robot: figuren){
            if (robot != null){
                if (clientHandler.ID == robot.getGamerID()){
                  rob = robot;
                  break;
                }
            }
        }
        if (rob != null) {
            if (rob.getAbleForRestart()) {
                PlayerTurning playerTurning;
                rob.setDirection(direction);
                rob.setAbleToChooseRestartDirection(false);
                switch (direction){
                    case "top":
                        break;
                    case "bottom":
                        playerTurning = new PlayerTurning(clientHandler.ID, "clockwise");
                        PlayerTurning playerTurning2 = new PlayerTurning(clientHandler.ID, "clockwise");
                        playerTurning2.getMessageBody().setKeys(new String[]{"clientID", "rotation"});
                        playerTurning.getMessageBody().setKeys(new String[]{"clientID", "rotation"});
                        SERVER.sendMessageForAllUsers(playerTurning2);
                        SERVER.sendMessageForAllUsers(playerTurning);
                        break;
                    case "left":
                        playerTurning = new PlayerTurning(clientHandler.ID, "counterclockwise");
                        playerTurning.getMessageBody().setKeys(new String[]{"clientID", "rotation"});
                        SERVER.sendMessageForAllUsers(playerTurning);
                        break;
                    case "right":
                        playerTurning = new PlayerTurning(clientHandler.ID, "clockwise");
                        playerTurning.getMessageBody().setKeys(new String[]{"clientID", "rotation"});
                        SERVER.sendMessageForAllUsers(playerTurning);
                        break;
                }
            }
        }
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
        if (board.getMap()[x][y].get(0).getType().equals("StartPoint")) {
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
            figuren[clientHandler.figure].setStartPointX(x);
            figuren[clientHandler.figure].setStartPointY(y);
            if (activeMap.equals("DeathTrap")){
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
       try {
           if (figuren[clientHandler.figure].cardIntoRegister(card, register)) {
               boolean filled = true;
               if (card == null) filled = false;
               CardSelected cardSelected = new CardSelected(clientHandler.ID, register, filled);
               cardSelected.getMessageBody().setKeys(new String[]{"clientID", "register", "filled"});
               SERVER.sendMessageForAllUsers(cardSelected);
               if (figuren[clientHandler.figure].allRegistersFilled() && !timerActivated) {
                   sendSelectionFinished(clientHandler);
                   startTimer();
                   timerActivated = true;
               }
           } else {
               sendError("Dies ist nicht möglich.", clientHandler);
           }
       } catch (Exception e){
           e.printStackTrace();
       }
    }

    public void startTimer(){
        try {
            TimerStarted timerStarted = new TimerStarted();
            SERVER.sendMessageForAllUsers(timerStarted);
            OurTimer ourTimer = new OurTimer(30, this);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void timerEnded(){
        try {
            ArrayList<Integer> schlafmützen = checkWhoIsntDone();
            Integer[] zuLangsameSpieler = new Integer[schlafmützen.size()];
            for (int i = 0; i < zuLangsameSpieler.length; i++) {
                zuLangsameSpieler[i] = schlafmützen.get(i);
            }
            TimerEnded timerEnded = new TimerEnded(zuLangsameSpieler);
            timerEnded.getMessageBody().setKeys(new String[]{"clientIDs"});
            SERVER.sendMessageForAllUsers(timerEnded);
            fillRegisters(zuLangsameSpieler);
            activePhase = 3;
            startGame();
        } catch (Exception e){
            e.printStackTrace();
        }
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

    public boolean checkMovement(Robot robot, String direction, boolean necessary){
        Movement movement;
        switch (direction) {
            case "top":
                if (!robot.getDead() || necessary) {
                    for (BoardElement list : board.getMap()[robot.getX()][robot.getY()]) {
                        if (list.getType().equals("Wall")) {
                            for (String s : list.getOrientations()) {
                                if (s.equals("top")) return false;
                            }
                        }
                    }
                    if (robot.getX() - 1 < 0) {
                        reboot(robot, board.getMap()[robot.getX()][robot.getY()].get(0).getIsOnBoard());
                        return true;
                    }
                    for (BoardElement element : board.getMap()[robot.getX() - 1][robot.getY()]) {
                        if (element.getType().equals("Wall")) {
                            for (String s : element.getOrientations()) {
                                if (s.equals("bottom")) return false;
                            }
                        } else if (element.getType().equals("Antenna")) return false;
                        else if (element.getType().equals("Pit")) {
                            reboot(robot, board.getMap()[robot.getX()][robot.getY()].get(0).getIsOnBoard());
                            return true;
                        }
                    }
                    for (Robot r : figuren) {
                        if (r != null && !r.equals(robot)) {
                            if (r.getX() == robot.getX() - 1 && r.getY() == robot.getY()) {
                                if (!checkMovement(r, direction, true)) {
                                    return false;
                                }
                            }
                        }
                    }
                    robot.setX(robot.getX() - 1);
                    movement = new Movement(robot.getGamerID(), robot.getX(), robot.getY());
                    movement.getMessageBody().setKeys(new String[]{"clientID", "x", "y"});
                    SERVER.sendMessageForAllUsers(movement);
                    return true;
                } else return false;
            case "bottom":
                if (!robot.getDead() || necessary) {
                    for (BoardElement list : board.getMap()[robot.getX()][robot.getY()]) {
                        if (list.getType().equals("Wall")) {
                            for (String s : list.getOrientations()) {
                                if (s.equals("bottom")) return false;
                            }
                        }
                    }
                    if (robot.getX() + 1 >= board.getHeight()) {
                        reboot(robot, board.getMap()[robot.getX()][robot.getY()].get(0).getIsOnBoard());
                        return true;
                    }
                    for (BoardElement element : board.getMap()[robot.getX() + 1][robot.getY()]) {
                        if (element.getType().equals("Wall")) {
                            for (String s : element.getOrientations()) {
                                if (s.equals("top")) return false;
                            }
                        } else if (element.getType().equals("Antenna")) return false;
                        else if (element.getType().equals("Pit")) {
                            reboot(robot, board.getMap()[robot.getX()][robot.getY()].get(0).getIsOnBoard());
                            return true;
                        }
                    }
                    for (Robot r : figuren) {
                        if (r != null && !r.equals(robot)) {
                            if (r.getX() == robot.getX() + 1 && r.getY() == robot.getY()) {
                                if (!checkMovement(r, direction, true)) {
                                    return false;
                                }
                            }
                        }
                    }
                    robot.setX(robot.getX() + 1);
                    movement = new Movement(robot.getGamerID(), robot.getX(), robot.getY());
                    movement.getMessageBody().setKeys(new String[]{"clientID", "x", "y"});
                    SERVER.sendMessageForAllUsers(movement);
                    return true;
                } else return false;
            case "left":
                if (!robot.getDead() || necessary) {
                    for (BoardElement list : board.getMap()[robot.getX()][robot.getY()]) {
                        if (list.getType().equals("Wall")) {
                            for (String s : list.getOrientations()) {
                                if (s.equals("left")) return false;
                            }
                        }
                    }
                    if (robot.getY() - 1 < 0) {
                        reboot(robot, board.getMap()[robot.getX()][robot.getY()].get(0).getIsOnBoard());
                        return true;
                    }
                    for (BoardElement element : board.getMap()[robot.getX()][robot.getY() - 1]) {
                        if (element.getType().equals("Wall")) {
                            for (String s : element.getOrientations()) {
                                if (s.equals("right")) return false;
                            }
                        } else if (element.getType().equals("Antenna")) return false;
                        else if (element.getType().equals("Pit")) {
                            reboot(robot, board.getMap()[robot.getX()][robot.getY()].get(0).getIsOnBoard());
                            return true;
                        }
                    }
                    for (Robot r : figuren) {
                        if (r != null && !r.equals(robot)) {
                            if (r.getX() == robot.getX() && r.getY() == robot.getY() - 1) {
                                if (!checkMovement(r, direction, true)) {
                                    return false;
                                }
                            }
                        }
                    }
                    robot.setY(robot.getY() - 1);
                    movement = new Movement(robot.getGamerID(), robot.getX(), robot.getY());
                    movement.getMessageBody().setKeys(new String[]{"clientID", "x", "y"});
                    SERVER.sendMessageForAllUsers(movement);
                    return true;
                } else return false;
            case "right":
                if (!robot.getDead() ||necessary) {
                    for (BoardElement list : board.getMap()[robot.getX()][robot.getY()]) {
                        if (list.getType().equals("Wall")) {
                            for (String s : list.getOrientations()) {
                                if (s.equals("right")) return false;
                            }
                        }
                    }
                    if (robot.getY() + 1 >= board.getWidth()) {
                        reboot(robot, board.getMap()[robot.getX()][robot.getY()].get(0).getIsOnBoard());
                        return true;
                    }
                    for (BoardElement element : board.getMap()[robot.getX()][robot.getY() + 1]) {
                        if (element.getType().equals("Wall")) {
                            for (String s : element.getOrientations()) {
                                if (s.equals("left")) return false;
                            }
                        } else if (element.getType().equals("Antenna")) return false;
                        else if (element.getType().equals("Pit")) {
                            reboot(robot, board.getMap()[robot.getX()][robot.getY()].get(0).getIsOnBoard());
                            return true;
                        }
                    }
                    for (Robot r : figuren) {
                        if (r != null && !r.equals(robot)) {
                            if (r.getX() == robot.getX() && r.getY() == robot.getY() + 1) {
                                if (!checkMovement(r, direction, true)) {
                                    return false;
                                }
                            }
                        }
                    }
                    robot.setY(robot.getY() + 1);
                    movement = new Movement(robot.getGamerID(), robot.getX(), robot.getY());
                    movement.getMessageBody().setKeys(new String[]{"clientID", "x", "y"});
                    SERVER.sendMessageForAllUsers(movement);
                    return true;
                } else return false;
            default:
                return false;
        }
    }

    public boolean checkConveyor(Robot robot){
        try {
            String robotDirection = robot.getDirection();
            BoardElement conveyor = null;
            for (BoardElement element : board.getMap()[robot.getX()][robot.getY()]) {
                if (element.getType().equals("ConveyorBelt")) {
                    conveyor = element;
                }
            }
            BoardElement next = null;
            if (conveyor != null) {
                switch (conveyor.getOrientations()[0]) {
                    case "top":
                        if (robot.getX() - 1 < 0) {
                            reboot(robot, conveyor.getIsOnBoard());
                            return true;
                        }
                        for (BoardElement boardElement : board.getMap()[robot.getX() - 1][robot.getY()]) {
                            if (boardElement.getType().equals("ConveyorBelt") || boardElement.getType().equals("Pit")) {
                                next = boardElement;
                            }
                        }
                        if (next != null) {
                            if (next.getType().equals("Pit")) {
                                reboot(robot, next.getIsOnBoard());
                                return true;
                            } else {
                                if (next.getOrientations()[0].equals("top")) {
                                    robot.setX(robot.getX() - 1);
                                    sendMovement(robot.getGamerID(), robot.getX(), robot.getY());
                                    return true;
                                } else if (next.getOrientations()[0].equals("left")) {
                                    robot.setX(robot.getX() - 1);
                                    sendMovement(robot.getGamerID(), robot.getX(), robot.getY());
                                    sendRotation(robot, "counterclockwise");
                                    return true;
                                } else if (next.getOrientations()[0].equals("right")) {
                                    robot.setX(robot.getX() - 1);
                                    sendMovement(robot.getGamerID(), robot.getX(), robot.getY());
                                    sendRotation(robot, "clockwise");
                                    return true;
                                } else {
                                    //Pfeile zueinander?! was passiert?
                                    //--> Simon!! Existiert der Fall überhaupt? Fehler in der Map?
                                }
                            }
                        } else {
                            for (Robot r : figuren) {
                                if (r != null && !r.equals(robot)) {
                                    if (r.getX() == robot.getX() - 1 && r.getY() == robot.getY()) {
                                        return false;
                                    }
                                }
                            }
                            robot.setX(robot.getX() - 1);
                            sendMovement(robot.getGamerID(), robot.getX(), robot.getY());
                            return true;
                        }
                        break;
                    case "bottom":
                        if (robot.getX() + 1 >= board.getHeight()) {
                            reboot(robot, conveyor.getIsOnBoard());
                            return true;
                        }
                        for (BoardElement boardElement : board.getMap()[robot.getX() + 1][robot.getY()]) {
                            if (boardElement.getType().equals("ConveyorBelt") || boardElement.getType().equals("Pit")) {
                                next = boardElement;
                            }
                        }
                        if (next != null) {
                            if (next.getType().equals("Pit")) {
                                reboot(robot, next.getIsOnBoard());
                                return true;
                            } else {
                                if (next.getOrientations()[0].equals("bottom")) {
                                    robot.setX(robot.getX() + 1);
                                    sendMovement(robot.getGamerID(), robot.getX(), robot.getY());
                                    return true;
                                } else if (next.getOrientations()[0].equals("right")) {
                                    robot.setX(robot.getX() + 1);
                                    sendMovement(robot.getGamerID(), robot.getX(), robot.getY());
                                    sendRotation(robot, "counterclockwise");
                                    return true;
                                } else if (next.getOrientations()[0].equals("left")) {
                                    robot.setX(robot.getX() + 1);
                                    sendMovement(robot.getGamerID(), robot.getX(), robot.getY());
                                    sendRotation(robot, "clockwise");
                                    return true;
                                } else {
                                    //Pfeile zueinander?! was passiert?
                                    //--> Simon!! Existiert der Fall überhaupt? Fehler in der Map?
                                }
                            }
                        } else {
                            for (Robot r : figuren) {
                                if (r != null && !r.equals(robot)) {
                                    if (r.getX() == robot.getX() + 1 && r.getY() == robot.getY()) {
                                        return false;
                                    }
                                }
                            }
                            robot.setX(robot.getX() + 1);
                            sendMovement(robot.getGamerID(), robot.getX(), robot.getY());
                            return true;
                        }
                        break;
                    case "left":
                        if (robot.getY() - 1 < 0) {
                            reboot(robot, conveyor.getIsOnBoard());
                            return true;
                        }
                        for (BoardElement boardElement : board.getMap()[robot.getX()][robot.getY() - 1]) {
                            if (boardElement.getType().equals("ConveyorBelt") || boardElement.getType().equals("Pit")) {
                                next = boardElement;
                            }
                        }
                        if (next != null) {
                            if (next.getType().equals("Pit")) {
                                reboot(robot, next.getIsOnBoard());
                                return true;
                            } else {
                                if (next.getOrientations()[0].equals("left")) {
                                    robot.setY(robot.getY() - 1);
                                    sendMovement(robot.getGamerID(), robot.getX(), robot.getY());
                                    return true;
                                } else if (next.getOrientations()[0].equals("bottom")) {
                                    robot.setY(robot.getY() - 1);
                                    sendMovement(robot.getGamerID(), robot.getX(), robot.getY());
                                    sendRotation(robot, "counterclockwise");
                                    return true;
                                } else if (next.getOrientations()[0].equals("top")) {
                                    robot.setY(robot.getX() - 1);
                                    sendMovement(robot.getGamerID(), robot.getX(), robot.getY());
                                    sendRotation(robot, "clockwise");
                                    return true;
                                } else {
                                    //Pfeile zueinander?! was passiert?
                                    //--> Simon!! Existiert der Fall überhaupt? Fehler in der Map?
                                }
                            }
                        } else {
                            for (Robot r : figuren) {
                                if (r != null && !r.equals(robot)) {
                                    if (r.getX() == robot.getX() && r.getY() == robot.getY() - 1) {
                                        return false;
                                    }
                                }
                            }
                            robot.setY(robot.getY() - 1);
                            sendMovement(robot.getGamerID(), robot.getX(), robot.getY());
                            return true;
                        }
                        break;
                    case "right":
                        if (robot.getY() + 1 >= board.getWidth()) {
                            reboot(robot, conveyor.getIsOnBoard());
                            return true;
                        }
                        for (BoardElement boardElement : board.getMap()[robot.getX()][robot.getY() + 1]) {
                            if (boardElement.getType().equals("ConveyorBelt") || boardElement.getType().equals("Pit")) {
                                next = boardElement;
                            }
                        }
                        if (next != null) {
                            if (next.getType().equals("Pit")) {
                                reboot(robot, next.getIsOnBoard());
                                return true;
                            } else {
                                if (next.getOrientations()[0].equals("right")) {
                                    robot.setY(robot.getY() + 1);
                                    sendMovement(robot.getGamerID(), robot.getX(), robot.getY());
                                    return true;
                                } else if (next.getOrientations()[0].equals("top")) {
                                    robot.setY(robot.getY() + 1);
                                    sendMovement(robot.getGamerID(), robot.getX(), robot.getY());
                                    sendRotation(robot, "counterclockwise");
                                    return true;
                                } else if (next.getOrientations()[0].equals("bottom")) {
                                    robot.setY(robot.getX() + 1);
                                    sendMovement(robot.getGamerID(), robot.getX(), robot.getY());
                                    sendRotation(robot, "clockwise");
                                    return true;
                                } else {
                                    //Pfeile zueinander?! was passiert?
                                    //--> Simon!! Existiert der Fall überhaupt? Fehler in der Map?
                                }
                            }
                        } else {
                            for (Robot r : figuren) {
                                if (r != null && !r.equals(robot)) {
                                    if (r.getX() == robot.getX() && r.getY() == robot.getY() + 1) {
                                        return false;
                                    }
                                }
                            }
                            robot.setY(robot.getY() + 1);
                            sendMovement(robot.getGamerID(), robot.getX(), robot.getY());
                            return true;
                        }
                        break;
                    default:
                        return false;
                }
            } else return false;
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void sendMovement(int ID, int x, int y){
        Movement movement = new Movement(ID, x, y);
        movement.getMessageBody().setKeys(new String[]{"clientID", "x", "y"});
        SERVER.sendMessageForAllUsers(movement);
    }

    public void sendRotation(Robot robot, String rotation){
        switch (robot.getDirection()){
            case "top":
                if (rotation.equals("clockwise")){
                    robot.setDirection("right");
                } else robot.setDirection("left");
                break;
            case "bottom":
                if (rotation.equals("clockwise")){
                    robot.setDirection("left");
                } else robot.setDirection("right");
                break;
            case "left":
                if (rotation.equals("clockwise")){
                    robot.setDirection("top");
                } else robot.setDirection("bottom");
                break;
            case "right":
                if (rotation.equals("clockwise")){
                    robot.setDirection("bottom");
                } else robot.setDirection("top");
                break;
        }

        PlayerTurning playerTurning = new PlayerTurning(robot.getGamerID(), rotation);
        playerTurning.getMessageBody().setKeys(new String[]{"clientID", "rotation"});
        SERVER.sendMessageForAllUsers(playerTurning);
    }

    public Robot laserFired(int x, int y, String direction, Robot r){
        boolean stillFlying = true;
        while (stillFlying) {
            for (Robot robot : figuren) {
                if (robot != null && !robot.equals(r)) {
                    if (robot.getX() == x && robot.getY() == y) {
                        stillFlying = false;
                        return robot;
                    }
                }
            }
            switch (direction) {
                case "top":
                    for (BoardElement element: board.getMap()[x][y]){
                        if (element.getType().equals("Wall")){
                            for (String orientation: element.getOrientations()){
                                if (orientation.equals("top")){
                                    stillFlying = false;
                                    return null;
                                }
                            }
                        }
                    }
                    if (x-1 < 0) return null;
                    for (BoardElement element: board.getMap()[x-1][y]){
                        if (element.getType().equals("Wall")){
                            for (String orientation: element.getOrientations()){
                                if (orientation.equals("bottom")){
                                    stillFlying = false;
                                    return null;
                                }
                            }
                        }
                    }
                    x -= 1;
                    break;
                case "bottom":
                    for (BoardElement element: board.getMap()[x][y]){
                        if (element.getType().equals("Wall")){
                            for (String orientation: element.getOrientations()){
                                if (orientation.equals("bottom")){
                                    stillFlying = false;
                                    return null;
                                }
                            }
                        }
                    }
                    if (x+1 >= board.getHeight()) return null;
                    for (BoardElement element: board.getMap()[x+1][y]){
                        if (element.getType().equals("Wall")){
                            for (String orientation: element.getOrientations()){
                                if (orientation.equals("top")){
                                    stillFlying = false;
                                    return null;
                                }
                            }
                        }
                    }
                    x += 1;
                    break;
                case "left":
                    for (BoardElement element: board.getMap()[x][y]){
                        if (element.getType().equals("Wall")){
                            for (String orientation: element.getOrientations()){
                                if (orientation.equals("left")){
                                    stillFlying = false;
                                    return null;
                                }
                            }
                        }
                    }
                    if (y-1 < 0) return null;
                    for (BoardElement element: board.getMap()[x][y-1]){
                        if (element.getType().equals("Wall")){
                            for (String orientation: element.getOrientations()){
                                if (orientation.equals("right")){
                                    stillFlying = false;
                                    return null;
                                }
                            }
                        }
                    }
                    y -= 1;
                    break;
                case "right":
                    for (BoardElement element: board.getMap()[x][y]){
                        if (element.getType().equals("Wall")){
                            for (String orientation: element.getOrientations()){
                                if (orientation.equals("right")){
                                    stillFlying = false;
                                    return null;
                                }
                            }
                        }
                    }
                    if (y+1 >= board.getWidth()) return null;
                    for (BoardElement element: board.getMap()[x][y+1]){
                        if (element.getType().equals("Wall")){
                            for (String orientation: element.getOrientations()){
                                if (orientation.equals("left")){
                                    stillFlying = false;
                                    return null;
                                }
                            }
                        }
                    }
                    y += 1;
                    break;
                default:
                    return null;
            }
        }
        return null;
    }

    public void chooseDamageCard(ClientHandler clientHandler, String card){
        Robot rob = null;
        for (Robot robot: figuren){
            if (robot != null) {
                if (clientHandler.ID == robot.getGamerID()) {
                    if (robot.isAbleToChooseDamageCard()){
                        rob = robot;
                    }
                }
            }
        }
        if (chooseDamageCard && rob != null) {
            this.currentDamageCard = card;
            chooseDamageCard = false;
        }

    }

    public void drawDamageSpam(Robot robot, int count){
        try {
            String[] karten = new String[count];
            for (int i = 0; i < count; i++) {
                if (cardsForGame.spamCards.size() != 0) {
                    Cards card = cardsForGame.spamCards.remove(0);
                    robot.getDeck().getDiscard().add(card);
                    karten[i] = card.getName();
                } else {
                   karten = chooseNewCard(robot, karten, i);
                }
            }
            DrawDamage drawDamage = new DrawDamage(robot.getGamerID(), karten);
            drawDamage.getMessageBody().setKeys(new String[]{"clientID", "cards"});
            SERVER.sendMessageForAllUsers(drawDamage);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void drawDamageVirus(Robot robot, int count){
        try {
            String[] karten = new String[count];
            for (int i = 0; i < count; i++) {
                if (cardsForGame.virusCards.size() != 0) {
                    Cards card = cardsForGame.virusCards.remove(0);
                    robot.getDeck().getDiscard().add(card);
                    karten[i] = card.getName();
                } else {
                    karten = chooseNewCard(robot, karten, i);
                }
            }
            DrawDamage drawDamage = new DrawDamage(robot.getGamerID(), karten);
            drawDamage.getMessageBody().setKeys(new String[]{"clientID", "cards"});
            SERVER.sendMessageForAllUsers(drawDamage);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public String[] chooseNewCard(Robot robot, String[] karten, int i) {
        chooseDamageCard = true;
        robot.setAbleToChooseDamageCard(true);
        int anzahl = 0;
        ArrayList<String> damage = new ArrayList<String>();
        if (cardsForGame.spamCards.size() != 0) {
            anzahl += 1;
            damage.add("Spam");
        }
        if (cardsForGame.virusCards.size() != 0) {
            anzahl += 1;
            damage.add("Virus");
        }
        if (cardsForGame.wormCards.size() != 0) {
            anzahl += 1;
            damage.add("Worm");
        }
        if (cardsForGame.trojanHorse.size() != 0) {
            anzahl += 1;
            damage.add("Trojan");
        }
        if (anzahl != 0) {
            String[] piles = new String[anzahl];
            for (int p = 0; p < piles.length; p++) {
                piles[p] = damage.remove(0);
            }
            PickDamage pickDamage = new PickDamage(1, piles);
            pickDamage.getMessageBody().setKeys(new String[]{"count", "availablePiles"});
            SERVER.sendMessageForSingleClient(pickDamage, verbindungen.get(robot.getGamerID()));
            while (chooseDamageCard) {

            }
            Cards karte = null;
            switch (this.currentDamageCard) {
                case "Spam":
                    karte = cardsForGame.spamCards.remove(0);
                    break;
                case "Virus":
                    karte = cardsForGame.virusCards.remove(0);
                    break;
                case "Worm":
                    karte = cardsForGame.wormCards.remove(0);
                    break;
                case "Trojan":
                    karte = cardsForGame.trojanHorse.remove(0);
                    break;
                default:
                    currentDamageCard = null;
                    break;
            }
            robot.getDeck().getDiscard().add(karte);
            karten[i] = karte.getName();
            robot.setAbleToChooseDamageCard(false);
            return karten;
        } else {
            karten[i] = null;
            return karten;
        }
    }

    public void sendVirus(Robot rob){
        for (Robot robot: figuren){
            if (robot != null && !robot.equals(rob)){
                int entfernungX = Math.abs(robot.getX() - rob.getX());
                int entfernungY = Math.abs(robot.getY() - rob.getY());
                int entfernungGesamt = entfernungX + entfernungY;
                if (entfernungGesamt <= 6){
                    drawDamageVirus(robot, 1);
                }
            }
        }
    }

    public void reboot(Robot robot, String isOnBoard){
        try {
            drawDamageSpam(robot, 2);
            robot.setDead(true);

            int rebootX = board.searchX("RestartPoint");
            int rebootY = board.searchY("RestartPoint");

            if (!isOnBoard.equals("A")) {
                for (Robot r : figuren) {
                    if (r != null && !r.equals(robot)) {
                        if (r.getX() == rebootX && r.getY() == rebootY) {
                            for (BoardElement element : board.getMap()[rebootX][rebootY]) {
                                if (element.getType().equals("RestartPoint")) {
                                    checkMovement(r, element.getOrientations()[0], true);
                                }
                            }
                        }
                    }
                }
                robot.setX(rebootX);
                robot.setY(rebootY);
            } else {
                boolean startPointTaken = false;
                for (Robot r : figuren) {
                    if (r != null && !r.equals(robot)) {
                        if (r.getX() == robot.getStartPointX() && r.getY() == robot.getStartPointY()) {
                            for (BoardElement element : board.getMap()[robot.getStartPointX()][robot.getStartPointY()]) {
                                if (element.getType().equals("StartPoint")) {
                                    startPointTaken = true;
                                }
                            }
                        }
                    }
                }
                if (startPointTaken) {
                    boolean newStartPointFound = true;
                    int zufall = (int) Math.floor(Math.random() * 5);
                    while (newStartPointFound) {
                        for (int i = 0; i < board.getMap().length; i++) {
                            for (int u = 0; u < board.getMap()[i].length; u++) {
                                for (BoardElement boardElement : board.getMap()[i][u]) {
                                    if (boardElement.getType().equals("StartPoint")) {
                                        if (zufall >= 0) {
                                            zufall -= 1;
                                        } else {
                                            newStartPointFound = false;
                                            for (Robot rob : figuren) {
                                                if (rob != null && !rob.equals(robot)) {
                                                    if (rob.getX() == i && rob.getY() == u) {
                                                        newStartPointFound = true;
                                                    }
                                                }
                                            }
                                            if (!newStartPointFound) {
                                                robot.setX(i);
                                                robot.setY(u);
                                                zufall = 1000;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    robot.setX(robot.getStartPointX());
                    robot.setY(robot.getStartPointY());
                }
            }
            robot.setDirection("top");
            robot.setAbleToChooseRestartDirection(true);
            Movement movement = new Movement(robot.getGamerID(), robot.getX(), robot.getY());
            movement.getMessageBody().setKeys(new String[]{"clientID", "x", "y"});
            SERVER.sendMessageForAllUsers(movement);

            Reboot reboot = new Reboot(robot.getGamerID());
            reboot.getMessageBody().setKeys(new String[]{"clientID"});
            SERVER.sendMessageForAllUsers(reboot);
        } catch (Exception e){
            e.printStackTrace();
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

    public int getActiveRegister() {
        return activeRegister;
    }

    public int getNeededCheckpoints() {
        return neededCheckpoints;
    }

    public CardsForGame getCardsForGame() {
        return cardsForGame;
    }

}


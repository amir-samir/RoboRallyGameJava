import Messages.*;
import Messages.Actions.ChooseRegister;
import Messages.Actions.RebootDirection;
import Messages.Actions.ReturnCards;
import Messages.Actions.SelectedDamage;
import Messages.Phase.BuyUpgrade;
import Messages.Phase.SelectedCard;
import Messages.Phase.SetStartingPoint;
import com.google.gson.internal.LinkedTreeMap;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * Diese Klasse stellt das Verbindungsstück eines Spielers zum Server dar.
 *
 * @author Amir Azim
 * @author Dairen Gonschior
 * @author Luca Weyhofen
 *
 * @version 2.1
 */
public class Client implements Runnable {

    private final Socket SOCKET;
    private final String GROUP = "Innige Irrwege";
    private final boolean isAi;
    private boolean connected;
    private int ID;
    private boolean ready = false;
    private static Client client;
    private static Thread thread;
    static {
        try {
            client = new Client();
            thread = new Thread(client);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<BoardElement>[][] map;
    private ArrayList<String> upgradeShop;

    private final BufferedReader bufferedReader;
    private final PrintWriter bufferedWriter;

    public String protocol;
    public ObservableList<String> chatMessages;
    public ObservableList<String> usernamesGui;
    public HashMap<Integer, Player> player = new HashMap<Integer, Player>();
    public Robot[] figuren = new Robot[6];
    ChatView chatView = new ChatView();
    public static ChatView chatView1;
    public static SelectMapView selectMapView = new SelectMapView();
    public static ChooseCardsForSwap chooseCardsForSwap;
    public static AllInOneView allInOneView;
    public static FirstView firstView;
    private String selectedMap;
    public int figureForGui;
    public String CardOfGui = "SomeCard";
    public ObservableList<Integer> figurenForGui;
    private int cubesZahl = 5;
    private RalleyLogger ralleyLogger = new RalleyLogger();
    private String[] UpgradeCardName = new String[2];
    private String titleUserName;
    private int robterGewonnen;
    private boolean istGewinner = false;


    /**
     * Dies ist der Konstruktor.
     * Er stellt die Verbindung zwischen Server und Client her und bereitet den Client auf die Verwendung vor.
     * @throws IOException Throw this exception if the connection between server and client fails.
     */
    public Client() throws IOException {
        SOCKET = new Socket("localhost", 1237);
        bufferedReader = new BufferedReader(new InputStreamReader(SOCKET.getInputStream()));
        bufferedWriter = new PrintWriter(SOCKET.getOutputStream(), true);
        usernamesGui = FXCollections.observableArrayList();
        chatMessages = FXCollections.observableArrayList();
        figurenForGui = FXCollections.observableArrayList();
        upgradeShop = new ArrayList<>();
        Arrays.fill(UpgradeCardName,"leer");
        isAi = false;
    }

    /**
     * Diese Methode sendet Chatnachrichten an einen anderen Spieler.
     * @param senderId Die ID des Versenders
     * @param message Die Nachricht
     * @param userName Die ID des Empfängers
     */
    public void singleMessage(int senderId, String message, int userName){
        String[] keys = {"message", "to"};
        SendChat sendChat = new SendChat(message, userName);
        sendChat.getMessageBody().setKeys(keys);
        bufferedWriter.println(Adopter.javabeanToJson(sendChat));

        SendChat sentMsg= new SendChat(message + " was sent to " + userName, senderId);
        sentMsg.getMessageBody().setKeys(keys);
        bufferedWriter.println(Adopter.javabeanToJson(sentMsg));
    }

    /**
     * Diese Methode verschickt Chatnachrichten an alle Spieler.
     * @param input Die Nachricht
     */
    public void printMessage(String input) {
        SendChat message = new SendChat(input, -1);
        String[] key = {"message", "to"};
        message.getMessageBody().setKeys(key);
        String toSend = Adopter.javabeanToJson(message);
        bufferedWriter.println(toSend);
    }

    /**
     * Diese Methode versendet die ausgewählten Kategorien an Schadenskarten an den Server.
     * @param cards Datenfeld mit den ausgewählten Schadenskarten-Kategorien
     */
    public void sendSelectedDamage(String[] cards){
        SelectedDamage selectedDamage = new SelectedDamage(cards);
        selectedDamage.getMessageBody().setKeys(new String[]{"cards"});
        bufferedWriter.println(Adopter.javabeanToJson(selectedDamage));
    }

    /**
     * Diese Methode sendet Name und Figur des Spielers an den Server.
     * @param name Name des Spielers
     * @param figur Nummer der Figur des Spielers
     */
    public void configuration(String name, int figur){
        PlayerValues message = new PlayerValues(name, figur);
        String[] keys = {"name", "figure"};
        message.getMessageBody().setKeys(keys);
        bufferedWriter.println(Adopter.javabeanToJson(message));
        figureForGui = figur;
        titleUserName = name;
        Platform.runLater(new Runnable(){
            @Override
            public void run() {
                    getChatView().setImageFromFigur(figur);

            }
        });
    }

    /**
     * Diese Methode versendet den ausgewählten Startpunkt an den Server.
     * @param x x-Koordinate
     * @param y y-Koordinate
     */
    public void setStartingPoint(int x, int y){
        SetStartingPoint setStartingPoint = new SetStartingPoint(x, y);
        setStartingPoint.getMessageBody().setKeys(new String[]{"x", "y"});
        bufferedWriter.println(Adopter.javabeanToJson(setStartingPoint));
        System.out.println(Adopter.javabeanToJson(setStartingPoint));
    }

    /**
     * Verschicken der HelloServer Nachricht an den Server.
     * @param message HelloServer Nachricht
     */
    public void sendHelloServer(Message message){
        protocol = (String) message.getMessageBody().getContent()[0];
        HelloServer output = new HelloServer(GROUP, isAi, protocol);
        String[] keys = {"group", "isAI", "protocol"};
        output.getMessageBody().setKeys(keys);
        String S = Adopter.javabeanToJson(output);
        bufferedWriter.println(S);
    }

    /**
     * Diese Methode verschickt die Kartenauswahl des Spielers an den Server.
     * @param card Die ausgewählte Karte
     * @param register Das Register, in das die Karte gespielt werden soll
     */
    public void sendCardToRegister(String card, int register){
        SelectedCard selectedCard = new SelectedCard(card, register);
        selectedCard.getMessageBody().setKeys(new String[]{"card", "register"});
        bufferedWriter.println(Adopter.javabeanToJson(selectedCard));
        System.out.println(Adopter.javabeanToJson(selectedCard));
    }

    /**
     * Wenn der Spieler eine Map ausgewählt hat, wird diese hier an den Server verschickt.
     * @param map Die ausgewählte Map
     */
    public void mapSelected(String map){
        MapSelected mapSelected = new MapSelected(map);
        String[] key = {"map"};
        mapSelected.getMessageBody().setKeys(key);
        bufferedWriter.println(Adopter.javabeanToJson(mapSelected));
    }

    /**
     * Versendung der playCard Nachricht
     * @param card Gespielte Karte
     */
    public void playCard(String card){
        PlayCard playCard = new PlayCard(card);
        String[] key = {"card"};
        playCard.getMessageBody().setKeys(key);

        bufferedWriter.println(Adopter.javabeanToJson(playCard));
    }

    /**
     * Die neue Position der Roboter wird aktualisiert.
     * @param x neue x-Koordinate
     * @param y neue y-Koordinate
     * @param ID ID des Spielers, der sich bewegt hat
     */
    public void updateFigure(int x, int y, int ID){
        for (Robot robot: figuren){
            if (robot != null) {
                if (robot.getGamerID() == ID) {
                    robot.setX(x);
                    robot.setY(y);
                    if (selectedMap.equals("DeathTrap")) {
                        robot.setDirection("left");
                    } else robot.setDirection("right");
                }
            }
        }
    }

    /**
     * Wenn der Spieler eine Upgrade-Karte kauft, wird dies hier dem Server mitgeteilt.
     * @param isBuying Boolean der angibt, ob der Spieler eine Karte kauft
     * @param card Karte, die der Spieler kaufen möchte
     */
    public void buyUpgrade(boolean isBuying, String card){
        BuyUpgrade buyUpgrade = new BuyUpgrade(isBuying, card);
        buyUpgrade.getMessageBody().setKeys(new String[]{"isBuying", "card"});
        bufferedWriter.println(Adopter.javabeanToJson(buyUpgrade));
    }

    /**
     * Im Rahmen der AdminPrivilege-Karte kann ein Register ausgewählt werden.
     * @param register Das ausgewählte Register
     */
    public void chooseRegister(int register){
        ChooseRegister chooseRegister = new ChooseRegister(register);
        chooseRegister.getMessageBody().setKeys(new String[]{"register"});
        bufferedWriter.println(Adopter.javabeanToJson(chooseRegister));
    }

    /**
     * Umwandlung einer Liste voll Strings in eine Liste voller Karten
     * @param array Liste mit Strings
     * @return Liste mit Karten-Objekte
     */
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

    /**
     * Die Map wird generiert.
     * @param m Die Nachricht, in der die Map verschickt wurde
     */
    public void generateMap(Message m){
        ArrayList<BoardElement>[][] map = new ArrayList[10][13];
        int i = 0;
        while (i < map.length){
            int u = 0;
            while (u < map[i].length){
                map[i][u] = new ArrayList<BoardElement>();
                u++;
            }
            i++;
        }
        ArrayList<Object> list = (ArrayList<Object>) m.getMessageBody().getContent()[0];
        int x = 0;
        while (x < list.size()){
            ArrayList<Object> y_list = (ArrayList<Object>) list.get(x);
            int y = 0;
            while (y < y_list.size()){
                ArrayList<Object> field = (ArrayList<Object>) y_list.get(y);
                int z = 0;
                while (z < field.size()){
                    LinkedTreeMap<String, Object> typ = (LinkedTreeMap<String, Object>) field.get(z);
                    if (typ == null){
                        map[y][x].add(new Empty("A"));
                    } else {
                        String zuPruefen = (String) typ.get("type");
                        String[] orientations;
                        switch (zuPruefen) {
                            case "StartPoint":
                                map[y][x].add(new StartPoint((String) typ.get("isOnBoard")));
                                break;
                            case "ConveyorBelt":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                ArrayList<BoardElement> neu = new ArrayList<>();
                                map[y][x].add(new ConveyorBelt((String) typ.get("isOnBoard"), orientations, (int) (double) typ.get("speed")));
                                break;
                            case "PushPanel":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                ArrayList<Double> list1 = (ArrayList<Double>) typ.get("registers");
                                int[] register = new int[list1.size()];
                                for (int p = 0; p < register.length; p++){
                                    register[p] = (int) (double) list1.remove(0);
                                }
                                ArrayList<BoardElement> neuePusherPanels = new ArrayList<BoardElement>();
                                neuePusherPanels.add(new PushPanel((String) typ.get("isOnBoard"), orientations, register));
                                neuePusherPanels.addAll(map[y][x]);
                                map[y][x] = neuePusherPanels;
                                //map[y][x].add(new PushPanel((String) typ.get("isOnBoard"), orientations, register));
                                break;
                            case "Gear":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                map[y][x].add(new Gear((String) typ.get("isOnBoard"), orientations));
                                break;
                            case "Pit":
                                map[y][x].add(new Pit((String) typ.get("isOnBoard")));
                                break;
                            case "EnergySpace":
                                map[y][x].add(new EnergySpace((String) typ.get("isOnBoard"), (int)(double) typ.get("count")));
                                break;
                            case "Wall":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                map[y][x].add(new Wall((String) typ.get("isOnBoard"), orientations));
                                break;
                            case "Laser":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                ArrayList<BoardElement> neueLaser = new ArrayList<BoardElement>();
                                neueLaser.add(new Laser((String) typ.get("isOnBoard"), orientations, (int)(double) typ.get("count")));
                                neueLaser.addAll(map[y][x]);
                                map[y][x] = neueLaser;
                                //map[y][x].add(new Laser((String) typ.get("isOnBoard"), orientations, (int)(double) typ.get("count")));
                                break;
                            case "Antenna":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                map[y][x].add(new Antenna((String) typ.get("isOnBoard"), orientations));
                                break;
                            case "CheckPoint":
                                map[y][x].add(new CheckPoint((String) typ.get("isOnBoard"), (int)(double) typ.get("order")));
                                break;
                            case "RestartPoint":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                map[y][x].add(new RestartPoint((String) typ.get("isOnBoard"), orientations));
                                break;
                            default:
                        }
                    }
                    z += 1;
                }
                y += 1;
            }
            x += 1;
        }
        this.map = map;
    }

    /**
     * Umwandlung einr Liste von Strings in ein Datenfeld mit Strings
     * @param list Eine Liste mit Strings
     * @return Ein Datenfeld befüllt mir Strings
     */
    public String[] changeListIntoArray(ArrayList<String> list){
        String[] orientations = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            orientations[i] = list.get(i);
        }
        return orientations;
    }

    /**
     * Nach einem reboot, kann ein Spieler eine neue Ausrichtung wählen
     * @param direction Die neue Ausrichtung des Spielers
     */
    public void setNewDirection(String direction){
        RebootDirection rebootDirection = new RebootDirection(direction);
        rebootDirection.getMessageBody().setKeys(new String[]{"direction"});
        bufferedWriter.println(Adopter.javabeanToJson(rebootDirection));
        Platform.runLater(() -> {
            getAllInOneView().setDirectionUnvisible();
        });

    }

    /**
     * Verarbeitung der returnCards Nachricht (memory swap)
     * @param cards Die Karten, die zurückgegeben werden
     */
    public void returnCards(String[] cards){
        ReturnCards returnCards = new ReturnCards(cards);
        returnCards.getMessageBody().setKeys(new String[]{"cards"});
        bufferedWriter.println(Adopter.javabeanToJson(returnCards));
    }

    /**
     * Die CheckPointReached Nachricht wird verarbeitet.
     * @param m CheckPointReached Nachricht
     * @return String, der im Chatfenster erscheint
     */
    public String handleCheckPointReached(Message m){
        int clientID = (int) (double) m.getMessageBody().getContent()[0];
        int number = (int) (double) m.getMessageBody().getContent()[1];
        String s;
        if (clientID == this.ID){
            s = "Du hast folgenden Checkpoint erreicht: " + (number+1);
        } else s = player.get(clientID).name + " (" + clientID + ") hat folgenden Checkpoint erreicht: " + (number+1);
        return s;
    }

    /**
     * Die GameFinished Nachricht wird verarbeitet.
     * @param m GameFinished Nachricht
     * @return String, der im Chatfenster erscheint
     */
    public String handleGameFinished(Message m) throws IOException {
        int clientID = (int) (double) m.getMessageBody().getContent()[0];
        String s;
        if (clientID == this.ID){
            robterGewonnen = player.get(this.ID).figur;
            istGewinner = true;
            s = "Du hast das Spiel gewonnen! Glückwunsch!";
            //GUI: GEWONNEN
            Platform.runLater(() -> {
                try {
                    getAllInOneView().runGewonnen();
                    StageSaver.getStageSaver().getAllInOneStage().close();
                    if (StageSaver.getStageSaver().getUpgradeCardsStage() != null) {
                        StageSaver.getStageSaver().getUpgradeCardsStage().close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } else {
            s = player.get(clientID).name + " (" + clientID + ") hat das Spiel gewonnen. Nächstes mal klappts bestimmt...";
            //GUI: VERLOREN
            Platform.runLater(() -> {
                try {
                    getAllInOneView().runGewonnen();
                    StageSaver.getStageSaver().getAllInOneStage().close();
                    if (StageSaver.getStageSaver().getUpgradeCardsStage() != null) {
                        StageSaver.getStageSaver().getUpgradeCardsStage().close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }
        bufferedWriter.close();
        SOCKET.close();
        bufferedReader.close();
        return s;
    }

    /**
     * Die replaceCard Nachricht wird verarbeitet.
     * @param m replaceCard Nachricht
     * @return String, der im Chatfenster erscheint
     */
    public String handleReplaceCard(Message m){
        int clientID = (int) (double) m.getMessageBody().getContent()[2];
        int register = (int) (double) m.getMessageBody().getContent()[0];
        String newCard = (String) m.getMessageBody().getContent()[1];
        String s;
        if (clientID == this.ID){
            s = "In deinem Register " + register + " wurde die Karte durch folgende Karte ersetzt: " + newCard;
        } else s = player.get(clientID).name + " (" + clientID + ") hat in Register " + register + " jetzt folgende Karte: " + newCard;
        return s;
    }

    /**
     * Die energy Nachricht wird verarbeitet.
     * @param m energy Nachricht
     * @return String, der im Chatfenster erscheint
     */
    public String handleEnergy(Message m){
        int clientID = (int) (double) m.getMessageBody().getContent()[0];
        int number = (int) (double) m.getMessageBody().getContent()[1];
        String source = (String) m.getMessageBody().getContent()[2];
        String s;
        if (clientID == this.ID){
            s = "Du hast " + number + "Energie von folgender Quelle hinzugewonnen: " + source;
            this.cubesZahl = number;
            Platform.runLater(() -> {
                getAllInOneView().updateCubes();
            });
        } else s = player.get(clientID).name + " (" + clientID + ") hat " + number + "Energie von folgender Quelle gewonnen: " + source;
        return s;
    }

    /**
     * Die pickDamage Nachricht wird verarbeitet.
     * @param m pickDamage Nachricht
     * @return String, der im Chatfenster erscheint
     */
    public String handlePickDamage(Message m){
        int count = (int)(double) m.getMessageBody().getContent()[0];
        ArrayList<String> list = (ArrayList<String>) m.getMessageBody().getContent()[1];
        //GUI: Neues Fenster: Karte wählen mit list
        Platform.runLater(() -> {
            try {
                getAllInOneView().runDamageCardExtra();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return "Der Stapel mit den Schadenskarten ist leer. Bitte wähle eine andere Sorte.";
    }

    /**
     * Die refillShop Nachricht wird verarbeitet.
     * @param m refillShop Nachricht
     * @return String, der im Chatfenster erscheint
     */
    public String handleRefillShop(Message m){
        ArrayList<String> karten = (ArrayList<String>) m.getMessageBody().getContent()[0];
        for (String s: karten){
            this.upgradeShop.add(s);
        }
        String toSend = "Der Shop wurde aufgefüllt. Folgende Karten befinden sich jetzt im Shop: " + "\n" + "| ";
        for (String s: upgradeShop){
            toSend += s + " |";
        }
        Platform.runLater(() -> {
            try {
                getAllInOneView().runUpgradeCards();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return toSend;
    }

    /**
     * Die exchangeShop Nachricht wird verarbeitet.
     * @param m exchangeShop Nachricht
     * @return String, der im Chatfenster erscheint
     */
    public String handleExchangeShop(Message m){
        ArrayList<String> karten = (ArrayList<String>) m.getMessageBody().getContent()[0];
        this.upgradeShop = karten;
        String toSend = "Der Shop wurde erneuert. Folgende Karten befinden sich jetzt im Shop: " + "\n" + "| ";
        for (String s: upgradeShop){
            toSend += s + " |";
        }
        Platform.runLater(() -> {
            try {
                getAllInOneView().setUnvisibleUpGradeCards();
                getAllInOneView().runUpgradeCards();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        return toSend;
    }

    /**
     * Die upgradeBought Nachricht wird verarbeitet.
     * @param m upgradeBought Nachricht
     * @return String, der im Chatfenster erscheint
     */
    public String handleUpgradeBought(Message m){
        int clientID = (int)(double)m.getMessageBody().getContent()[0];
        String card = (String) m.getMessageBody().getContent()[1];
        String s;
        setUpgradeCardName(card);
        if (clientID == this.ID){
            Platform.runLater(() -> {
                getAllInOneView().setImageForUpgradeCard(card);
            });
            int costs = 0;
            if (card.equals("AdminPrivilege") || card.equals("SpamBlocker")){
                costs = 3;
            } else if (card.equals("RearLaser")){
                costs = 2;
            } else if (card.equals("MemorySwap")){
                costs = 1;
            }
            this.cubesZahl -= costs;
            Platform.runLater(() -> {
                getAllInOneView().updateCubes();
            });
            s = "Du hast folgende Karte gekauft: " + card;
        } else s = player.get(clientID).name + " (" + clientID + ") hat folgende Karte gekauft: " + card;
        this.upgradeShop.remove(card);
        return s;
    }

    /**
     * Die registerChosen Nachricht wird verarbeitet.
     * @param m registerChosen Nachricht
     * @return String, der im Chatfenster erscheint
     */
    public String handleRegisterChosen(Message m){
        int clientID = (int)(double)m.getMessageBody().getContent()[0];
        int register = (int)(double)m.getMessageBody().getContent()[1];
        String s;
        if (clientID == this.ID){
            s = "Du hast folgendes Register gewählt: " + register;
        } else s = player.get(clientID).name + " (" + clientID + ") hat folgendes Register (mit AdminPrivilege) gewählt: " + register;
        return s;
    }

    /**
     * Die connectionUpdate Nachricht wird verarbeitet.
     * @param m connectionUpdate Nachricht
     * @return String, der im Chatfenster erscheint
     */
    public String handleConnectionUpdate(Message m){
        int clientID = (int)(double) m.getMessageBody().getContent()[0];
        Player player = this.player.remove(clientID);
        usernamesGui.remove(clientID + "," + player.name);
        figuren[player.figur] = null;

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                getAllInOneView().setDefaultMap();
                for (int i = 0; i < figuren.length; i++) {
                    if (figuren[i] != null && figuren[i].getX() != -1) {

                        getAllInOneView().setFigureOnMapNew(i, figuren[i].getDirection(), figuren[i].getX(), figuren[i].getY());
                    }
                }
            }
        });

        return player.name + " (" + clientID + ") hat die Verbindung verloren und wurde entfernt.";
    }

    /**
     * Die checkpointMoved Nachricht wird verarbeitet.
     * @param m checkpointMoved Nachricht
     */
    public void handleCheckPointMoved(Message m){
        int checkpointID = (int) (double) m.getMessageBody().getContent()[0];
        int x = (int) (double) m.getMessageBody().getContent()[1];
        int y = (int) (double) m.getMessageBody().getContent()[2];
        Platform.runLater(() -> {
            getAllInOneView().moveCheckpoints(checkpointID, x, y);
            /*for (int i = 0; i < figuren.length; i++) {
                if (figuren[i] != null && figuren[i].getX() != -1) {
                    getAllInOneView().setFigureOnMapNew(i, figuren[i].getDirection(), figuren[i].getX(), figuren[i].getY());
                }
            }*/

        });
    }

    /**
     * Diese Methode überschreibt die run() Methode aus Runnable.
     * Sie empfängt und verarbeitet die Daten, die vom Server empfangen werden.
     */
    @Override
    public void run() {
        while (true) {
            try {
                String toSend;
                String inputFromServer = bufferedReader.readLine(); // Data read from the game.Server.
                if (inputFromServer == null) {
                    break;
                }
                ralleyLogger.getLogger().info(inputFromServer);
                Message message = Adopter.getMessage(inputFromServer);
                if(message.getMessageType().equals("HelloClient")){
                    sendHelloServer(message);
                    toSend = "Das Protokoll wurde eingelesen. Verwendete Version: " + protocol;
                } else if (message.getMessageType().equals("Error1")) {
                    toSend = (String) message.getMessageBody().getContent()[0];
                } else if (message.getMessageType().equals("Welcome")){
                    double wert = (double) message.getMessageBody().getContent()[0];
                    ID = (int) wert;
                    connected = true;
                    toSend = "Willkommen im Chat. Deine ID wurde erfolgreich generiert.";
                } else if(message.getMessageType().equals("ReceivedChat")){
                    int from = (int)(double)message.getMessageBody().getContent()[1];
                    boolean isPrivate = (boolean) message.getMessageBody().getContent()[2];
                    String nachricht = (String) message.getMessageBody().getContent()[0];
                    toSend = player.get(from).name + " (" + from + "): " + nachricht;
                } else if(message.getMessageType().equals("Alive")){
                    bufferedWriter.println("{\"messageType\": \"Alive\", \"messageBody\": {}}");
                    toSend = null;
                } else if(message.getMessageType().equals("PlayerAdded")){
                    int newFigure = (int)(double) message.getMessageBody().getContent()[2];
                    int clientID = (int)(double) message.getMessageBody().getContent()[0];
                    String username = (String) message.getMessageBody().getContent()[1];
                    if (username != null && !username.equals("null")) {
                        figuren[newFigure] = new Robot(clientID);
                        usernamesGui.add(clientID + "," + username);
                        figurenForGui.add(figureForGui);
                        Player newPlayer = new Player(clientID, username, newFigure);
                        player.put(clientID, newPlayer);
                        toSend = username + " hat sich verbunden. Er/Sie spielt mit Figur: " + newFigure;
                    } else toSend = null;
                } else if(message.getMessageType().equals("PlayerStatus")){
                    boolean isReady = (boolean) message.getMessageBody().getContent()[1];
                    int clientID = (int) (double) message.getMessageBody().getContent()[0];
                    for(Player player: player.values()){
                        if(player.ID == clientID){
                            player.ready = isReady;
                        }
                    }
                    if(isReady){
                        toSend = player.get(clientID).name + " ist jetzt bereit.";
                    } else {
                        toSend = player.get(clientID).name + " ist nicht mehr bereit.";
                    }
                } else if (message.getMessageType().equals("SelectMap")){
                    Platform.runLater(() -> {
                        try {
                            getChatView().selectMap();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                    toSend = "Bitte wähle die Map aus.";
                } else if (message.getMessageType().equals("MapSelected")){
                    String map = (String) message.getMessageBody().getContent()[0];
                    selectedMap = map;
                    toSend = "Folgende Map wurde ausgewählt: " + map;
                } else if (message.getMessageType().equals("ActivePhase")){
                    int activePhase = (int) (double) message.getMessageBody().getContent()[0];
                    if (activePhase == 0){
                        //GUI? StartBoard auswählen
                        toSend = "Die Aufbauphase läuft aktuell. Bitte wähle deine Startposition";
                    } else if (activePhase == 1){
                        //UpgradePhase? GUI
                        toSend = "Die UpgradePhase läuft aktuell.";
                    } else if (activePhase == 2){
                        //ProgrammierPhase? GUI?
                        toSend = "Die Programmierphase läuft aktuell.";
                    } else if (activePhase == 3){
                        //AktivierungsPhase? GUI?
                        toSend = "Die Aktivierungsphase läuft aktuell.";
                    } else toSend = null;
                } else if (message.getMessageType().equals("CurrentPlayer")){
                    int activePlayer = (int)(double)message.getMessageBody().getContent()[0];
                    if (this.ID == activePlayer){
                        //GUI?
                        toSend = "Du bist am Zug.";
                    } else {
                        //GUI?
                        String name = player.get(activePlayer).name;
                        toSend = name + " (" + activePlayer + ") " + "ist aktuell am Zug";
                    }
                } else if (message.getMessageType().equals("GameStarted")){
                    generateMap(message);
                    Platform.runLater(() -> {
                        try {
                            getChatView().runAllInOne();
                            StageSaver.getStageSaver().getChatViewStage().close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });
                    toSend = "Das Spiel wurde jetzt gestartet.";
                }
                else if (message.getMessageType().equals("StartingPointTaken")){
                    int x = (int) (double) message.getMessageBody().getContent()[0];
                    int y = (int) (double) message.getMessageBody().getContent()[1];
                    int clientID = (int) (double) message.getMessageBody().getContent()[2];
                    updateFigure(x, y, clientID);

                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            for (int i = 0; i < figuren.length; i++) {
                                if (figuren[i] != null && figuren[i].getX() != -1) {
                                   getAllInOneView().setFigureOnMapNew(i, figuren[i].getDirection(), figuren[i].getX(), figuren[i].getY());
                                }
                            }
                        }
                    });

                    toSend = player.get(clientID).name + " (" + clientID + ") hat seine Startposition gewählt.";
                } else if (message.getMessageType().equals("YourCards")){
                    ArrayList<String> cards = (ArrayList<String>) message.getMessageBody().getContent()[0];
                    ArrayList<Cards> handkarten = this.arrayToList(cards);
                    figuren[player.get(this.ID).figur].setHandCards(handkarten);
                    String senden = "Folgende Karten befinden sich auf deiner Hand: ";
                    for (int i = 0; i < figuren[player.get(this.ID).figur].getHandCards().size(); i++){
                        senden += figuren[player.get(this.ID).figur].getHandCards().get(i).getName() + " ";
                    }
                    if (figuren[player.get(this.ID).figur].getHandCards().size() == 12){
                        //GUI --> Karten abgeben!!
                        Platform.runLater(() -> {
                            try {
                                StageSaver.getStageSaver().getUpgradeCardsStage().close();
                                getAllInOneView().resetRegisterCard();
                                getAllInOneView().runChooseCardsForSwap();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });

                    } else {
                        Platform.runLater(() -> {
                            try {
                                StageSaver.getStageSaver().getUpgradeCardsStage().close();
                                getAllInOneView().resetRegisterCard();
                                getAllInOneView().fillChooseCard();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                    }
                    toSend = senden;
                } else if (message.getMessageType().equals("NotYourCards")){
                    int ID = (int) (double) message.getMessageBody().getContent()[0];
                    int cardsInHand = (int) (double) message.getMessageBody().getContent()[1];
                    toSend = player.get(ID).name + " (" + ID + ") hat " + cardsInHand + " Karten auf der Hand";
                } else if (message.getMessageType().equals("CardSelected")){
                    int ID = (int) (double) message.getMessageBody().getContent()[0];
                    int register = (int) (double) message.getMessageBody().getContent()[1];
                    boolean filled = (boolean) message.getMessageBody().getContent()[2];
                    if (filled) {
                        toSend = player.get(ID).name + " (" + ID + ") hat folgendes Register befüllt: " + register;
                    } else {
                        toSend = player.get(ID).name + " (" + ID + ") hat folgendes Register geleert: " + register;
                    }
                } else if (message.getMessageType().equals("TimerStarted")){
                    toSend = "Der Timer wurde gestartet.";
                    Platform.runLater(() -> {
                        getAllInOneView().startTimer();
                        if (getChooseCardsForSwap() != null){
                            getChooseCardsForSwap().startTimer();
                        }
                    });
                } else if (message.getMessageType().equals("TimerEnded")){
                    ArrayList<Double> list = (ArrayList<Double>) message.getMessageBody().getContent()[0];
                    String s = "Der Timer ist beendet." + "\n" + "Folgende Spieler sind nicht fertig geworden: ";
                    Platform.runLater(() -> {
                        getAllInOneView().setChooseCardUnvisible();
                        if (getChooseCardsForSwap() != null){
                            StageSaver.getStageSaver().getUpgradeCardsForSwap().close();
                        }
                    });
                    Platform.runLater(() -> {
                        getAllInOneView().hideTimer();
                    });
                    for (Double doubl: list){
                        s += "(" + doubl + ") ";
                    }
                    toSend = s;
                } else if (message.getMessageType().equals("SelectionFinished")){
                    int ID = (int) (double) message.getMessageBody().getContent()[0];
                    toSend = player.get(ID).name + " (" + ID + ") hat seine Auswahl erfolgreich beendet.";
                } else if (message.getMessageType().equals("Movement")){
                    int ID = (int) (double) message.getMessageBody().getContent()[0];
                    int x = (int) (double) message.getMessageBody().getContent()[1];
                    int y = (int) (double) message.getMessageBody().getContent()[2];
                    Robot robot = figuren[player.get(ID).figur];
                    robot.setX(x);
                    robot.setY(y);
                    TimeUnit. SECONDS.sleep(1);
                    Platform.runLater(() -> {
                       getAllInOneView().setDefaultMap();
                        for (int i = 0; i < figuren.length; i++) {
                            if (figuren[i] != null && figuren[i].getX() != -1) {
                                getAllInOneView().setFigureOnMapNew(i, figuren[i].getDirection(), figuren[i].getX(), figuren[i].getY());
                        }
                    }});
                    toSend = player.get(ID).name + " (" + ID + ") hat seine Position verändert";
                } else if (message.getMessageType().equals("PlayerTurning")){
                    int ID = (int) (double) message.getMessageBody().getContent()[0];
                    String direction = (String) message.getMessageBody().getContent()[1];
                    Robot robot = figuren[player.get(ID).figur];
                    switch (robot.getDirection()){
                        case "top":
                            if (direction.equals("clockwise")){
                                robot.setDirection("right");
                            } else robot.setDirection("left");
                            break;
                        case "bottom":
                            if (direction.equals("clockwise")){
                                robot.setDirection("left");
                            } else robot.setDirection("right");
                            break;
                        case "left":
                            if (direction.equals("clockwise")){
                                robot.setDirection("top");
                            } else robot.setDirection("bottom");
                            break;
                        case "right":
                            if (direction.equals("clockwise")){
                                robot.setDirection("bottom");
                            } else robot.setDirection("top");
                            break;
                    }
                    TimeUnit. SECONDS. sleep(1);
                    Platform.runLater(() -> {
                        getAllInOneView().setDefaultMap();
                        for (int i = 0; i < figuren.length; i++) {
                            if (figuren[i] != null && figuren[i].getX() != -1 && figuren[i].getY() != -1) {
                                getAllInOneView().setFigureOnMapNew(i, figuren[i].getDirection(), figuren[i].getX(), figuren[i].getY());
                            }
                        }});
                    toSend = player.get(ID).name + " (" + ID + ") hat sich gedreht.";
                } else if (message.getMessageType().equals("CurrentCards")){
                    List<LinkedTreeMap> list = (List<LinkedTreeMap>) message.getMessageBody().getContent()[0];
                    String s = "Folgende Karten wurden im aktuellen Register gelegt: ";
                    for (LinkedTreeMap<String, Object> tree: list){
                        int clientID = (int) (double) tree.get("clientID");
                        String card = (String) tree.get("card");
                        s += "\n" + "Von Spieler/in " + player.get(clientID).name + " (" + clientID + "): " + card;
                    }
                    toSend = s;
                } else if (message.getMessageType().equals("CardsYouGotNow")){
                    List<String> list = (List<String>) message.getMessageBody().getContent()[0];
                    String s = "Weil du zu langsam gewesen bist, besitzt du jetzt folgende Karten: \n";
                    for (String string: list){
                        s += "| " + string + " |";
                    }
                    toSend = s;
                } else if (message.getMessageType().equals("Reboot")){
                    int clientID = (int) (double) message.getMessageBody().getContent()[0];
                    figuren[player.get(clientID).figur].setDirection("top");
                    Platform.runLater(() -> {
                        getAllInOneView().setDefaultMap();
                        for (int i = 0; i < figuren.length; i++) {
                            if (figuren[i] != null && figuren[i].getX() != -1 && figuren[i].getY() != -1) {
                                getAllInOneView().setFigureOnMapNew(i, figuren[i].getDirection(), figuren[i].getX(), figuren[i].getY());
                            }
                        }});
                    if (this.ID == clientID) {
                        toSend = "Du bist gestorben. Bitte wähle eine neue Richtung aus";
                        Platform.runLater(() -> {
                            getAllInOneView().ChooseDirectionSetvisible();
                        });
                    } else toSend = player.get(clientID).name + " (" + clientID + ") ist gestorben";
                } else if (message.getMessageType().equals("DrawDamage")){
                    toSend = handleDamage(message);
                } else if (message.getMessageType().equals("CheckPointReached")){
                    toSend = handleCheckPointReached(message);
                } else if (message.getMessageType().equals("GameFinished")){
                    toSend = handleGameFinished(message);
                } else if (message.getMessageType().equals("ReplaceCard")){
                    toSend = handleReplaceCard(message);
                } else if (message.getMessageType().equals("Energy")){
                    toSend = handleEnergy(message);
                } else if (message.getMessageType().equals("PickDamage")){
                    toSend = handlePickDamage(message);
                } else if (message.getMessageType().equals("RefillShop")){
                    toSend = handleRefillShop(message);
                } else if (message.getMessageType().equals("ExchangeShop")){
                    toSend = handleExchangeShop(message);
                } else if (message.getMessageType().equals("UpgradeBought")) {
                    toSend = handleUpgradeBought(message);
                } else if (message.getMessageType().equals("RegisterChosen")){
                    toSend = handleRegisterChosen(message);
                } else if (message.getMessageType().equals("ConnectionUpdate")) {
                    toSend = handleConnectionUpdate(message);
                } else if (message.getMessageType().equals("CheckpointMoved")){
                    handleCheckPointMoved(message);
                    toSend = null;
                } else {
                    toSend = inputFromServer;
                }

                Platform.runLater(() -> {
                    if (toSend != null) {
                        chatMessages.add(toSend); // Adding the input to Chat window.
                    }
                });
            } catch (IOException e) {
                e.printStackTrace();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Die damage Nachricht wird verarbeitet.
     * @param m damage Nachricht
     * @return String, der im Chatfenster erscheint
     */
    public String handleDamage(Message m){
        int clientID = (int) (double) m.getMessageBody().getContent()[0];
        List<String> damageCards = (List<String>) m.getMessageBody().getContent()[1];
        String s;
        if (clientID == this.ID){
            s = "Du musstest folgende Schadenskarten ziehen: " + "\n";
        } else s = player.get(clientID).name + " (" + clientID + ") musste folgende Schadenskarten ziehen: " + "\n";
        for (String card: damageCards){
            s += "| " + card + " ";
        }
        s += "|";
        return s;
    }


    public String getSelectedMap(){
        return selectedMap;
    }


    public static void setChatViewModel(ChatView chatView){
        chatView1 = chatView;
    }

    public static void setSelectMapView(SelectMapView selectMapView1){
        selectMapView = selectMapView1;
    }

    public ChatView getChatView(){
        return chatView1;
    }

    public static void setAllInOneView(AllInOneView allInOneView1){
        allInOneView = allInOneView1;
    }
    public AllInOneView getAllInOneView(){
        return allInOneView;
    }

    public static void setFirstView(FirstView firstView1){
        firstView = firstView1;
    }

    public FirstView getFirstView(){
        return firstView;
    }

    public ArrayList<Cards> getHandcards(){
        return figuren[player.get(ID).figur].getHandCards();
    }

    public ArrayList<BoardElement>[][] getMap(){
        return map;
    }

    public ArrayList<String> getUpgradeShop(){
        return upgradeShop;
    }

    public int getCubesZahl(){
        return cubesZahl;
    }

    public static void setChooseCardsForSwap(ChooseCardsForSwap chooseCardsForSwap1){
        chooseCardsForSwap = chooseCardsForSwap1;
    }

    public ChooseCardsForSwap getChooseCardsForSwap(){
        return chooseCardsForSwap;
    }

    public void setUpgradeCardName(String name){
        if (UpgradeCardName[0].equals("leer")){
            UpgradeCardName[0] = name;
        }
        else {
            UpgradeCardName[1] = name;
        }
    }

    public String[] getUpgradeCardName(){
        return UpgradeCardName;
    }

    public String getTitleUserName(){
        return titleUserName;
    }

    public Socket getSOCKET() {
        return SOCKET;
    }

    public int getRobterGewonnen(){
        return robterGewonnen;
    }

    public static Client getClient(){
        return client;
    }

    public void setReady(){
        if(ready){
            ready = false;
            player.get(ID).ready = false;
            SetStatus setStatus = new SetStatus(false);
            bufferedWriter.println(Adopter.javabeanToJson(setStatus));
        } else if (!ready){
            ready = true;
            if (player.get(ID) != null) {
                player.get(ID).ready = true;
            }
            SetStatus setStatus = new SetStatus(true);
            bufferedWriter.println(Adopter.javabeanToJson(setStatus));
        }
    }

    public void setCardOfGui(String cardName){
        this.CardOfGui = cardName;
    }

    public String getCardOfGui(){
        return CardOfGui;
    }

    public int getID(){
        return ID;
    }

    public boolean getIstGewinner(){
        return istGewinner;
    }


}

import Messages.*;
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
import java.util.Collections;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * In dieser Klasse agiert die künstliche Intelligenz für das Spiel Robo-Rally.
 *
 * @author Luca Weyhofen
 *
 * @version 2.1
 */
public class KI implements Runnable {

    private final Socket SOCKET;
    private final String GROUP = "Innige Irrwege";
    private final boolean isAi;
    private boolean connected;
    private int ID;
    private ArrayList<BoardElement>[][] map;
    private ArrayList<String> upgradeShop;

    private final BufferedReader bufferedReader;
    private final PrintWriter bufferedWriter;

    public String protocol;
    private HashMap<Integer, Player> player = new HashMap<Integer, Player>();
    private Robot[] figuren = new Robot[6];
    private String selectedMap;
    private boolean figurSelected = false;
    private boolean startPointTaken = false;
    private boolean upgradeBought = false;
    private int nextCheckPoint;


    /**
     * Dies ist der Konstruktor der Klasse.
     * Hier verbindet sich die KI mit dem Server.
     * @throws IOException Wenn die Verbindung zum Server nicht klappt, wird diese Exception geworfen
     */
    public KI() throws IOException {
        SOCKET = new Socket("localhost", 1237);
        bufferedReader = new BufferedReader(new InputStreamReader(SOCKET.getInputStream()));
        bufferedWriter = new PrintWriter(SOCKET.getOutputStream(), true);
        nextCheckPoint = 0;
        isAi = true;
    }

    /**
     * Diese Methode setzt die KI auf den Status "bereit".
     */
    public void setReady() {
        if (player.get(ID) != null) {
            player.get(ID).ready = true;
        }
        SetStatus setStatus = new SetStatus(true);
        bufferedWriter.println(Adopter.javabeanToJson(setStatus));
    }

    /**
     * Hier wird ein zufälliger Name für die KI generiert und dem Server mitgeteilt.
     */
    public void configuration() {
        String name;
        int figur = (int) Math.floor(Math.random() * 6);
        if (figur == 1) {
            name = "BummBot";
        } else if (figur == 2) {
            name = "Annihilator 3000";
        } else if (figur == 3) {
            name = "Oma Manfred";
        } else if (figur == 4) {
            name = "MONSTER_Garry";
        } else if (figur == 5) {
            name = "RobotKarol";
        } else {
            name = "CrashBot";
        }

        PlayerValues message = new PlayerValues(name, figur);
        String[] keys = {"name", "figure"};
        message.getMessageBody().setKeys(keys);
        bufferedWriter.println(Adopter.javabeanToJson(message));
    }

    /**
     * In dieser Methode wählt die KI einen zufälligen Startpunkt.
     */
    public void setStartingPoint() {
        startPointTaken = true;
        ArrayList<Integer[]> list = new ArrayList();
        if (selectedMap.equals("DeathTrap")) {
            list.add(new Integer[]{1, 11});
            list.add(new Integer[]{3, 12});
            list.add(new Integer[]{4, 11});
            list.add(new Integer[]{5, 11});
            list.add(new Integer[]{6, 12});
            list.add(new Integer[]{8, 11});
        } else {
            list.add(new Integer[]{1, 1});
            list.add(new Integer[]{3, 0});
            list.add(new Integer[]{4, 1});
            list.add(new Integer[]{5, 1});
            list.add(new Integer[]{6, 0});
            list.add(new Integer[]{8, 1});
        }
        Collections.shuffle(list);

        for (int i = 0; i < list.size(); i++) {
            boolean free = true;
            for (Robot robot : figuren) {
                if (robot != null) {
                    if (robot.getX() == list.get(i)[0] && robot.getY() == list.get(i)[1]) {
                        free = false;
                    }
                }
            }
            if (free) {
                SetStartingPoint setStartingPoint = new SetStartingPoint(list.get(i)[0], list.get(i)[1]);
                setStartingPoint.getMessageBody().setKeys(new String[]{"x", "y"});
                bufferedWriter.println(Adopter.javabeanToJson(setStartingPoint));
                return;
            }
        }
    }

    /**
     * Die Nachricht empfängt die Willkommensnachricht des Servers und verschickt die HelloServer Nachricht
     * @param message Die Willkommensnachricht des Servers
     */
    public void sendHelloServer(Message message) {
        protocol = (String) message.getMessageBody().getContent()[0];
        HelloServer output = new HelloServer(GROUP, isAi, protocol);
        String[] keys = {"group", "isAI", "protocol"};
        output.getMessageBody().setKeys(keys);
        String S = Adopter.javabeanToJson(output);
        bufferedWriter.println(S);
    }

    /**
     * In dieser Methode werden die gewünschte Karte in ein gewünschtes Register gelegt.
     * @param card Die Karte, die gespielt werden soll
     * @param register Das Register, in das die Karte gelegt werden soll
     */
    public void sendCardToRegister(String card, int register) {
        SelectedCard selectedCard = new SelectedCard(card, register);
        selectedCard.getMessageBody().setKeys(new String[]{"card", "register"});
        bufferedWriter.println(Adopter.javabeanToJson(selectedCard));
    }

    /**
     * Sollte die KI eine zufällige Map auswählen dürfen, wird diese hier verschickt.
     * @param map Die Map, die ausgewählt wurde
     */
    public void mapSelected(String map) {
        MapSelected mapSelected = new MapSelected(map);
        String[] key = {"map"};
        mapSelected.getMessageBody().setKeys(key);
        bufferedWriter.println(Adopter.javabeanToJson(mapSelected));
    }

    /**
     * Aktualisierung der Positionen der Roboter.
     * @param x neue x-Koordinate
     * @param y neue y-Koordinate
     * @param ID ID des Roboters, der seine Position geändert hat
     */
    public void updateFigure(int x, int y, int ID) {
        for (Robot robot : figuren) {
            if (robot != null) {
                if (robot.getGamerID() == ID) {
                    robot.setX(x);
                    robot.setY(y);
                }
                if (selectedMap.equals("DeathTrap")) {
                    robot.setDirection("left");
                } else robot.setDirection("right");
            }
        }
    }

    /**
     * In dieser Methode werden die Karten berechnet und gelegt, die die KI spielen soll.
     * @param list Liste an verfügbaren Karten
     */
    public void playCards(ArrayList<String> list) {
        Spielwiese spielwiese = new Spielwiese(this.map, list);
        list = spielwiese.simulate(figuren[player.get(this.ID).figur], this.nextCheckPoint);
        try {
            TimeUnit.SECONDS.sleep(2);
            sendCardToRegister(list.get(0), 0);
            TimeUnit.SECONDS.sleep(2);
            sendCardToRegister(list.get(1), 1);
            TimeUnit.SECONDS.sleep(2);
            sendCardToRegister(list.get(2), 2);
            TimeUnit.SECONDS.sleep(2);
            sendCardToRegister(list.get(3), 3);
            TimeUnit.SECONDS.sleep(2);
            sendCardToRegister(list.get(4), 4);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Diese Methode verwandelt eine String Liste in Array
     * @param list Die umzuwandelnde Liste
     * @return Das fertige Array
     */
    public String[] changeListIntoArray(ArrayList<String> list) {
        String[] orientations = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            orientations[i] = list.get(i);
        }
        return orientations;
    }

    /**
     * Hier wird die Karte generiert.
     * @param m Die Nachricht mit der Map, die vom Server verschickt wurde
     */
    public void generateMap(Message m) {
        ArrayList<BoardElement>[][] map = new ArrayList[10][13];
        int i = 0;
        while (i < map.length) {
            int u = 0;
            while (u < map[i].length) {
                map[i][u] = new ArrayList<BoardElement>();
                u++;
            }
            i++;
        }
        ArrayList<Object> list = (ArrayList<Object>) m.getMessageBody().getContent()[0];
        int x = 0;
        while (x < list.size()) {
            ArrayList<Object> y_list = (ArrayList<Object>) list.get(x);
            int y = 0;
            while (y < y_list.size()) {
                ArrayList<Object> field = (ArrayList<Object>) y_list.get(y);
                int z = 0;
                while (z < field.size()) {
                    LinkedTreeMap<String, Object> typ = (LinkedTreeMap<String, Object>) field.get(z);
                    if (typ == null) {
                        map[y][x].add(new Empty("A"));
                    } else {
                        String zuPrüfen = (String) typ.get("type");
                        String[] orientations;
                        switch (zuPrüfen) {
                            case "StartPoint":
                                map[y][x].add(new StartPoint((String) typ.get("isOnBoard")));
                                break;
                            case "ConveyorBelt":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                map[y][x].add(new ConveyorBelt((String) typ.get("isOnBoard"), orientations, (int) (double) typ.get("speed")));
                                break;
                            case "PushPanel":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                ArrayList<Double> list1 = (ArrayList<Double>) typ.get("registers");
                                int[] register = new int[list1.size()];
                                for (int p = 0; p < register.length; p++) {
                                    register[p] = (int) (double) list1.remove(0);
                                }
                                map[y][x].add(new PushPanel((String) typ.get("isOnBoard"), orientations, register));
                                break;
                            case "Gear":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                map[y][x].add(new Gear((String) typ.get("isOnBoard"), orientations));
                                break;
                            case "Pit":
                                map[y][x].add(new Pit((String) typ.get("isOnBoard")));
                                break;
                            case "EnergySpace":
                                map[y][x].add(new EnergySpace((String) typ.get("isOnBoard"), (int) (double) typ.get("count")));
                                break;
                            case "Wall":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                map[y][x].add(new Wall((String) typ.get("isOnBoard"), orientations));
                                break;
                            case "Laser":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                map[y][x].add(new Laser((String) typ.get("isOnBoard"), orientations, (int) (double) typ.get("count")));
                                break;
                            case "Antenna":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                map[y][x].add(new Antenna((String) typ.get("isOnBoard"), orientations));
                                break;
                            case "CheckPoint":
                                map[y][x].add(new CheckPoint((String) typ.get("isOnBoard"), (int) (double) typ.get("order")));
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
     * Diese Methode kauft für die KI UpgradeKarten.
     */
    public void buyUpgradeCard(){
        boolean isBuying = false;
        String card = null;
        for (String s: upgradeShop){
            if (s.equals("RearLaser")){
                isBuying = true;
                card = "RearLaser";
            }
        }
        if (isBuying && !upgradeBought){
            upgradeBought = true;
            BuyUpgrade buyUpgrade = new BuyUpgrade(isBuying, card);
            buyUpgrade.getMessageBody().setKeys(new String[]{"isBuying", "card"});
            bufferedWriter.println(Adopter.javabeanToJson(buyUpgrade));
        } else {
            BuyUpgrade buyUpgrade = new BuyUpgrade(false, null);
            buyUpgrade.getMessageBody().setKeys(new String[]{"isBuying", "card"});
            bufferedWriter.println(Adopter.javabeanToJson(buyUpgrade));
        }
    }

    /**
     * Verarbeitet die CheckPointReached Nachricht
     * @param m Die Nachricht vom Server
     */
    public void handleCheckPointReached(Message m) {
        int clientID = (int) (double) m.getMessageBody().getContent()[0];
        if (clientID == this.ID) {
            nextCheckPoint += 1;
        }
    }

    /**
     * Verarbeitet die exchangeShop Nachricht
     * @param m Die Nachricht
     * @return null
     */
    public String handleExchangeSho(Message m) {
        ArrayList<String> karten = (ArrayList<String>) m.getMessageBody().getContent()[0];
        this.upgradeShop = karten;
        return null;
    }

    /**
     * Verarbeitung der refillShop Nachricht
     * @param m RefillShop Nachricht
     * @return null
     */
    public String handleRefillSho(Message m) {
        ArrayList<String> karten = (ArrayList<String>) m.getMessageBody().getContent()[0];
        for (String s : karten) {
            this.upgradeShop.add(s);
        }
        return null;
    }

    /**
     * Verarbeitung der UpgradeBought Nachricht
     * @param m UpgradeBought Nachricht
     * @return null
     */
    public String handleUpgradeBough(Message m) {
        int clientID = (int) (double) m.getMessageBody().getContent()[0];
        String card = (String) m.getMessageBody().getContent()[1];
        this.upgradeShop.remove(card);
        return null;
    }

    /**
     * Diese Methode überschreibt die Methode run() aus Runnable.
     * Hier werden die eingehenden Nachrichten vom Server verarbeitet und es wird entsprechend darauf reagiert.
     */
    @Override
    public void run() {
        while (true) {
            try {
                String inputFromServer = bufferedReader.readLine(); // Data read from the game.Server.
                if (inputFromServer == null) {
                    break;
                }
                Message message = Adopter.getMessage(inputFromServer);
                if(message.getMessageType().equals("HelloClient")){
                    sendHelloServer(message);
                } else if (message.getMessageType().equals("Error1")) {
                    if (!figurSelected) {
                        configuration();
                    }
                } else if (message.getMessageType().equals("Welcome")){
                    double wert = (double) message.getMessageBody().getContent()[0];
                    ID = (int) wert;
                    connected = true;
                    configuration();
                } else if(message.getMessageType().equals("ReceivedChat")){

                } else if(message.getMessageType().equals("Alive")){
                    bufferedWriter.println("{\"messageType\": \"Alive\", \"messageBody\": {}}");
                } else if(message.getMessageType().equals("PlayerAdded")){
                    int newFigure = (int)(double) message.getMessageBody().getContent()[2];
                    int clientID = (int)(double) message.getMessageBody().getContent()[0];
                    String username = (String) message.getMessageBody().getContent()[1];
                    if (clientID == this.ID) figurSelected = true;
                    figuren[newFigure] = new Robot(clientID);
                    Player newPlayer = new Player(clientID, username, newFigure);
                    player.put(clientID, newPlayer);
                    if (clientID == ID) setReady();
                } else if(message.getMessageType().equals("PlayerStatus")){
                    boolean isReady = (boolean) message.getMessageBody().getContent()[1];
                    int clientID = (int) (double) message.getMessageBody().getContent()[0];
                    for(Player player: player.values()){
                        if(player.ID == clientID){
                            player.ready = isReady;
                        }
                    }
                } else if (message.getMessageType().equals("SelectMap")){
                    ArrayList<String> names = (ArrayList<String>) message.getMessageBody().getContent()[0];
                    Collections.shuffle(names);
                    mapSelected(names.get(0));
                } else if (message.getMessageType().equals("MapSelected")){
                    String map = (String) message.getMessageBody().getContent()[0];
                    selectedMap = map;
                } else if (message.getMessageType().equals("ActivePhase")){
                    int activePhase = (int) (double) message.getMessageBody().getContent()[0];
                } else if (message.getMessageType().equals("CurrentPlayer")){
                    int activePlayer = (int)(double)message.getMessageBody().getContent()[0];
                    if (this.ID == activePlayer){
                        if (!startPointTaken) {
                            setStartingPoint();
                        } else {
                            buyUpgradeCard();
                        }
                    }
                } else if (message.getMessageType().equals("GameStarted")){
                    generateMap(message);
                }
                else if (message.getMessageType().equals("StartingPointTaken")){
                    int x = (int) (double) message.getMessageBody().getContent()[0];
                    int y = (int) (double) message.getMessageBody().getContent()[1];
                    int clientID = (int) (double) message.getMessageBody().getContent()[2];
                    updateFigure(x, y, clientID);
                } else if (message.getMessageType().equals("YourCards")){
                    ArrayList<String> cards = (ArrayList<String>) message.getMessageBody().getContent()[0];
                    playCards(cards);
                } else if (message.getMessageType().equals("NotYourCards")){

                } else if (message.getMessageType().equals("CardSelected")){

                } else if (message.getMessageType().equals("TimerStarted")){

                } else if (message.getMessageType().equals("TimerEnded")){

                } else if (message.getMessageType().equals("SelectionFinished")){

                } else if (message.getMessageType().equals("Movement")){
                    int ID = (int) (double) message.getMessageBody().getContent()[0];
                    int x = (int) (double) message.getMessageBody().getContent()[1];
                    int y = (int) (double) message.getMessageBody().getContent()[2];
                    Robot robot = figuren[player.get(ID).figur];
                    robot.setX(x);
                    robot.setY(y);
                } else if (message.getMessageType().equals("PlayerTurning")){
                    try {
                        int ID = (int) (double) message.getMessageBody().getContent()[0];
                        String direction = (String) message.getMessageBody().getContent()[1];
                        Robot robot = figuren[player.get(ID).figur];
                    switch (robot.getDirection()) {
                        case "top":
                            if (direction.equals("clockwise")) {
                                robot.setDirection("right");
                            } else robot.setDirection("left");
                            break;
                        case "bottom":
                            if (direction.equals("clockwise")) {
                                robot.setDirection("left");
                            } else robot.setDirection("right");
                            break;
                        case "left":
                            if (direction.equals("clockwise")) {
                                robot.setDirection("top");
                            } else robot.setDirection("bottom");
                            break;
                        case "right":
                            if (direction.equals("clockwise")) {
                                robot.setDirection("bottom");
                            } else robot.setDirection("top");
                            break;
                    }
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                } else if (message.getMessageType().equals("ExchangeShop")){
                    this.handleExchangeSho(message);
                } else if (message.getMessageType().equals("RefillShop")){
                    this.handleRefillSho(message);
                } else if (message.getMessageType().equals("UpgradeBought")){
                    this.handleUpgradeBough(message);
                } else if (message.getMessageType().equals("CheckPointReached")){
                    handleCheckPointReached(message);
                }
                else {
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    /**
     * Dies ist die Main-Methode der KI.
     * @param args Kommandozeilenargumente
     * @throws IOException Sollte die Verbindung zum Server nicht korrekt laufen, wird diese Exception geworfen
     */
    public static void main(String[] args) throws IOException {
        KI ki = new KI();
        Thread thread = new Thread(ki);
        thread.start();
    }

}

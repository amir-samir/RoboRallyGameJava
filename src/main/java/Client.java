import Messages.*;
import Messages.Phase.SelectedCard;
import Messages.Phase.SetStartingPoint;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;


public class Client implements Runnable {

    private final Socket SOCKET;
    private final String GROUP = "Innige Irrwege";
    private final boolean isAi;
    private boolean connected;
    private int ID;
    private boolean ready = false;
    private int activePlayer;
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

    private final BufferedReader bufferedReader;
    private final PrintWriter bufferedWriter;

    public String protocol;
    public ObservableList<String> chatMessages;
    public ObservableList<String> usernamesGui;
    public HashMap<String, Integer> ids = new HashMap<String, Integer>();
    public HashMap<Integer, Player> player = new HashMap<Integer, Player>();
    public Robot[] figuren = new Robot[6];
    ChatView chatView = new ChatView();
    public static ChatView chatView1;
    public static SelectMapView selectMapView = new SelectMapView();
    public static MaybeMapsController maybeMapsController;
    public static AllInOneView allInOneView;
    private String selectedMap;
    public int figureForGui;
    public String CardOfGui = "SomeCard";


    /**
     * A Constructor that builds a connection between the client and the server and asks the server if
     * the username is not taken.
     *
     * @throws IOException            Throw this exception if the connection between server and client fails.
     */
    public Client() throws IOException {
        SOCKET = new Socket("localhost", 1524);
        bufferedReader = new BufferedReader(new InputStreamReader(SOCKET.getInputStream()));
        bufferedWriter = new PrintWriter(SOCKET.getOutputStream(), true);
        usernamesGui = FXCollections.observableArrayList();
        chatMessages = FXCollections.observableArrayList();
        isAi = false;
    }
    public static Client getClient(){
        return client;
    }
    public static Thread getThread(){
        return thread;
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
    public void singleMessage(int senderId, String message, String userName){
        int empfaenger = ids.get(userName);
        String[] keys = {"message", "to"};
        SendChat sendChat = new SendChat(message, empfaenger);
        sendChat.getMessageBody().setKeys(keys);
        bufferedWriter.println(Adopter.javabeanToJson(sendChat));

        SendChat sentMsg= new SendChat(message + " was sent to " + userName, senderId);
        sentMsg.getMessageBody().setKeys(keys);
        bufferedWriter.println(Adopter.javabeanToJson(sentMsg));
    }

    /**
     * A method that transfer the input to the game.Server.
     * @param input The input from user.
     */
    public void printMessage(String input) {
        SendChat message = new SendChat(input, -1);
        String[] key = {"message", "to"};
        message.getMessageBody().setKeys(key);
        String toSend = Adopter.javabeanToJson(message);
        bufferedWriter.println(toSend);
    }

    public synchronized  ObservableList getUsernames(){
        return usernamesGui;
    }

    public Integer getFigur(){
        return figureForGui;
    }

    public void configuration(String name, int figur){
        PlayerValues message = new PlayerValues(name, figur);
        String[] keys = {"name", "figure"};
        message.getMessageBody().setKeys(keys);
        bufferedWriter.println(Adopter.javabeanToJson(message));
        Platform.runLater(new Runnable(){

            @Override
            public void run() {
                getChatView().setImageFromFigur(figur);
            }
        });
    }

    public int getID(){
        return ID;
    }

    /**
     * A method that receive and returns information from the game.Server.
     * @throws IOException Throw this exception if the connection between server and client fails.
     */
    public String receiveFromServer() throws IOException {
        return bufferedReader.readLine();
    }

    public void closeConnection() throws IOException {
        SOCKET.close();
    }

    public boolean isConnected(){
        return connected;
    }

    public void setStartingPoint(int x, int y){
        SetStartingPoint setStartingPoint = new SetStartingPoint(x, y);
        setStartingPoint.getMessageBody().setKeys(new String[]{"x", "y"});
        bufferedWriter.println(Adopter.javabeanToJson(setStartingPoint));
        System.out.println(Adopter.javabeanToJson(setStartingPoint));
    }

    public void sendHelloServer(Message message){
        protocol = (String) message.getMessageBody().getContent()[0];
        HelloServer output = new HelloServer(GROUP, isAi, protocol);
        String[] keys = {"group", "isAI", "protocol"};
        output.getMessageBody().setKeys(keys);
        String S = Adopter.javabeanToJson(output);
        bufferedWriter.println(S);
    }

    public void sendCardToRegister(String card, int register){
        SelectedCard selectedCard = new SelectedCard(card, register);
        selectedCard.getMessageBody().setKeys(new String[]{"card", "register"});
        bufferedWriter.println(Adopter.javabeanToJson(selectedCard));
    }

    public void mapSelected(String map){
        MapSelected mapSelected = new MapSelected(map);
        String[] key = {"map"};
        mapSelected.getMessageBody().setKeys(key);
        bufferedWriter.println(Adopter.javabeanToJson(mapSelected));
    }

    public void playCard(String card){
        PlayCard playCard = new PlayCard(card);
        String[] key = {"card"};
        playCard.getMessageBody().setKeys(key);

        bufferedWriter.println(Adopter.javabeanToJson(playCard));
    }

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

    public int getFigurenID(int ID){
        for (int i = 0; i < figuren.length; i++){
            if (figuren[i].getGamerID() == ID){
                return i;
            }
        }
        return -1;
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
                default:
                    break;
            }
        }
        return handcards;
    }

    /**
     * This method is an overridden method which displays the input that is coming from the server in
     * the Chat view.
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
                    toSend = from + ": " + nachricht;
                } else if(message.getMessageType().equals("Alive")){
                    bufferedWriter.println("{\"messageType\": \"Alive\", \"messageBody\": {}}");
                    toSend = null;
                } else if(message.getMessageType().equals("PlayerAdded")){
                    int newFigure = (int)(double) message.getMessageBody().getContent()[2];
                    int clientID = (int)(double) message.getMessageBody().getContent()[0];
                    String username = (String) message.getMessageBody().getContent()[1];
                    if (ids.get(username) == null) {
                        ids.put(username, clientID);
                        figuren[newFigure] = new Robot(clientID);
                        usernamesGui.add(clientID + "," + username);
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
                            //Information an GUI weitergeben?
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

                    /*Platform.runLater(new Runnable(){

                        @Override
                        public void run() {
                            try {
                                getChatView().selectMap();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    });*/


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
                        Platform.runLater(() -> {
                            try {
                                getAllInOneView().resetRegisterCard();
                                getChatView().ChooseCard();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        });
                        //ProgrammierPhase? GUI?
                        toSend = "Die Programmierphase läuft aktuell.";
                    } else if (activePhase == 3){
                        //AktivierungsPhase? GUI?
                        toSend = "Die Aktivierungsphase läuft aktuell.";
                    } else toSend = null;
                } else if (message.getMessageType().equals("CurrentPlayer")){
                    int activePlayer = (int)(double)message.getMessageBody().getContent()[0];
                    if (this.ID == this.activePlayer){
                        //GUI?
                        toSend = "Du bist am Zug.";
                    } else {
                        //GUI?
                        String name = player.get(activePlayer).name;
                        toSend = name + " (" + activePlayer + ") " + "ist aktuell am Zug";
                    }
                } else if (message.getMessageType().equals("GameStarted")){
                    //HIER
                    Platform.runLater(() -> {
                        try {
                            getChatView().runAllInOne();
                            StageSaver.getStageSaver().getChatViewStage().close();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    });

                   /* Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                selectMapView.RunMap();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });*/
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
                                   getAllInOneView().setFigureOnMapNew(i, "right", figuren[i].getX(), figuren[i].getY());
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
                } else if (message.getMessageType().equals("TimerEnded")){
                    ArrayList<Double> list = (ArrayList<Double>) message.getMessageBody().getContent()[0];
                    String s = "Der Timer ist beendet." + "\n" + "Folgende Spieler sind nicht fertig geworden: ";
                    Platform.runLater(() -> {
                        StageSaver.getStageSaver().getChooseCardStage().close();
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
                }
                else {
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

    public String getSelectedMap(){
        return selectedMap;
    }


    public static void setChatViewModel(ChatView chatView){
        chatView1 = chatView;
    }

    public static void setSelectMapView(SelectMapView selectMapView1){
        selectMapView = selectMapView1;
    }

    public SelectMapView getSelectMapView(){
        return selectMapView;
    }

    public static void setMaybeMapsController(MaybeMapsController maybeMapsController1){
        maybeMapsController = maybeMapsController1;
    }

    public MaybeMapsController getMaybeMapsController(){
        return maybeMapsController;
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

    public ArrayList<Cards> getHandcards(){
        return figuren[player.get(ID).figur].getHandCards();
    }

}

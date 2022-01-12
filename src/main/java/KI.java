import Messages.*;
import Messages.Phase.SelectedCard;
import Messages.Phase.SetStartingPoint;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;


public class KI implements Runnable {

    private final Socket SOCKET;
    private final String GROUP = "Innige Irrwege";
    private final boolean isAi;
    private boolean connected;
    private int ID;
    private boolean ready = false;
    private int activePlayer;
    private static int countKI = 1;
    private ArrayList<BoardElement>[][] map;
    private static Thread thread;

    static {
        try {
            KI ki = new KI();
            thread = new Thread(ki);
            thread.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private final BufferedReader bufferedReader;
    private final PrintWriter bufferedWriter;

    public String protocol;
    public HashMap<String, Integer> ids = new HashMap<String, Integer>();
    public HashMap<Integer, Player> player = new HashMap<Integer, Player>();
    public Robot[] figuren = new Robot[6];
    private String selectedMap;
    public int figureForGui;


    /**
     * A Constructor that builds a connection between the client and the server and asks the server if
     * the username is not taken.
     *
     * @throws IOException            Throw this exception if the connection between server and client fails.
     */
    public KI() throws IOException {
        SOCKET = new Socket("localhost", 1524);
        bufferedReader = new BufferedReader(new InputStreamReader(SOCKET.getInputStream()));
        bufferedWriter = new PrintWriter(SOCKET.getOutputStream(), true);
        isAi = true;
    }

    public void setReady(){
        ready = true;
        if (player.get(ID) != null) {
            player.get(ID).ready = true;
        }
        SetStatus setStatus = new SetStatus(true);
        bufferedWriter.println(Adopter.javabeanToJson(setStatus));
    }

    public Integer getFigur(){
        return figureForGui;
    }

    public void configuration(){
        String name;
        int figur = -1;
        if (countKI == 1){
            name = "BummBot";
        } else if (countKI == 2){
            name = "Robot annihilator 3000";
        } else if (countKI == 3){
            name = "Viiiiktoooor";
        } else if (countKI == 4){
            name = "ich bin zu unkreativ";
        } else if (countKI == 5){
            name = "Ich bin ein Roboter";
        } else {
            name = "CrashBot";
        }
        countKI += 1;

        for (int i = 0; i < figuren.length; i++){
            if (figuren[i] == null){
                figur = i;
                break;
            }
        }

        PlayerValues message = new PlayerValues(name, figur);
        String[] keys = {"name", "figure"};
        message.getMessageBody().setKeys(keys);
        bufferedWriter.println(Adopter.javabeanToJson(message));
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

    public void setStartingPoint(){
        ArrayList<Integer[]> list = new ArrayList();
        if (selectedMap.equals("DeathTrap")){
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

        for (int i = 0; i < list.size(); i++){
            boolean free = true;
            for (Robot robot: figuren){
                if (robot != null) {
                    if (robot.getX() == list.get(i)[0] && robot.getY() == list.get(i)[1]) {
                        free = false;
                    }
                }
            }
            if (free){
                SetStartingPoint setStartingPoint = new SetStartingPoint(list.get(i)[0], list.get(i)[1]);
                setStartingPoint.getMessageBody().setKeys(new String[]{"x", "y"});
                bufferedWriter.println(Adopter.javabeanToJson(setStartingPoint));
                return;
            }
        }
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
                }
                if (selectedMap.equals("DeathTrap")) {
                    robot.setDirection("left");
                } else robot.setDirection("right");
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

    public void playCards(ArrayList<String> list){
        sendCardToRegister(list.get(0), 0);
        sendCardToRegister(list.get(1), 1);
        sendCardToRegister(list.get(2), 2);
        sendCardToRegister(list.get(3), 3);
        sendCardToRegister(list.get(4), 4);
    }

    /**
     * This method is an overridden method which displays the input that is coming from the server in
     * the Chat view.
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
                    if (ids.get(username) == null) {
                        ids.put(username, clientID);
                        figuren[newFigure] = new Robot(clientID);
                        Player newPlayer = new Player(clientID, username, newFigure);
                        player.put(clientID, newPlayer);
                    }
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
                    mapSelected("DizzyHighway");
                } else if (message.getMessageType().equals("MapSelected")){
                    String map = (String) message.getMessageBody().getContent()[0];
                    selectedMap = map;
                } else if (message.getMessageType().equals("ActivePhase")){
                    int activePhase = (int) (double) message.getMessageBody().getContent()[0];
                } else if (message.getMessageType().equals("CurrentPlayer")){
                    int activePlayer = (int)(double)message.getMessageBody().getContent()[0];
                    if (this.ID == activePlayer){
                        setStartingPoint();
                    } else {
                        String name = player.get(activePlayer).name;
                    }
                } else if (message.getMessageType().equals("GameStarted")){

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
                }
                else {
                }
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public ArrayList<Cards> getHandcards(){
        return figuren[player.get(ID).figur].getHandCards();
    }

    public static void main(String[] args) {
        try {
            KI ki = new KI();
        } catch (Exception e){

        }
    }

}

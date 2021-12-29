import Messages.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.HashMap;


public class Client implements Runnable {

    private final Socket SOCKET;
    private final String GROUP = "Innige Irrwege";
    private boolean isAi;
    private boolean connected;
    private int ID;
    private boolean ready = false;

    private String userName;
    private BufferedReader bufferedReader;
    private PrintWriter bufferedWriter;
    public String protocol;
    public ObservableList<String> chatMessages;
    public ObservableList<String> usernamesGui;
    public HashMap<String, Integer> ids = new HashMap<String, Integer>();
    public HashMap<Integer, Player> player = new HashMap<Integer, Player>();
    public int[] figuren = new int[6];

    /**
     * A Constructor that builds a connection between the client and the server and asks the server if
     * the username is not taken.
     *
     * @throws IOException            Throw this exception if the connection between server and client fails.
     */
    public Client() throws IOException {
        SOCKET = new Socket("localhost", 1523);
        bufferedReader = new BufferedReader(new InputStreamReader(SOCKET.getInputStream()));
        bufferedWriter = new PrintWriter(SOCKET.getOutputStream(), true);
        usernamesGui = FXCollections.observableArrayList();
        //this.userName = userName;
        chatMessages = FXCollections.observableArrayList();
        //bufferedWriter.println(userName);
        isAi = false;
        //listenForMessages();
    }

   /* public void listenForMessages(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                String message;
                while (SOCKET.isConnected()){
                    try {
                        message = bufferedReader.readLine();
                        System.out.println(message);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        });
    } */

    public void setCleint(Client client){

    }

    public void setReady(){
        if(ready){
            ready = false;
            player.get(ID).ready = false;
            SetStatus setStatus = new SetStatus(false);
            bufferedWriter.println(Adopter.javabeanToJson(setStatus));
        } else if (!ready){
            ready = true;
            player.get(ID).ready = true;
            SetStatus setStatus = new SetStatus(true);
            bufferedWriter.println(Adopter.javabeanToJson(setStatus));
        }
    }

    public void singleMessage(int senderId, String message, String userName){
        int empfaenger = ids.get(userName);
        //int senderId = ids.get(senderName);
        String[] keys = {"message", "to"};
        SendChat sendChat = new SendChat(message, empfaenger);
        sendChat.getMessageBody().setKeys(keys);
        bufferedWriter.println(Adopter.javabeanToJson(sendChat));

        SendChat sentMsg= new SendChat(message + " was sent to " + userName, senderId);
        sentMsg.getMessageBody().setKeys(keys);
        bufferedWriter.println(Adopter.javabeanToJson(sentMsg));
    }

    /**
     * A method that transfer the input to the Server.
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

    public void configuration(String name, int figur){
        PlayerValues message = new PlayerValues(name, figur);
        String[] keys = {"name", "figure"};
        message.getMessageBody().setKeys(keys);
        bufferedWriter.println(Adopter.javabeanToJson(message));
    }

    public int getID(){
        return ID;
    }
    public String getUserName(){
        return userName;
    }
    /**
     * A method that receive and returns information from the Server.
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

    public void sendHelloServer(Message message){
        protocol = (String) message.getMessageBody().getContent()[0];
        HelloServer output = new HelloServer(GROUP, isAi, protocol);
        String[] keys = {"group", "isAI", "protocol"};
        output.getMessageBody().setKeys(keys);
        String S = Adopter.javabeanToJson(output);
        bufferedWriter.println(S);
    }

    public void mapSelected(String map){
        MapSelected mapSelected = new MapSelected(map);
        String[] key = {"map"};
        mapSelected.getMessageBody().setKeys(key);
        bufferedWriter.println(mapSelected);
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
                String inputFromServer = bufferedReader.readLine(); // Data read from the Server.
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
                    ids.put(username, clientID);
                    figuren[newFigure] = clientID;
                    usernamesGui.add(clientID + "," + username);
                    Player newPlayer = new Player(clientID, username, newFigure);
                    player.put(clientID, newPlayer);
                    toSend = username + " hat sich verbunden. Er/Sie spielt mit Figur: " + newFigure;
                    setReady();
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
                    //Methode von AMIR aufrufen --> GUI: Select Map
                    toSend = "Bitte wähle die Map aus.";
                    mapSelected("DizzyHighway");
                } else if (message.getMessageType().equals("MapSelected")){
                    String map = (String) message.getMessageBody().getContent()[0];
                    toSend = "Folgende Map wurde ausgewählt: " + map;
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
            }
        }
    }
}
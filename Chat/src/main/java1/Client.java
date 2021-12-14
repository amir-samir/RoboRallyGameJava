import Messages.*;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;


public class Client implements Runnable {

    private final Socket SOCKET;
    private final String GROUP = "Innige Irrwege";
    private boolean isAi;
    private boolean connected;
    private int ID;

    private String userName;
    private BufferedReader bufferedReader;
    private PrintWriter bufferedWriter;
    public ObservableList<String> chatMessages;
    public String protocol;
    public  ObservableList<String> usernamesGui;
    public HashMap<String, Integer> ids = new HashMap<String, Integer>();
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

    public void singleMessage(String message, String userName){

        //SendChat sendChat = new SendChat(String message, int to);
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
                    int from = (int)(double)message.getMessageBody().getContent()[0];
                    boolean isPrivate = (boolean) message.getMessageBody().getContent()[1];
                    String nachricht = (String) message.getMessageBody().getContent()[2];
                    toSend = from + ": " + nachricht;
                } else if(message.getMessageType().equals("Alive")){
                    bufferedWriter.println("{\"messageType\": \"Alive\", \"messageBody\": {}}");
                    toSend = null;
                } else if(message.getMessageType().equals("PlayerAdded")) {
                    int newFigure = (int)(double) message.getMessageBody().getContent()[0];
                    int clientID = (int)(double) message.getMessageBody().getContent()[1];
                    String username = (String) message.getMessageBody().getContent()[2];
                    ids.put(username, clientID);
                    figuren[newFigure] = clientID;
                    usernamesGui.add(username);
                    toSend = username + " hat sich verbunden. Er spielt mit Figur: " + newFigure;
                    // System.out.println(toSend) ;
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
            }
        }
    }
}
import Messages.Adopter;
import Messages.HelloServer;
import Messages.Message;
import Messages.SendChat;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Client implements Runnable {

    private final Socket SOCKET;
    private final String GROUP = "Innige Irrwege";
    private boolean isAi;
    private int ID;

    private String userName;
    private BufferedReader bufferedReader;
    private PrintWriter bufferedWriter;
    public ObservableList<String> chatMessages;
    public String protocol;

    /**
     * A Constructor that builds a connection between the client and the server and asks the server if
     * the username is not taken.
     *
     * @param userName                The input username from the user.
     * @throws IOException            Throw this exception if the connection between server and client fails.
     *
     */
    public Client(String userName) throws IOException {
        SOCKET = new Socket("localhost", 1523);
        bufferedReader = new BufferedReader(new InputStreamReader(SOCKET.getInputStream()));
        bufferedWriter = new PrintWriter(SOCKET.getOutputStream(), true);
        this.userName = userName;
        chatMessages = FXCollections.observableArrayList();
        bufferedWriter.println(userName);
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
                    protocol = (String) message.getMessageBody().getContent()[0];
                    toSend = "Das Protokoll wurde eingelesen. Verwendete Version: " + protocol;
                    HelloServer output = new HelloServer(GROUP, isAi, protocol);
                    String[] keys = {"group", "isAI", "protocol"};
                    output.getMessageBody().setKeys(keys);
                    String S = Adopter.javabeanToJson(output);
                    bufferedWriter.println(S);
                } else if (message.getMessageType().equals("Error1")) {
                    toSend = (String) message.getMessageBody().getContent()[0];
                } else if (message.getMessageType().equals("Welcome")){
                    double wert = (double) message.getMessageBody().getContent()[0];
                    ID = (int) wert;
                    toSend = "Willkommen im Chat. Deine ID wurde erfolgreich generiert.";
                } else if(message.getMessageType().equals("ReceivedChat")){
                    int from = (int)(double)message.getMessageBody().getContent()[0];
                    boolean isPrivate = (boolean) message.getMessageBody().getContent()[1];
                    String nachricht = (String) message.getMessageBody().getContent()[2];
                    toSend = from + ": " + nachricht;
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
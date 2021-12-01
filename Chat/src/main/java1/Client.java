import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;



public class Client implements Runnable {

    private Socket socket;
    private String userName;
    private BufferedReader bufferedReader;
    private PrintWriter bufferedWriter;
    public Server server;
    public ObservableList<String> chatMessages;
    private ClientHandler clientHandler ;
    private ServerSocket serverSocket;
    Server server1 = new Server(serverSocket);

    /**
     * A Constructor that builds a connection between the client and the server and asks the server if
     * the username is not taken.
     *
     * @param userName                The input username from the user.
     * @throws IOException            Throw this exception if the connection between server and client fails.
     *
     */
    public Client(String userName) throws IOException {
        socket = new Socket("localhost", 1234);
        bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        bufferedWriter = new PrintWriter(socket.getOutputStream(), true);

        //bufferedWriter.println(userName);
        //String checkUsername = bufferedReader.readLine();


        if (!server1.checkName(userName)) {
            clientHandler.newUsername();

        } else {
            this.userName = userName;
            chatMessages = FXCollections.observableArrayList();
            bufferedWriter.println(userName);

        }

    }


    /**
     * Get the username.
     */
    public String getUserName() {

        return userName;

    }

    /**
     * Sets the username.
     * @param name The name from user.
     */
    public void setUserName(String name) {

        this.userName = name;

    }

    /**
     * A method that transfer the input to the Server.
     * @param input The input from user.
     */
    public void printMessage(String input) {

        bufferedWriter.println(input);

    }

    /**
     * A method that receive and returns information from the Server.
     * @throws IOException Throw this exception if the connection between server and client fails.
     */
    public String receiveFromServer() throws IOException {

        return bufferedReader.readLine();

    }

    public void closeConnection() throws IOException {

        socket.close();

    }

    /**
     * This method is an overridden method which displays the input that is coming from the server in
     * the Chat view.
     */
    @Override
    public void run() {

        while (true) {
            try {
                String inputFromServer = bufferedReader.readLine(); // Data read from the Server.

                if (inputFromServer == null) {
                    break;
                }

                Platform.runLater(() -> {
                    chatMessages.add(inputFromServer); // Adding the input to Chat window.
                });

            } catch (IOException e) {

                e.printStackTrace();
                break;

            }
        }
    }
}
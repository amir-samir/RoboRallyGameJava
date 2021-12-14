import Messages.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;

/**
 * Diese Klasse stellt den Server dar, auf welchem der Chat und das Spiel ausgeführt werden.
 * @author Luca, Dairen
 *
 */

public class Server {

    public static int laufendeID = 2000;

    public HashMap<Integer, ClientHandler> users = new HashMap<Integer, ClientHandler>();
    public HashMap<String, Integer> ids = new HashMap<String, Integer>();
    public int[] figuren = new int[6];

    private ServerSocket serverSocket;
    public String protocol;


    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Dies ist die Main-Methode. Sie instanziiert einen neuen Server und startet diesen.
     * @param args Kommandozeilenparameter
     */

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(1523);
            Server server = new Server(serverSocket);
            server.protocol = "Version 0.2";
            server.startServer();
        } catch (Exception e){
            e.printStackTrace();
            System.err.println("Fehler. Der Server konnte nicht gestartet werden.");
        }
    }

    /**
     * Diese Methode sorgt dafür, dass private Nachrichten verschickt werden können.
     * param id Username des Empfängers der Nachricht
     * @param message Die Nachricht, die versendet werden soll
     */
    public void singleMessage(int from, String message, int to) {
        ReceivedChat toSend = new ReceivedChat(message, from, true);
        String[] keys = {"message", "from", "to"};
        toSend.getMessageBody().setKeys(keys);
        String nachricht = Adopter.javabeanToJson(toSend);
        ClientHandler c = users.get(to);
        c.owriter.println(nachricht);
    }

    /**
     * Diese Methode verschickt eine Nachricht an alle aktiven Mitglieder des Chat-Raums.
     * @param message Die Nachricht, die versendet werden soll
     */
    public void messageForAllUsers(String message, int from) {
        ReceivedChat toSend = new ReceivedChat(message, from, false);
        String[] keys = {"message", "from", "isPrivate"};
        toSend.getMessageBody().setKeys(keys);
        String nachricht = Adopter.javabeanToJson(toSend);
        for(ClientHandler clientHandler: users.values()){
            clientHandler.owriter.println(nachricht);
        }
    }

    /**
     * Diese Methode fügt neue Mitglieder des Chat-Raums in die HashMap aller Mitglieder hinzu.
     * @param clientHandler Der ClientHandler, der in die HashMap eingefügt werden soll.
     * @return Gibt zurück, ob der ClientHandler in die HashMap eingefügt werden konnte.
     */
    public boolean addClient(ClientHandler clientHandler) {
        users.put(clientHandler.ID, clientHandler);
        return true;
    }

    public void addUsername(ClientHandler clientHandler){
        ids.put(clientHandler.username, clientHandler.ID);
    }

    public boolean checkFigure(int figur, ClientHandler clientHandler){
        if(figuren[figur] == 0){
            figuren[figur] = clientHandler.ID;
            System.out.println("Figur wurde gewählt");
            return true;
        } else return false;
    }

    public void playerAdded(ClientHandler clientHandler){
        PlayerAdded playerAdded = new PlayerAdded(clientHandler.ID, clientHandler.username, clientHandler.figure);
        String[] keys = {"clientID", "name", "figure"};
        playerAdded.getMessageBody().setKeys(keys);
        for (ClientHandler clientHandler1 : users.values()){
            clientHandler1.owriter.println(Adopter.javabeanToJson(playerAdded));

        }
    }

    /**
     * Diese Methode prüft, ob ein Username schon in der Hashmap vorhanden ist.
     * @param username Der Username, der überprüft werden soll
     * @return Gibt zurück, ob der Username schon exisitiert
     */
    public boolean checkName(String username) {
        return !users.containsKey(username);
    }

    /**
     * Diese Methode startet den Server und baut die Verbindungen zu neuen Spielern auf.
     * Hierfür werden neue Threads instanziiert, um mehrere Verbindungen gleichzeitig aufrechtzuerhalten.
     */
    public void startServer() {
        try {
            while (!serverSocket.isClosed()) {
                Socket socket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(socket, this);
                Thread thread = new Thread(clientHandler);
                thread.start();
                sendHelloClient(clientHandler);
                System.out.println("A new client has connected");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendHelloClient(ClientHandler clientHandler){
        HelloClient message = new HelloClient(this.protocol);
        String[] key = {"protocol"};
        message.getMessageBody().setKeys(key);
        String toSend = Adopter.javabeanToJson(message);
        try {
            clientHandler.owriter.println(toSend);
        } catch (Exception e){

        }
    }

    public int generateID(){
        int ID = this.laufendeID;
        laufendeID++;
        return ID;
    }

    public void print(String s){
        System.out.println(s);
    }
    public void printPlayerValues(String name, int figure){
        System.out.println(name + figure);
    }
}

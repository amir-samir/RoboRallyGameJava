import game.Messages.*;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

/**
 * Diese Klasse stellt den Server dar, auf welchem der Chat und das Spiel ausgeführt werden.
 * @author Luca, Dairen
 *
 */

public class Server {

    public static int laufendeID = 2000;

    public HashMap<Integer, ClientHandler> users = new HashMap<Integer, ClientHandler>();
    public static HashMap<String, Integer> ids = new HashMap<String, Integer>();
    public int[] figuren = new int[6];
    public String[] availableMaps = {"DizzyHighway", "ExtraCrispy", "LostBearings", "Death Trap"};
    String activeMap = null;
    //Game game;

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
            server.protocol = "Version 1.0";
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
        } else {
            return false;
        }
    }

    public void playerAdded(ClientHandler clientHandler) {
        PlayerAdded playerAdded = new PlayerAdded(clientHandler.ID, clientHandler.username, clientHandler.figure);
        String[] keys = {"clientID", "name", "figure"};
        playerAdded.getMessageBody().setKeys(keys);
        //Versendung des neuen Spielers an alle anderen
        for (ClientHandler clientHandler1 : users.values()) {
            clientHandler1.owriter.println(Adopter.javabeanToJson(playerAdded));
        }
        //Versendung aller anderen Spieler an den neuen
        for (ClientHandler clientHandler1 : users.values()) {
            if (clientHandler.ID != clientHandler1.ID) {
                PlayerAdded pA = new PlayerAdded(clientHandler1.ID, clientHandler1.username, clientHandler1.figure);
                String[] key = {"clientID", "name", "figure"};
                pA.getMessageBody().setKeys(key);
                clientHandler.owriter.println(Adopter.javabeanToJson(pA));
                if (clientHandler1.isReady){
                    PlayerStatus playerStatus = new PlayerStatus(clientHandler1.ID, true);
                    String[] k = {"clientID", "ready"};
                    playerStatus.getMessageBody().setKeys(k);
                    clientHandler.owriter.println(Adopter.javabeanToJson(playerStatus));
                }
            }
        }
    }

    public void handleReady(ClientHandler cH){
        int countReady = 0;
        for (ClientHandler clientHandler: users.values()){
            if(clientHandler.isReady){
                countReady += 1;
            }
        }

        if (countReady == 1){
            if (cH.isReady){
                SelectMap selectMap = new SelectMap(availableMaps);
                String[] keys = {"availableMaps"};
                selectMap.getMessageBody().setKeys(keys);
                cH.owriter.println(Adopter.javabeanToJson(selectMap));
                System.out.println(Adopter.javabeanToJson(selectMap));
            }
        }

        PlayerStatus playerStatus = new PlayerStatus(cH.ID, cH.isReady);
        String[] keys = {"clientID", "ready"};
        playerStatus.getMessageBody().setKeys(keys);

        for (ClientHandler clientHandler: users.values()){
            clientHandler.owriter.println(Adopter.javabeanToJson(playerStatus));
        }
    }

    public void handleMapSelected (String map){
        MapSelected mapToSend = new MapSelected(map);
        String[] key = {"map"};
        mapToSend.getMessageBody().setKeys(key);
        activeMap = map;
        for (ClientHandler clientHandler: users.values()){
            clientHandler.owriter.println(Adopter.javabeanToJson(mapToSend));
        }

        if(readyToStart()){
            createGame();
            createMap();
        }
    }

    public boolean readyToStart(){
        int count = 0;
        for (ClientHandler clientHandler: users.values()){
            count += 1;
            if (!clientHandler.isReady) return false;
        }
        if(count > 1){
            return true;
        } else return false;
    }

    public void createMap(){
        //Map erstellen
    }

    public void createGame(){

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

    public void handlePlayCard(String card, int ID){
        //game.cardPlayed(card);

        CardPlayed cardPlayed = new CardPlayed(ID, card);
        String[] keys = {"clientID", "card"};
        cardPlayed.getMessageBody().setKeys(keys);
        for(ClientHandler clientHandler: users.values()){
            clientHandler.owriter.println(Adopter.javabeanToJson(cardPlayed));
        }

    }

    public int generateID(){
        int ID = Server.laufendeID;
        laufendeID++;
        return ID;
    }
}
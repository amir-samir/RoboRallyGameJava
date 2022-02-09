import Messages.*;
import Messages.Actions.GameFinished;
import Messages.Phase.StartingPointTaken;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Diese Klasse stellt den game.Server dar, auf welchem der Chat und das Spiel ausgeführt werden.
 * @author Luca, Dairen
 *
 */

public class Server {

    public static int laufendeID = 2000;
    public static boolean mapSelected = false;
    public static ClientHandler clientWhoSelectedMap;
    public List<ClientHandler> verbindungen = new ArrayList<ClientHandler>();
    public static List<Integer> choosenBots = new ArrayList<Integer>();
    public HashMap<Integer, ClientHandler> users = new HashMap<Integer, ClientHandler>();
    //public static HashMap<String, Integer> ids = new HashMap<String, Integer>();
    public Robot[] figuren = new Robot[6];
    RalleyLogger ralleyLogger = new RalleyLogger();
    public String[] availableMaps = {"DizzyHighway", "ExtraCrispy", "LostBearings", "DeathTrap"};
    String activeMap = null;
    Game game;

    private ServerSocket serverSocket;
    public String protocol;



    public Server(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    /**
     * Dies ist die Main-Methode. Sie instanziiert einen neuen game.Server und startet diesen.
     * @param args Kommandozeilenparameter
     */

    public static void main(String[] args) {
        try {
            clearTxt();
            ServerSocket serverSocket = new ServerSocket(1237);
            Server server = new Server(serverSocket);
            server.protocol = "Version 1.0";
            server.startServer();
        } catch (Exception e){
            e.printStackTrace();
            System.err.println("Fehler. Der game.Server konnte nicht gestartet werden.");
        }
    }

    /**
     * Diese Methode sorgt dafür, dass die robots.txt wieder leer ist.
     */
    public static void clearTxt() throws FileNotFoundException {
        PrintWriter writer = new PrintWriter("robots.txt");
        writer.print("");
        writer.close();
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
        c.writer.println(nachricht);
        ralleyLogger.getLogger().info(nachricht);
    }

    public static synchronized void setChoosenBots(int bot) {
        choosenBots.add(bot);
    }
    public static synchronized List<Integer> getChoosenBots() {
        return choosenBots;
    }
    public void sendMessageForSingleClient(Message m, ClientHandler clientHandler){
        clientHandler.writer.println(Adopter.javabeanToJson(m));
        ralleyLogger.getLogger().info(Adopter.javabeanToJson(m));
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
            clientHandler.writer.println(nachricht);
        }
        ralleyLogger.getLogger().info(nachricht);
    }

    public void sendMessageForAllUsers(Message m){
        for (ClientHandler clientHandler: users.values()){
            clientHandler.writer.println(Adopter.javabeanToJson(m));
        }
        ralleyLogger.getLogger().info(Adopter.javabeanToJson(m));
    }

    /**
     * Diese Methode fügt neue Mitglieder des Chat-Raums in die HashMap aller Mitglieder hinzu.
     * @param clientHandler Der game.ClientHandler, der in die HashMap eingefügt werden soll.
     * @return Gibt zurück, ob der game.ClientHandler in die HashMap eingefügt werden konnte.
     */
    public boolean addClient(ClientHandler clientHandler) {
        verbindungen.add(clientHandler);
        users.put(clientHandler.ID, clientHandler);
        return true;
    }

    public void addUsername(ClientHandler clientHandler){
        //ids.put(clientHandler.username, clientHandler.ID);
    }

    public boolean checkFigure(int figur, ClientHandler clientHandler){
        if(figuren[figur] == null){
            figuren[figur] = new Robot(clientHandler.ID);
            return true;
        } else {
            return false;
        }
    }

    public void exitPlayer(ClientHandler clientHandler){

    }

    public void handleChooseRegister(ClientHandler clientHandler){
        game.handleChooseRegister(clientHandler);
    }

    public void playerAdded(ClientHandler clientHandler) {
        PlayerAdded playerAdded = new PlayerAdded(clientHandler.ID, clientHandler.username, clientHandler.figure);
        String[] keys = {"clientID", "name", "figure"};
        playerAdded.getMessageBody().setKeys(keys);
        //Versendung des neuen Spielers an alle anderen
        for (ClientHandler clientHandler1 : users.values()) {
            clientHandler1.writer.println(Adopter.javabeanToJson(playerAdded));
        }
        ralleyLogger.getLogger().info(Adopter.javabeanToJson(playerAdded));
        //Versendung aller anderen Spieler an den neuen
        for (ClientHandler clientHandler1 : users.values()) {
            if (clientHandler.ID != clientHandler1.ID) {
                PlayerAdded pA = new PlayerAdded(clientHandler1.ID, clientHandler1.username, clientHandler1.figure);
                String[] key = {"clientID", "name", "figure"};
                pA.getMessageBody().setKeys(key);
                clientHandler.writer.println(Adopter.javabeanToJson(pA));
                if (clientHandler1.isReady){
                    PlayerStatus playerStatus = new PlayerStatus(clientHandler1.ID, true);
                    String[] k = {"clientID", "ready"};
                    playerStatus.getMessageBody().setKeys(k);
                    clientHandler.writer.println(Adopter.javabeanToJson(playerStatus));
                }
            }
        }
    }

    public void handleReady(ClientHandler cH){
       /* int countReady = 0;
        for (ClientHandler clientHandler: users.values()){
            if(clientHandler.isReady){
                countReady += 1;
            }
        }
        */
        /*if (countReady == 1){
            if (cH.isReady){
                SelectMap selectMap = new SelectMap(availableMaps);
                String[] keys = {"availableMaps"};
                selectMap.getMessageBody().setKeys(keys);
                cH.writer.println(Adopter.javabeanToJson(selectMap));
                System.out.println(Adopter.javabeanToJson(selectMap));
            }
        }
         */
        if (cH.isReady) {
            if (!this.mapSelected && !cH.isAi) {
                clientWhoSelectedMap = cH;
                SelectMap selectMap = new SelectMap(availableMaps);
                String[] keys = {"availableMaps"};
                selectMap.getMessageBody().setKeys(keys);
                cH.writer.println(Adopter.javabeanToJson(selectMap));
                ralleyLogger.getLogger().info(Adopter.javabeanToJson(selectMap));
            }
        } else {
            if (clientWhoSelectedMap.equals(cH)){
                mapSelected = false;
                clientWhoSelectedMap = null;
            }
        }

        PlayerStatus playerStatus = new PlayerStatus(cH.ID, cH.isReady);
        String[] keys = {"clientID", "ready"};
        playerStatus.getMessageBody().setKeys(keys);

        for (ClientHandler clientHandler: users.values()){
            clientHandler.writer.println(Adopter.javabeanToJson(playerStatus));
        }
        ralleyLogger.getLogger().info(Adopter.javabeanToJson(playerStatus));
        if(readyToStart()){
            createGame();
            generateMap();
        }
    }

    public void handleMapSelected (String map){
        MapSelected mapToSend = new MapSelected(map);
        String[] key = {"map"};
        mapToSend.getMessageBody().setKeys(key);
        activeMap = map;
        mapSelected = true;
        for (ClientHandler clientHandler: users.values()){
            clientHandler.writer.println(Adopter.javabeanToJson(mapToSend));
        }
        ralleyLogger.getLogger().info(Adopter.javabeanToJson(mapToSend));
        if (readyToStart()){
            createGame();
            generateMap();
        }
    }

    public void generateMap(){
        Board board;
        String s;

        switch (activeMap){
            case "DizzyHighway":
                board = new DizzyHighway();
                s = board.json;
                break;
            case "DeathTrap":
                board = new DeathTrap();
                s = board.json;
                break;
            case "ExtraCrispy":
                board = new ExtraCrispy();
                s = board.json;
                break;
            case "LostBearings":
                board = new LostBearings();
                s = board.json;
                break;
            default:
                s = null;
        }

        for (ClientHandler clientHandler: users.values()){
            clientHandler.writer.println(s);
        }

        ralleyLogger.getLogger().info(s);
    }

    public void handleSelectedCard(String card, int register, ClientHandler clientHandler){
        game.handleSelectedCard(card, register, clientHandler);
    }

    public void handleRebootDirection(String string, ClientHandler clientHandler){
        game.handleRebootDirection(string, clientHandler);
    }

    public boolean readyToStart(){
        int count = 0;
        for (ClientHandler clientHandler: users.values()){
            count += 1;
            if (!clientHandler.isReady) {
                return false;
            }
        }
        if(count > 1 && mapSelected){
            return true;
        } else {
            return false;
        }
    }

    public void handleSelectDamage(ClientHandler clientHandler, ArrayList<String> card){
        String karte = card.get(0);
        game.chooseDamageCard(clientHandler, karte);
    }

    public void handleBuyUpgrade(boolean isBuying, String card, ClientHandler clientHandler){
        game.handleBuyUpgrade(isBuying, card, clientHandler);
    }

    public void createGame(){
        game = new Game(this, users, verbindungen, activeMap, figuren);
    }

    public void setStartingPoint(int x, int y, ClientHandler clientHandler){
        game.setStartingPoint(x, y, clientHandler);
    }

    public void validStartingPoint(int x, int y, ClientHandler clientHandler){
        StartingPointTaken startingPointTaken = new StartingPointTaken(x, y, clientHandler.ID);
        startingPointTaken.getMessageBody().setKeys(new String[]{"x", "y", "clientID"});
        sendMessageForAllUsers(startingPointTaken);
    }

    /**
     * Diese Methode startet den game.Server und baut die Verbindungen zu neuen Spielern auf.
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
                ralleyLogger.getLogger().info("Server wurde gestartet.");
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
        ralleyLogger.getLogger().info(toSend);
        try {
            clientHandler.writer.println(toSend);
        } catch (Exception e){

        }
    }

    public void handlePlayCard(String card, int ID){
        CardPlayed cardPlayed = new CardPlayed(ID, card);
        String[] keys = {"clientID", "card"};
        cardPlayed.getMessageBody().setKeys(keys);
        for(ClientHandler clientHandler: users.values()){
            clientHandler.writer.println(Adopter.javabeanToJson(cardPlayed));
        }
        ralleyLogger.getLogger().info(Adopter.javabeanToJson(cardPlayed));
    }

    public void handleReturnCards(ArrayList<String> cards, ClientHandler clientHandler){
        game.handleReturnCards(cards, clientHandler);
    }

    public int generateID(){
        int ID = Server.laufendeID;
        laufendeID++;
        return ID;
    }

    public void endGame(Robot robot){
        GameFinished gameFinished = new GameFinished(robot.getGamerID());
        gameFinished.getMessageBody().setKeys(new String[]{"clientID"});
        sendMessageForAllUsers(gameFinished);
    }
}

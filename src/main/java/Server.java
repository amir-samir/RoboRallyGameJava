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
 * Diese Klasse stellt Server dar, auf welchem der Chat und das Spiel ausgeführt werden.
 *
 * @author Amir Azim
 * @author Dairen Gonschior
 * @author Luca Weyhofen
 *
 * @version 2.1
 */

public class Server {

    private ServerSocket serverSocket;
    public String protocol;
    private static int laufendeID = 2000;
    private int mindestAnzahlAnSpielers;
    private static List<Integer> choosenBots = new ArrayList<>();
    private List<ClientHandler> verbindungen = new ArrayList<>();
    private HashMap<Integer, ClientHandler> users = new HashMap<Integer, ClientHandler>();
    RalleyLogger ralleyLogger = new RalleyLogger();

    private Robot[] figuren = new Robot[6];
    private String[] availableMaps = {"DizzyHighway", "ExtraCrispy", "LostBearings", "DeathTrap", "Twister"};
    private static boolean mapSelected = false;
    private static ClientHandler clientWhoSelectedMap;
    private String activeMap = null;
    private Game game;

    /**
     * Dies ist der Konstruktor
     * @param serverSocket Der ServerSocket
     * @param anzahlSpieler Die Anzahl der Spieler, die mindestens aktiv sein sollen
     */
    public Server(ServerSocket serverSocket, int anzahlSpieler) {
        this.serverSocket = serverSocket;
    }

    /**
     * Dies ist die Main-Methode. Sie instanziiert einen neuen Server und startet diesen.
     * @param args Kommandozeilenparameter
     */
    public static void main(String[] args) {
        try {
            clearTxt();
            int anzahlSpieler = (int) Integer.parseInt(args[0]);
            ServerSocket serverSocket = new ServerSocket(1237);
            Server server = new Server(serverSocket, anzahlSpieler);
            server.protocol = "Version 2.1";
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
     * @param from ID des Versenders der Nachricht
     * @param message Die Nachricht, die versendet werden soll
     * @param to ID des Empfängers
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

    /**
     * Eine Nachricht kann an einen bestimmten Client geschickt werden.
     * @param m Die Nachricht, die verschickt werden soll
     * @param clientHandler Der Empfänger der Nachricht
     */
    public void sendMessageForSingleClient(Message m, ClientHandler clientHandler){
        clientHandler.writer.println(Adopter.javabeanToJson(m));
        ralleyLogger.getLogger().info(Adopter.javabeanToJson(m));
    }

    /**
     * Diese Methode verschickt eine Chat-Nachricht an alle aktiven Spieler.
     * @param message Die Nachricht, die versendet werden soll
     * @param from ID des Versenders der Nachricht
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

    /**
     * Diese Methode verschickt eine Nachricht an alle aktiven Spieler.
     * @param m Nachricht die verschickt werden soll
     */
    public void sendMessageForAllUsers(Message m){
        for (ClientHandler clientHandler: users.values()){
            clientHandler.writer.println(Adopter.javabeanToJson(m));
        }
        ralleyLogger.getLogger().info(Adopter.javabeanToJson(m));
    }

    /**
     * Diese Methode fügt neue Mitglieder des Chat-Raums in die HashMap aller Mitglieder hinzu.
     * @param clientHandler Der ClientHandler, der in die HashMap eingefügt werden soll.
     * @return Gibt zurück, ob der ClientHandler in die HashMap eingefügt werden konnte.
     */
    public boolean addClient(ClientHandler clientHandler) {
        verbindungen.add(clientHandler);
        users.put(clientHandler.ID, clientHandler);
        return true;
    }

    /**
     * Diese Methode prüft, ob eine Figur schon von einem Spieler ausgewählt wurde und fügt die Figur hinzu.
     * @param figur Die gewünschte Figur
     * @param clientHandler Der Spieler, der die Figur auswählen möchte
     * @return Boolean der angibt, ob das Einfügen der Figur erfolgreich war
     */
    public boolean checkFigure(int figur, ClientHandler clientHandler){
        if(figuren[figur] == null){
            figuren[figur] = new Robot(clientHandler.ID);
            return true;
        } else {
            return false;
        }
    }

    /**
     * Diese Methode entfernt einen Spieler, der seine Verbindung verloren hat und informiert die übrigen Spieler.
     * @param clientHandler Der Spieler, der die Verbindung verloren hat
     */
    public void exitPlayer(ClientHandler clientHandler){
        users.remove(clientHandler.ID);
        verbindungen.remove(clientHandler);
        figuren[clientHandler.figure] = null;
        ConnectionUpdate connectionUpdate = new ConnectionUpdate(clientHandler.ID, false, "Remove");
        connectionUpdate.getMessageBody().setKeys(new String[]{"clientID", "isConnected", "action"});
        game.exitPlayer(clientHandler);
        for (ClientHandler cH: verbindungen){
            cH.writer.println(Adopter.javabeanToJson(connectionUpdate));
            RalleyLogger.getLogger().info(Adopter.javabeanToJson(connectionUpdate));
        }
    }

    /**
     * Diese Methode leitet die chooseRegister-Nachricht an das Spiel weiter
     * @param clientHandler Der Spieler, der die Nachricht verschickt hat
     */
    public void handleChooseRegister(ClientHandler clientHandler){
        game.handleChooseRegister(clientHandler);
    }

    /**
     * Fügt einen Spieler dem Spiel (und dem Chat-Raum) hinzu.
     * @param clientHandler Der Spieler, der hinzugefügt werden soll
     */
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

    /**
     * Verarbeitet die Ready-Nachricht, die von Spielern verschickt wird.
     * Sofern es nötig ist, wird die Auswahl der Map eingeleitet oder das Spiel gestartet.
     * @param cH Der Spieler, der die Nachricht verschickt hat
     */
    public void handleReady(ClientHandler cH){
        if (cH.isReady) {
            if (!this.mapSelected && !cH.isAi) {
                clientWhoSelectedMap = cH;
                mapSelected = true;
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

    /**
     * Verarbeitet die Map-Auswahl eines Spielers.
     * @param map Die Map, die ausgewählt wurde
     */
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

    /**
     * Hier wird die Map generiert und an alle Spieler verschickt.
     */
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
            case "Twister":
                board = new Twister();
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

    /**
     * Die selectedCard-Nachricht wird an das Spiel weitergeleitet.
     * @param card Die Karte, die ausgewählt wurde
     * @param register Das Register, das ausgewählt wurde
     * @param clientHandler Der Spieler, der die Karte ausgewählt hat
     */
    public void handleSelectedCard(String card, int register, ClientHandler clientHandler){
        game.handleSelectedCard(card, register, clientHandler);
    }

    /**
     * Das Festlegen der Direction nach dem Tod wird an das Spiel weitergeleitet.
     * @param string Die neue Direction
     * @param clientHandler Der Spieler, der gestorben ist und die neue Richtung gewählt
     */
    public void handleRebootDirection(String string, ClientHandler clientHandler){
        game.handleRebootDirection(string, clientHandler);
    }

    /**
     * Prüft, ob das Spiel gestartet werden kann.
     * @return Boolean der angibt, ob das Spiel gestartet werden kann
     */
    public boolean readyToStart(){
        if (activeMap != null) {
            int count = 0;
            for (ClientHandler clientHandler : users.values()) {
                count += 1;
                if (!clientHandler.isReady) {
                    return false;
                }
            }
            if (count > 1 && mapSelected) {
                return true;
            } else {
                return false;
            }
        } else return false;
    }

    /**
     * Leitet die selectedDamage-Nachricht an das Spiel weiter
     * @param clientHandler Der Spieler, von dem die Nachricht verschickt wurde
     * @param card Die Karte, die gewählt wurde
     */
    public void handleSelectDamage(ClientHandler clientHandler, ArrayList<String> card){
        String karte = card.get(0);
        game.chooseDamageCard(clientHandler, karte);
    }

    /**
     * Die Nachricht wird an das Spiel weitergeleitet.
     * @param isBuying Gibt an, ob der Spieler eine Karte kaufen möchte
     * @param card Die Karte, die gekauft werden soll
     * @param clientHandler Der Spieler, der die Karte kaufen möchte
     */
    public void handleBuyUpgrade(boolean isBuying, String card, ClientHandler clientHandler){
        game.handleBuyUpgrade(isBuying, card, clientHandler);
    }

    /**
     * Erstellt das Spiel
     */
    public void createGame(){
        game = new Game(this, users, verbindungen, activeMap, figuren);
    }

    /**
     * Leitet die Nachricht an das Spiel weiter
     * @param x Koordinate des Startpunktes
     * @param y Koordinate des Startpunktes
     * @param clientHandler Der Spieler, der die Nachricht verschickt hat
     */
    public void setStartingPoint(int x, int y, ClientHandler clientHandler){
        game.setStartingPoint(x, y, clientHandler);
    }

    /**
     * Der Startpunkt wird bestätigt und allen Spielern mitgeteilt.
     * @param x Koordinate des Startpunktes
     * @param y Koordinate des Startpunktes
     * @param clientHandler Der Spieler, der einen Startpunkt gewählt hat
     */
    public void validStartingPoint(int x, int y, ClientHandler clientHandler){
        StartingPointTaken startingPointTaken = new StartingPointTaken(x, y, clientHandler.ID);
        startingPointTaken.getMessageBody().setKeys(new String[]{"x", "y", "clientID"});
        sendMessageForAllUsers(startingPointTaken);
    }

    /**
     * Diese Methode startet den Server und baut die Verbindungen mit neuen Spielern auf.
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

    /**
     * Die HelloClient Nachricht wird an den neuen Client verschickt.
     * @param clientHandler Der Spieler, der neu hinzugefügt wurde
     */
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

    /**
     * Die Nachricht, dass eine Karte gespielt wurde, wird an alle Spieler weitergeleitet.
     * @param card Die gespielte Karte
     * @param ID Die ID des Spielers, der die Karte gespielt hat
     */
    public void handlePlayCard(String card, int ID){
        CardPlayed cardPlayed = new CardPlayed(ID, card);
        String[] keys = {"clientID", "card"};
        cardPlayed.getMessageBody().setKeys(keys);
        for(ClientHandler clientHandler: users.values()){
            clientHandler.writer.println(Adopter.javabeanToJson(cardPlayed));
        }
        ralleyLogger.getLogger().info(Adopter.javabeanToJson(cardPlayed));
    }

    /**
     * Weiterleitung der Nachricht an den Server
     * @param cards Liste der zurückgegebenen Karten
     * @param clientHandler Der Spieler, der diese Aktion initiiert
     */
    public void handleReturnCards(ArrayList<String> cards, ClientHandler clientHandler){
        game.handleReturnCards(cards, clientHandler);
    }

    /**
     * Hier wird eine neue ID generiert
     * @return
     */
    public int generateID(){
        int ID = Server.laufendeID;
        laufendeID++;
        return ID;
    }

    /**
     * Hier wird das Ende eines Spiels behandelt.
     * @param robot Der Gewinner
     */
    public void endGame(Robot robot){
        GameFinished gameFinished = new GameFinished(robot.getGamerID());
        gameFinished.getMessageBody().setKeys(new String[]{"clientID"});
        sendMessageForAllUsers(gameFinished);
        //System.exit(0);
    }

    /**
     * Dies ist ein Getter
     * @return Das Spiel
     */
    public Game getGame() {
        return game;
    }

    /**
     * Dies ist ein Setter
     * @param bot Der neue Roboter
     */
    public static synchronized void setChoosenBots(int bot) {
        choosenBots.add(bot);
    }
}

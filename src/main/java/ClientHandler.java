import Messages.Adopter;
import Messages.Error1;
import Messages.Message;
import Messages.Welcome;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Diese Klasse fungiert als Bindestück zwischen Client Server.
 * Sie sorgt für die richtige Verarbeitung der Nachrichten und Befehle, die von den Benutzern (Clients)
 * gesendet werden und verschickt Informationen vom Server an den zugehörigen Client.
 *
 * @author Amir Azim
 * @author Dairen Gonschior
 * @author Luca Weyhofen
 *
 * @version 2.1
 */

public class ClientHandler implements Runnable {

    public final Server SERVER;
    public final Socket SOCKET;
    public int ID;

    public String username;
    public int figure;
    public int chosenRegister;
    public String group;
    public boolean isAi;
    public boolean isReady;

    public BufferedReader reader;
    public PrintWriter writer;

    /**
     * Dies ist der Konstruktor
     * @param socket Initialisiert die Globale Variable Socket
     * @param server Initialisiert die Globale Variable Server
     */
    public ClientHandler(Socket socket, Server server) {
        this.SERVER = server;
        this.SOCKET = socket;
        this.chosenRegister = 0;

        try {
            writer = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(SOCKET.getInputStream()));
        } catch (Exception e) {
            closeEverything(socket, reader, writer);
            e.printStackTrace();
        }
    }

    /**
     * Diese Methode überschreibt die run() Methode von Runnable. Hier werden Eingaben der Benutzer verarbeitet.
     */
    @Override
    public void run() {
        Timer t = new Timer();
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                writer.println("{\"messageType\": \"Alive\", \"messageBody\": {}}");
            }
        };
        t.schedule(timerTask, 0, 5000);
        while (SOCKET.isConnected() && !SOCKET.isClosed()) {
            if (SOCKET.isConnected() && !SOCKET.isClosed()) {
                try {
                    String input = reader.
                            readLine();
                    Message message = Adopter.getMessage(input);
                    if (message.getMessageType().equals("HelloServer")) {
                        if (!SERVER.protocol.equals((String) message.getMessageBody().getContent()[2])) {
                            Error1 error = new Error1("Das verwendete Protokoll wird nicht unterstützt. Das Programm wird jetzt beendet.");
                            String[] key = {"error"};
                            error.getMessageBody().setKeys(key);
                            writer.println(Adopter.javabeanToJson(error));
                        } else {
                            sendWelcomeMessage(message);
                        }
                    } else if (message.getMessageType().equals("SendChat")) {
                        String toSend = (String) message.getMessageBody().getContent()[0];
                        int to = (int) (double) message.getMessageBody().getContent()[1];
                        if (to == -1) {
                            SERVER.messageForAllUsers(toSend, this.ID);
                        } else {
                            SERVER.singleMessage(this.ID, toSend, to);
                        }
                    } else if (message.getMessageType().equals("PlayerValues")) {
                        int figure = (int) (double) message.getMessageBody().getContent()[1];
                        String name = (String) message.getMessageBody().getContent()[0];
                        username = name;
                        if (SERVER.checkFigure(figure, this)) {
                            this.figure = figure;
                            SERVER.playerAdded(this);
                        } else {
                            Error1 error = new Error1("Diese Figur wurde bereits von einem anderem Spieler gewählt.");
                            String[] keys = {"error"};
                            error.getMessageBody().setKeys(keys);
                            writer.println(Adopter.javabeanToJson(error));
                        }
                    } else if (message.getMessageType().equals("SetStatus")) {
                        boolean ready = (boolean) message.getMessageBody().getContent()[0];
                        this.isReady = ready;
                        SERVER.handleReady(this);
                    } else if (message.getMessageType().equals("MapSelected")) {
                        String map = (String) message.getMessageBody().getContent()[0];
                        SERVER.handleMapSelected(map);
                    } else if (message.getMessageType().equals("PlayCard")) {
                        String card = (String) message.getMessageBody().getContent()[0];
                        SERVER.handlePlayCard(card, ID);
                    } else if (message.getMessageType().equals("SetStartingPoint")) {
                        int x = (int) (double) message.getMessageBody().getContent()[0];
                        int y = (int) (double) message.getMessageBody().getContent()[1];
                        SERVER.setStartingPoint(x, y, this);
                    } else if (message.getMessageType().equals("SelectedCard")) {
                        String card = (String) message.getMessageBody().getContent()[0];
                        int register = (int) (double) message.getMessageBody().getContent()[1];
                        SERVER.handleSelectedCard(card, register, this);
                    } else if (message.getMessageType().equals("RebootDirection")) {
                        String direction = (String) message.getMessageBody().getContent()[0];
                        SERVER.handleRebootDirection(direction, this);
                    } else if (message.getMessageType().equals("SelectedDamage")) {
                        handleSelectedDamage(message);
                    } else if (message.getMessageType().equals("BuyUpgrade")) {
                        handleBuyUpgrade(message);
                    } else if (message.getMessageType().equals("ReturnCards")) {
                        handleReturnCards(message);
                    } else if (message.getMessageType().equals("ChooseRegister")) {
                        handleChooseRegister(message);
                    }
                } catch (Exception e) {
                    SERVER.exitPlayer(this);
                    try {
                        SOCKET.close();
                        writer.close();
                        reader.close();
                    } catch (IOException f) {
                    }
                }
            } else {
            }
        }
    }

    /**
     * Die chooseRegister-Nachricht wird an den Server weitergeleitet.
     * @param m Die empfangene Nachricht
     */
    public void handleChooseRegister(Message m){
        int register = (int) (double) m.getMessageBody().getContent()[0];
        this.chosenRegister = register;
        SERVER.handleChooseRegister(this);
    }

    /**
     * Die returnCards-Nachricht wird an den Server weitergeleitet.
     * @param message Die empfangene Nachricht
     */
    public void handleReturnCards(Message message){
        ArrayList<String> cards = (ArrayList<String>) message.getMessageBody().getContent()[0];
        SERVER.handleReturnCards(cards, this);
    }

    /**
     * Die buyUpgrade-Nachricht wird an den Server weitergeleitet.
     * @param message Die empfangene Nachricht
     */
    public void handleBuyUpgrade(Message message){
        boolean isBuying = (boolean) message.getMessageBody().getContent()[0];
        String card = (String) message.getMessageBody().getContent()[1];
        SERVER.handleBuyUpgrade(isBuying, card, this);
    }

    /**
     * Die selectDamage-Nachricht wird an den Server weitergeleitet.
     * @param message Die empfangene Nachricht
     */
    public void handleSelectedDamage(Message message){
        ArrayList<String> list = (ArrayList<String>) message.getMessageBody().getContent()[0];
        SERVER.handleSelectDamage(this, list);
    }

    /**
     * Die Welcome-Nachricht wird an den Client verschickt.
     * @param message Die empfangene helloServer-Nachricht
     */
    public void sendWelcomeMessage(Message message){
        isAi = (boolean) message.getMessageBody().getContent()[1];
        group = (String) message.getMessageBody().getContent()[0];
        int id = SERVER.generateID();
        this.ID = id;
        SERVER.addClient(this);
        Welcome welcome = new Welcome(ID);
        String[] key = {"clientID"};
        welcome.getMessageBody().setKeys(key);
        writer.println(Adopter.javabeanToJson(welcome));
    }

    /**
     * Diese Methode schließt für ein ordnungsgemäßes Schließen der Verbindung zum Server alle zugehörigen Instanzen.
     * @param socket Der Socket, der geschlossen werden soll
     * @param bufferedReader Der BufferedReader, der geschlossen werden soll
     * @param bufferedWriter Der BufferedWriter, der geschlossen werden soll
     */
    public void closeEverything(Socket socket, BufferedReader bufferedReader, PrintWriter bufferedWriter) {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (bufferedWriter != null) {
                bufferedWriter.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

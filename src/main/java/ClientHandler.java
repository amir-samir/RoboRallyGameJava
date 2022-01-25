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
 * Diese Klasse fungiert als Bindestück zwischen game.Client und game.Server. Sie sorgt für die richtige Verarbeitung der
 * Nachrichten und Befehle, die von den Benutzern (Clients) gesendet werden.
 * @author Luca, Dairen
 */

public class ClientHandler implements Runnable {

    public final Server SERVER;
    public final Socket SOCKET;
    public int ID;

    public String username;
    public int figure;
    public String group;
    public boolean isAi;
    public boolean isReady;

    public BufferedReader reader;
    public PrintWriter writer;
    /**
     * Diese Methode stellt den Konstruktor dar. Sie initialisiert die globalen Variablen und fügt nach Überprüfung den
     * eingegebenen Namen als neuen Username hinzu.
     * @param socket Initialisiert die Globale Variable Socket.
     * @param server Initialisiert die Globale Variable game.Server.
     */

    public ClientHandler(Socket socket, Server server) {

        this.SERVER = server;
        this.SOCKET = socket;

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
                //System.out.println("Timer aktiviert");
            }
        };
        t.schedule(timerTask, 0,5000);
        while (SOCKET.isConnected()) {
            try{
                String input = reader.
                        readLine();
                Message message = Adopter.getMessage(input);
                if (message.getMessageType().equals("HelloServer")){
                    if(!SERVER.protocol.equals((String) message.getMessageBody().getContent()[2])){
                        Error1 error = new Error1("Das verwendete Protokoll wird nicht unterstützt. Das Programm wird jetzt beendet.");
                        String[] key = {"error"};
                        error.getMessageBody().setKeys(key);
                        writer.println(Adopter.javabeanToJson(error));
                    } else {
                        sendWelcomeMessage(message);
                    }
                } else if (message.getMessageType().equals("SendChat")){
                    String toSend = (String) message.getMessageBody().getContent()[0];
                    int to = (int)(double) message.getMessageBody().getContent()[1];
                    if (to == -1){
                        SERVER.messageForAllUsers(toSend, this.ID);
                    } else {
                        SERVER.singleMessage(this.ID, toSend, to);
                    }
                } else if (message.getMessageType().equals("PlayerValues")){
                    int figure = (int) (double) message.getMessageBody().getContent()[1];
                    String name = (String) message.getMessageBody().getContent()[0];
                    username = name;
                    SERVER.addUsername(this);
                    if(SERVER.checkFigure(figure, this)){
                        this.figure = figure;
                        SERVER.playerAdded(this);
                    } else {
                        Error1 error = new Error1("Diese Figur wurde bereits von einem anderem Spieler gewählt.");
                        String[] keys = {"error"};
                        error.getMessageBody().setKeys(keys);
                        writer.println(Adopter.javabeanToJson(error));
                    }
                } else if (message.getMessageType().equals("SetStatus")){
                    boolean ready = (boolean) message.getMessageBody().getContent()[0];
                    this.isReady = ready;
                    SERVER.handleReady(this);
                }
                else if(message.getMessageType().equals("MapSelected")){
                    String map = (String) message.getMessageBody().getContent()[0];
                    SERVER.handleMapSelected(map);
                } else if(message.getMessageType().equals("PlayCard")){
                    String card = (String) message.getMessageBody().getContent()[0];
                    SERVER.handlePlayCard(card, ID);
                } else if (message.getMessageType().equals("SetStartingPoint")){
                    int x = (int) (double) message.getMessageBody().getContent()[0];
                    int y = (int) (double) message.getMessageBody().getContent()[1];
                    SERVER.setStartingPoint(x, y, this);
                } else if (message.getMessageType().equals("SelectedCard")){
                    String card = (String) message.getMessageBody().getContent()[0];
                    int register = (int)(double) message.getMessageBody().getContent()[1];
                    SERVER.handleSelectedCard(card, register, this);
                } else if (message.getMessageType().equals("RebootDirection")){
                    String direction = (String) message.getMessageBody().getContent()[0];
                    SERVER.handleRebootDirection(direction, this);
                } else if (message.getMessageType().equals("SelectedDamage")){
                    handleSelectedDamage(message);
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void handleSelectedDamage(Message message){
        ArrayList<String> list = (ArrayList<String>) message.getMessageBody().getContent()[0];
        SERVER.handleSelectDamage(this, list);
    }

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
     * Diese Methode schließt für ein ordnungsgemäßes Schließen der Verbindung zum game.Server alle zugehörigen Instanzen.
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

    public String getUsername(){
        return username;
    }
}

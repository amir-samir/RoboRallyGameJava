import Messages.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Diese Klasse fungiert als Bindestück zwischen Client und Server. Sie sorgt für die richtige Verarbeitung der
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

    public BufferedReader reader;
    public PrintWriter owriter;
    public static ObservableList<String> usernamesGui = FXCollections.observableArrayList();

    /**
     * Diese Methode stellt den Konstruktor dar. Sie initialisiert die globalen Variablen und fügt nach Überprüfung den
     * eingegebenen Namen als neuen Username hinzu.
     * @param socket Initialisiert die Globale Variable Socket.
     * @param server Initialisiert die Globale Variable Server.
     */

    public ClientHandler(Socket socket, Server server) {

        this.SERVER = server;
        this.SOCKET = socket;

        try {
            owriter = new PrintWriter(socket.getOutputStream(), true);
            reader = new BufferedReader(new InputStreamReader(SOCKET.getInputStream()));
        } catch (Exception e) {
            closeEverything(socket, reader, owriter);
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
                owriter.println("{\"messageType\": \"Alive\", \"messageBody\": {}}");
                System.out.println("Timer aktiviert");
            }
        };
        t.schedule(timerTask, 0,5000);
        while (SOCKET.isConnected()) {
            try{
                String input = reader.readLine();
                Message message = Adopter.getMessage(input);
                if (message.getMessageType().equals("HelloServer")){
                    if(!SERVER.protocol.equals((String) message.getMessageBody().getContent()[0])){
                        Error1 error = new Error1("Das verwendete Protokoll wird nicht unterstützt. Das Programm wird jetzt beendet.");
                        String[] key = {"error"};
                        error.getMessageBody().setKeys(key);
                        owriter.println(Adopter.javabeanToJson(error));
                    } else {
                        sendWelcomeMessage(message);
                    }
                } else if (message.getMessageType().equals("SendChat")){
                    String toSend = (String) message.getMessageBody().getContent()[1];
                    int to = (int)(double) message.getMessageBody().getContent()[0];
                    if (to == -1){
                        SERVER.messageForAllUsers(toSend, this.ID);
                    } else {
                        SERVER.singleMessage(this.ID, toSend, to);
                    }
                } else if (message.getMessageType().equals("PlayerValues")){
                    int figure = (int) (double) message.getMessageBody().getContent()[0];
                    String name = (String) message.getMessageBody().getContent()[1];
                    username = name;
                    SERVER.addUsername(this);
                    if(SERVER.checkFigure(figure, this)){
                        this.figure = figure;
                        SERVER.playerAdded(this);
                    } else {
                        Error1 error = new Error1("Diese Figur wurde bereits von einem anderem Spieler gewählt.");
                        String[] keys = {"error"};
                        error.getMessageBody().setKeys(keys);
                        owriter.println(Adopter.javabeanToJson(error));
                    }
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void sendWelcomeMessage(Message message){
        isAi = (boolean) message.getMessageBody().getContent()[1];
        group = (String) message.getMessageBody().getContent()[2];
        int id = SERVER.generateID();
        this.ID = id;
        SERVER.addClient(this);
        Welcome welcome = new Welcome(ID);
        String[] key = {"clientID"};
        welcome.getMessageBody().setKeys(key);
        owriter.println(Adopter.javabeanToJson(welcome));
        System.out.println("Erledigt");
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

    public String getUsername(){
        return username;
    }
}

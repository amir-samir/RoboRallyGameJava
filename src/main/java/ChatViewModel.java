import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
/**
 * Model Class of chatview Window
 * @author Amir Azim
 */

public class ChatViewModel {
    private StringProperty message = new SimpleStringProperty();
    private BooleanProperty sendButton = new SimpleBooleanProperty();
    private BooleanProperty startGame = new SimpleBooleanProperty();
    private StringProperty privteMessage = new SimpleStringProperty();

    private Client client;



    /**
     * Sets the current client
     * @param client client which should be setted
     */
    public void setClient(Client client) {
        this.client = client;
    }


    /**
     * Getter for the current client.
     * @return The current client-object
     */
    public Client getClient() {

        return client;
    }


    /**
     * Getter für die message
     * @return Gibt die message zurück
     */
    public StringProperty messageProperty() {
        return message;
    }
    /**
     * Getter für die privateMessage
     * @return Gibt die privateMessage zurück
     */
    public StringProperty privateMessageProperty() {
        return privteMessage;
    }

    /**
     * Getter für die final Message
     * @return Gibt die final message zurück
     */
    public final String getMessage() {
        return message.get() != null ? message.get() : "";
    }

    /**
     * Setter für die final Message
     * @param newMessage ISt der Inhalt der message
     */
    public final void setMessage(String newMessage) {
        message.set(newMessage);
    }
    /**
     * Getter für die startGame variable
     * @return Gibt startGame zurück
     */
    public BooleanProperty startGameProperty() {
        return startGame;
    }
    /**
     * Getter für sendButton
     * @return sendButton wird zurückgegeben
     */
    public BooleanProperty sendButtonProperty() {
        return sendButton;
    }
    /**
     * Setzt den Client auf Ready bzw. Unready
     */
    public final void startingGame() {
        client.setReady();
    }

    /**
     * Holt sich die aktuelle Nachricht und leitet diese an den Server weiter.
     */
    public final void sendMessage() {
        String currentMessage = getMessage();
        client.printMessage(currentMessage);
        // Close window and stop process if user enters "!BYE"
        if (currentMessage.equalsIgnoreCase("BYE")) {
            Platform.exit();
            System.exit(0);
        } else {
            this.setMessage("");
        }
    }

}

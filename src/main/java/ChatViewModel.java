import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ChatViewModel {
    // Properties corresponding to ChatView
    private StringProperty message = new SimpleStringProperty();
    private BooleanProperty sendButton = new SimpleBooleanProperty();
    private BooleanProperty startGame = new SimpleBooleanProperty();
    private BooleanProperty exitGame = new SimpleBooleanProperty();
    private BooleanProperty joinGame = new SimpleBooleanProperty();
    private BooleanProperty createRoomGame = new SimpleBooleanProperty();
    private StringProperty privteMessage = new SimpleStringProperty();

    private Thread clientThread;
    private Client client;
    private ClientHandler clientHandler;


    /**
     * Sets and runs the current client, called from the View-Class
     * param client The successfully constructed Client from Welcome-View
     *  clientHandler
     */
    public void setClient(Client client) {
        this.client = client;
        this.clientThread = new Thread(client);
        clientThread.start();
    }

    public void chooseBotClient() {

    }

    /**
     * Get the current client. Accessed by the ChatView-Class to append incoming messages
     * @return The current client-object
     */
    public Client getClient() {

        return client;
    }

    // Getters and Setters for Properties

    /**
     * Access the message property for bidirectional binding with the View-Class
     * @return The message String Property bound to the Textfield
     */
    public StringProperty messageProperty() {
        return message;
    }
    public StringProperty privateMessageProperty() {
        return privteMessage;
    }

    /**
     * Retrieves the actual String-message from the message property
     * Performs a quick check if the message entered is null.
     * @return the actual message submitted by the user
     */
    public final String getMessage() {
        return message.get() != null ? message.get() : "";
    }

    /**
     * Sets the content of the user's text field.
     * Used in sendMessage()-method to set the text field back to empty after the user hits submit-button
     * @param newMessage The new content of the message-text field
     */
    public final void setMessage(String newMessage) {
        message.set(newMessage);
    }
    /**
     * Retrieves the current property of the startGame
     * @return Boolean Property of the startGame
     */
    public BooleanProperty startGameProperty() {
        return startGame;
    }
    /**
     * Retrieves the current property of the exitGame
     * @return Boolean Property of the exitGame
     */
    public BooleanProperty joinGameProperty() {
        return joinGame;
    }
    /**
     * Retrieves the current property of the exitGame
     * @return Boolean Property of the exitGame
     */
    public BooleanProperty exitGameProperty() {
        return exitGame;
    }
    /**
     * Retrieves the current property of the sendButton
     * @return Boolean Property of the sendButton
     */
    public BooleanProperty sendButtonProperty() {
        return sendButton;
    }
    /**
     * Retrieves the current property of the createRoomGameProperty
     * @return Boolean Property of the createRoomGameProperty
     */
    public BooleanProperty createRoomGameProperty() {
        return createRoomGame;
    }
    /**
     * Retrieves the current property of the leftCard
     * @return Boolean Property of the leftCard
     */

    public final void joiningGame() {
        client.printMessage("/JOIN");
    }
    public final void createRoomGame()  {
        client.printMessage("/CREATE");

    }
    /**
     * set Ready for the Client
     */
    public final void startingGame() {
        //client.printMessage("funktioniert");
        SaveClients.client.setReady();
    }

    /**
     * set unready for Client
     */
    public final void exitingGame() {
        client.printMessage("set unready");
        client.setReady();
    }
    /**
     * Fetch the current message from ChatView, pass it on to the server and
     * empty the text field for the user.
     * Close the screen and shutdown client-side process if user enters "!BYE".
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

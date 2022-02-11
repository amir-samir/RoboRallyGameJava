import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
/**
 * Model Class of FirstView Window
 * @author Dairen Gonschior, Amir Azim, Mateo
 */
public class FirstViewModel {

Client client1;
    FirstView firstView;
    @FXML
    private ClientHandler clientHandler;
    Socket socket = new Socket();

    private StringProperty username = new SimpleStringProperty();
    private BooleanProperty signInButton = new SimpleBooleanProperty();


    /**
     * Getter username
     */
    public StringProperty getUsernameProperty() {
        return username;
    }
    /**
     * signInButton Getter
     */
    public BooleanProperty getSignInButton(){
        return signInButton;
    }

    /**
     * Getter final Username
     */
    public final String getUsername(){
        return username.get();
    }

    /**
     * Setter username, wird nicht verwendet
     */
    public final void setUsername(String readUsername){
        username.set(readUsername);
    }

    /**
     * Wird nicht verwendet
     */
    public void chooseUsername() throws IOException {
        //game.Client client = new game.Client(getUsername());
        //client1 = client;
    }


    /**
     * Öffnet das ChatView Fenster und schließt die FirstView
     * @param stage ist die stage der Chatview
     */
    public void takeUsername(Stage stage) throws IOException {

        //game.Client client = new game.Client(getUsername());
        //clientHandler.getUsername();
        stage.setTitle(SaveClients.client.getTitleUserName());
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ChatView.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        StageSaver.getStageSaver().setChatViewStage(stage);
        //scene.getStylesheets().add("fxml/SignInStyle.css");

        ChatView chatView = loader.getController();
        chatView.setClient();
        stage.show();

        //Close if Bye
        stage.setOnCloseRequest(e -> {
            //Platform.exit();
            //clientHandler.writer.equals("bye");
            stage.close();
            //System.exit(0);
        });
    }
}

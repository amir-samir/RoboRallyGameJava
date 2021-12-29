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

public class FirstViewModel {

Client client1;
    FirstView firstView;
    @FXML
    private ClientHandler clientHandler;
    Socket socket = new Socket();

    private StringProperty username = new SimpleStringProperty();
    private BooleanProperty signInButton = new SimpleBooleanProperty();

    public StringProperty getUsernameProperty() {
        return username;
    }

    public BooleanProperty getSignInButton(){
        return signInButton;
    }

    public final String getUsername(){
        return username.get();
    }

    public final void setUsername(String readUsername){
        username.set(readUsername);
    }


    public void chooseUsername() throws IOException {
        //Client client = new Client(getUsername());
        //client1 = client;
    }



    public void takeUsername(Stage stage) throws IOException {

        //Client client = new Client(getUsername());
        //clientHandler.getUsername();
           stage.setTitle(getUsername());



        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/ChatView.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        scene.getStylesheets().add("css/SignInStyle.css");

        ChatView chatView = loader.getController();
        chatView.setClient(client1);
        stage.show();

        //Close if Bye
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            //clientHandler.writer.equals("bye");
            stage.close();
            System.exit(0);
        });
    }
}

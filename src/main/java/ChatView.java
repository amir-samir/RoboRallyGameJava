import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class ChatView {

    // Chat
    @FXML
    private AnchorPane rootPaneChat;
    @FXML
    private AnchorPane chatWrite;
    @FXML
    private AnchorPane chatPane;
    @FXML
    private TextField writeField;
    @FXML
    private Button sendButton;
    @FXML
    private Button joinGame;
    @FXML
    private Button startGame;
    @FXML
    private Button exitGame;
    @FXML
    private Button createRoomGame;

    public static ObservableList<String> usernamesUpdated = FXCollections.observableArrayList();

    @FXML
    private ListView<String> chatBox;

    private final ChatViewModel viewModel = new ChatViewModel();

    @FXML
    private ComboBox PrivateMessage;

    @FXML
    private TextField privateMsgInput;

    @FXML
    void initialize() {
        writeField.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                viewModel.sendMessage();
            }
        } );
        writeField.textProperty().bindBidirectional(viewModel.messageProperty());
        sendButton.defaultButtonProperty().bindBidirectional(viewModel.sendButtonProperty());
        //joinGame.defaultButtonProperty().bindBidirectional(viewModel.joinGameProperty());
        startGame.defaultButtonProperty().bindBidirectional(viewModel.startGameProperty());
        exitGame.defaultButtonProperty().bindBidirectional(viewModel.exitGameProperty());
        //createRoomGame.defaultButtonProperty().bindBidirectional(viewModel.createRoomGameProperty());
        //ObservableList idsNamesList = (ObservableList) Client.ids;
        //PrivateMessage.setItems(viewModel.getClient().usernamesGui);
        // usernamesUpdated = Client.getUsernames();
        PrivateMessage.setItems(SaveClients.client.usernamesGui);
        privateMsgInput.textProperty().bindBidirectional(viewModel.privateMessageProperty());

        //PrivateMessage.getSelectionModel().selectFirst();


    }

    public void sendPrivateMsgFun() {
        String selectedUser = PrivateMessage.getValue().toString().split(",")[1];
        String msg = privateMsgInput.getText();

        SaveClients.client.singleMessage(SaveClients.client.getID(), msg, selectedUser);
    }
    public void comboAction(ActionEvent event) {
        String selectedUser = PrivateMessage.getValue().toString().split(",")[1];
        System.out.println(privateMsgInput.getText());

    }
    public void joinGame() {
        viewModel.joiningGame();
        joinGame.setDisable(true);
        System.out.println(SaveClients.client.usernamesGui);


    }
    public void startGame() {
        System.out.println(PrivateMessage);
        viewModel.startingGame();
        startGame.setDisable(true);
    }

    public void exitGame() {
        viewModel.exitingGame();
    }
    public void createRoomGame() throws IOException {
        viewModel.createRoomGame();
    }


    public void sendMessage() {

        viewModel.sendMessage();

    }

    public void selectMap() throws IOException {
        Stage stage1 = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/SelectMap.fxml"));
        Scene scene = new Scene(loader.load());
        stage1.setScene(scene);
        scene.getStylesheets().add("SignInStyle.css");
        stage1.show();

        //Close if Bye
        stage1.setOnCloseRequest(e -> {
            Platform.exit();
            //clientHandler.writer.equals("bye");
            stage1.close();
            System.exit(0);
        });
    }



    public void setClient() {

        viewModel.setClient(SaveClients.client);
        chatBox.setItems(SaveClients.client.chatMessages);
        PrivateMessage.setItems(SaveClients.client.usernamesGui);

    }

}

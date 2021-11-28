import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;


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

    @FXML
    private ListView<String> chatBox;

    private final ChatViewModel viewModel = new ChatViewModel();


    @FXML
    void initialize() {

        writeField.textProperty().bindBidirectional(viewModel.messageProperty());
        sendButton.defaultButtonProperty().bindBidirectional(viewModel.sendButtonProperty());
        joinGame.defaultButtonProperty().bindBidirectional(viewModel.joinGameProperty());
        startGame.defaultButtonProperty().bindBidirectional(viewModel.startGameProperty());
        exitGame.defaultButtonProperty().bindBidirectional(viewModel.exitGameProperty());
        createRoomGame.defaultButtonProperty().bindBidirectional(viewModel.createRoomGameProperty());

    }

    public void joinGame() {
        viewModel.joiningGame();
        joinGame.setDisable(true);

    }
    public void startGame() {
        viewModel.startingGame();
        startGame.setDisable(true);

    }

    public void exitGame() {
        viewModel.exitingGame();
    }
    public void createRoomGame() {
        viewModel.createRoomGame();
    }


    public void sendMessage() {

        viewModel.sendMessage();

    }


    public void setClient(Client client) {

        viewModel.setClient(client);
        chatBox.setItems(viewModel.getClient().chatMessages);

    }

}

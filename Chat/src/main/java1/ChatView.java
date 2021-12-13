import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

    @FXML
    private ListView<String> chatBox;

    private final ChatViewModel viewModel = new ChatViewModel();



    @FXML
    void initialize() {
        writeField.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                viewModel.sendMessage();
            }
        } );
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
    public void createRoomGame() throws IOException {
        viewModel.createRoomGame();
    }


    public void sendMessage() {

        viewModel.sendMessage();

    }
    public void chooseRoboter() {
        // Parent loader1;

        try {
            //Passing the current stage to the ViewModel
            Stage stageRobot = new Stage();
            //FXMLLoader loader1 = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("ChooseRobotView.fxml")));
            FXMLLoader loader1 = new FXMLLoader(getClass().getResource("ChooseRobotView.fxml"));
            Scene scene = new Scene(loader1.load());
            stageRobot.setScene(scene);
            ChooseRobotView chooseRobotView = loader1.getController();
            Client client = viewModel.getClient();
            chooseRobotView.setClientAndStage(client, stageRobot);
            stageRobot.show();

            //Client-Constructor throws DuplicateNameException if name already taken
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setClient(Client client) {

        viewModel.setClient(SaveClients.client);
        chatBox.setItems(SaveClients.client.chatMessages);

    }

}

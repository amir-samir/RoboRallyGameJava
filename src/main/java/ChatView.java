import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * View Class of chatview Window
 * @author Dairen Gonschior, Amir Azim
 */

public class ChatView {

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
    private ImageView FigureChat;

    @FXML
    Image figure0 = new Image("assets/twonkyMovegif.gif");
    Image figure1 = new Image("assets/smashbotMove2.gif");
    Image figure2 = new Image("assets/hulkMove2.gif");
    Image figure3 = new Image("assets/zoombotgif.gif");
    Image figure4 = new Image("assets/spinbotMove1.gif");
    Image figure5 = new Image("assets/hammerMove2.gif");
    @FXML
    Image testImage = new Image("assets/LostBearingMap.png");

    Client clientnew = Client.getClient();

    public ChatView(){
        clientnew.setChatViewModel(this);
    }

    /**
     * Initialisiert die ChatView
     */
    @FXML
    void initialize() {
        FigureChat.setImage(testImage);
        writeField.setOnKeyPressed( event -> {
            if( event.getCode() == KeyCode.ENTER ) {
                viewModel.sendMessage();
            }
        } );
        writeField.textProperty().bindBidirectional(viewModel.messageProperty());
        sendButton.defaultButtonProperty().bindBidirectional(viewModel.sendButtonProperty());
        startGame.defaultButtonProperty().bindBidirectional(viewModel.startGameProperty());
        PrivateMessage.setItems(clientnew.usernamesGui);
        privateMsgInput.textProperty().bindBidirectional(viewModel.privateMessageProperty());


    }
    /**
     * Handles the sending of private messages
     */
    public void sendPrivateMsgFun() {
        String selectedUser = PrivateMessage.getValue().toString().split(",")[0];
        String msg = privateMsgInput.getText();

        clientnew.singleMessage(clientnew.getID(), msg, Integer.parseInt(selectedUser));
    }
    /**
     * Allows the choices for the ChatView
     */
    public void comboAction(ActionEvent event) {
        String selectedUser = PrivateMessage.getValue().toString().split(",")[1];

    }

    /**
     * Initializes the ChatView
     */
    public void startGame() {
        viewModel.startingGame();
    }


    /**
     * Sends Groupmessage
     */
    public void sendMessage() {

        viewModel.sendMessage();

    }

    /**
     * Sets the client for the viewModel and thus provides the possibility to select the other clients for private messages
     */
    public void selectMap() throws IOException {
        Stage stage1 = new Stage();
        StageSaver.getStageSaver().setStageSaver(stage1);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/SelectMap.fxml"));
        Scene scene = new Scene(loader.load());
        stage1.setScene(scene);
        stage1.show();

        stage1.setOnCloseRequest(e -> {
            stage1.close();
        });
    }


    /**
     * Sets the client for the viewModel and thus
     * provides the possibility to select the other clients for private messages
     */
    public void setClient() {

        viewModel.setClient(clientnew);
        chatBox.setItems(clientnew.chatMessages);
        PrivateMessage.setItems(clientnew.usernamesGui);

    }
    /**
     Set the image to the image of the robot that was selected
     @param figure is the id of the selected roborter
     */
    public void setImageFromFigur(int figure){
        FigureChat.setImage(getImageForChatFigure(figure));
    }
    /**
     finds the right roborter image based on the robot id
     @param figure is the id of the selected roborter
     */
    public Image getImageForChatFigure(int figure) {
        switch (figure){
            case 0:
               return figure0;
            case 1:
                return figure1;
            case 2:
                return figure2;
            case 3:
                return figure3;
            case 4:
                return figure4;
            case 5:
                return figure5;
            default:
                return testImage;
        }


    }
    /**
     Opens the all-in-one view Window
     */
    public void runAllInOne() throws IOException {
        Stage stage = new Stage();
        stage.setTitle(SaveClients.client.getTitleUserName());
        Parent signIn = FXMLLoader.load(getClass().getResource("fxml/AllInOne.fxml"));
        Scene signInScene = new Scene(signIn);
        stage.setScene(signInScene);
        stage.show();
        StageSaver.getStageSaver().setAllInOneStage(stage);
        stage.setOnCloseRequest(e -> close());
    }
    /**
     Closes the game window and the client when pressing the close button
     */
    public void close() {
        try {
            SaveClients.client.getSOCKET().close();
            Platform.exit();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     Closes the game window and the client when pressing the close button
     */
    public ListView<String> getChatBox(){
        return chatBox;
    }



}

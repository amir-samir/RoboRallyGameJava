import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class AllInOneView implements Initializable {
    @FXML
    ImageView FigureChat;
    @FXML
    TextField privateMsgInput;
    @FXML
    ComboBox PrivateMessage;
    @FXML
    private ListView chatBox;
    @FXML
    TextField writeField;
    @FXML
    AnchorPane allInOnePane;
    @FXML
    AnchorPane chatPane;
    @FXML
    SplitPane SplitPaneee;


    public void besmella(){

    }
    public void sendMessage() {

        SaveClients.client.printMessage(writeField.textProperty().get());


    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //chatBox = SaveClients.client.getChatView().getChatBox();
        PrivateMessage.setItems(SaveClients.client.usernamesGui);
        chatPane.getChildren().add(SaveClients.client.getChatView().getChatBox());

    }

    public void sendPrivateMessage(){
        String selectedUser = PrivateMessage.getValue().toString().split(",")[1];
        String msg = privateMsgInput.getText();

        SaveClients.client.singleMessage(SaveClients.client.getID(), msg, selectedUser);
    }

    public void setReady(){
        SaveClients.client.setReady();
    }
}

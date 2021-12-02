import javafx.fxml.FXML;
import javafx.scene.control.Button;


public class ChooseRobotView {
    @FXML
    private Button smashBot;
    @FXML
    private Button spinBot;
    @FXML
    private Button twinkyBot;
    @FXML
    private Button zoomBot;
    @FXML
    private Button hulkBot;
    @FXML
    private Button hammerBot;

    private Thread clientThread;

    private final ChooseRobotViewModel chooseRobotViewModel = new ChooseRobotViewModel();
    @FXML
    void initialize() {

        smashBot.defaultButtonProperty().bindBidirectional(chooseRobotViewModel.getSmashBot());
        spinBot.defaultButtonProperty().bindBidirectional(chooseRobotViewModel.getSpinBot());
        twinkyBot.defaultButtonProperty().bindBidirectional(chooseRobotViewModel.getTwinkyBot());
        zoomBot.defaultButtonProperty().bindBidirectional(chooseRobotViewModel.getZoomBot());
        hulkBot.defaultButtonProperty().bindBidirectional(chooseRobotViewModel.getHulkBot());
        hammerBot.defaultButtonProperty().bindBidirectional(chooseRobotViewModel.getHammerBot());

    }

    public void setClient(Client client) {

        chooseRobotViewModel.setClient(client);
        //chatBox.setItems(viewModel.getClient().chatMessages);

    }

    public void OnRobotButton(){
        String bot = "No bot";
        if (!chooseRobotViewModel.getHammerBot().getValue()) {
            bot = hammerBot.getText();
            chooseRobotViewModel.sendChooesenBot("I choose " + bot);

        }

    }

}

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;


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

    private String bot;


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

    public void setClientAndStage(Client client, Stage stage) {

        chooseRobotViewModel.setClientandStage(client, stage);

    }

    public void OnHulkBot(){
        bot = hulkBot.getText();
        chooseRobotViewModel.sendChooesenBot("I choose " + bot);
        chooseRobotViewModel.closeStage();

    }

    public void OnHammerBot(){
            bot = hammerBot.getText();
            chooseRobotViewModel.sendChooesenBot("I choose " + bot);
            chooseRobotViewModel.closeStage();

        }

    public void OnSmashBot(){
        bot = smashBot.getText();
        chooseRobotViewModel.sendChooesenBot("I choose " + bot);
        chooseRobotViewModel.closeStage();

    }

    public void OnSpinBot(){
        bot = spinBot.getText();
        chooseRobotViewModel.sendChooesenBot("I choose " + bot);
        chooseRobotViewModel.closeStage();

    }
    public void OnTwinkyBot(){
        bot = twinkyBot.getText();
        chooseRobotViewModel.sendChooesenBot("I choose " + bot);
        chooseRobotViewModel.closeStage();

    }
    public void OnZoomBot(){
        bot = zoomBot.getText();
        chooseRobotViewModel.sendChooesenBot("I choose " + bot);
        chooseRobotViewModel.closeStage();

    }

    public String getBot(){
        return bot;
    }

}

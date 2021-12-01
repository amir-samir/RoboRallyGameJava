package main.java;

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

}

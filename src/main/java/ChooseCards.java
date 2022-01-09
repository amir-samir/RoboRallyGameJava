import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseCards implements Initializable {
    @FXML
    ImageView Card1;
    @FXML
    ImageView Card2;
    @FXML
    ImageView Card3;
    @FXML
    ImageView Card4;
    @FXML
    ImageView Card5;
    @FXML
    ImageView Card6;
    @FXML
    ImageView Card7;
    @FXML
    ImageView Card8;
    @FXML
    ImageView Card9;
   @FXML
    Image Move1 = new Image("assets/Move1.png");
    Image Move2 = new Image("assets/Move2.jpg");
    Image Move3 = new Image("assets/Move3.png");
    Image PowerUp = new Image("assets/PowerUp.png");
    Image RightTurn = new Image("assets/RightTurn.png");
    Image UTurn = new Image("assets/UTurn.png");
    Image LeftTurn = new Image ("assets/LeftTurn.png");


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Card1.setImage(Move1);
        Card2.setImage(Move2);
        Card3.setImage(Move3);
        Card4.setImage(PowerUp);
        Card5.setImage(RightTurn);
        Card6.setImage(UTurn);
        Card7.setImage(LeftTurn);
        Card8.setImage(Move1);
        Card9.setImage(Move1);

    }
}

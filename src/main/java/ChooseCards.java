import game.Card.*;
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
        Card1.setImage(getImageForCard(SaveClients.client.getHandcards().get(0).getName()));
        Card2.setImage(getImageForCard(SaveClients.client.getHandcards().get(1).getName()));
        Card3.setImage(getImageForCard(SaveClients.client.getHandcards().get(2).getName()));
        Card4.setImage(getImageForCard(SaveClients.client.getHandcards().get(3).getName()));
        Card5.setImage(getImageForCard(SaveClients.client.getHandcards().get(4).getName()));
        Card6.setImage(getImageForCard(SaveClients.client.getHandcards().get(5).getName()));
        Card7.setImage(getImageForCard(SaveClients.client.getHandcards().get(6).getName()));
        Card8.setImage(getImageForCard(SaveClients.client.getHandcards().get(7).getName()));
        Card9.setImage(getImageForCard(SaveClients.client.getHandcards().get(8).getName()));

    }

    public Image getImageForCard(String cardName) {
        switch (cardName) {
            case "MoveI":
                return Move1;
            case "MoveII":
                return Move2;
            case "MoveIII":
                return Move3;

            case "TurnLeft":
                return LeftTurn;

            case "TurnRight":
                return RightTurn;

            case "UTurn":
                return UTurn;

            case "BackUp":
                return Move1;
            case "PowerUp":
                return PowerUp;

            case "Again":
                return Move1;
            default:
                return Move1;
        }
    }

    public void ChooseCard1(){
        SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(0).getName(),0);
    }
    public void ChooseCard2(){
        SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(1).getName(),1);
    }
    public void ChooseCard3(){
        SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(2).getName(),2);
    }
    public void ChooseCard4(){
        SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(3).getName(),3);
    }
    public void ChooseCard5(){
        SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(4).getName(),4);
    }
    public void ChooseCard6(){
        SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(5).getName(),5);
    }
    public void ChooseCard7(){
        SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(6).getName(),6);
    }
    public void ChooseCard8(){
        SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(7).getName(),7);
    }
    public void ChooseCard9(){
        SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(8).getName(),8);
    }

}

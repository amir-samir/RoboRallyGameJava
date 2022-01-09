import game.Card.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseCards implements Initializable {
    int RegisterPlatz = -1;
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
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(0).getName(), RegisterPlatz);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(0).getName());
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard2(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(1).getName(), RegisterPlatz++);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(1).getName());
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard3(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(2).getName(), RegisterPlatz);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(2).getName());
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard4(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(3).getName(), RegisterPlatz);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(3).getName());
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard5(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(4).getName(), RegisterPlatz);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(4).getName());
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard6(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(5).getName(), RegisterPlatz);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(5).getName());
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard7(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(6).getName(), RegisterPlatz);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(6).getName());
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard8(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(7).getName(), RegisterPlatz);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(7).getName());
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard9(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(8).getName(), RegisterPlatz);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(8).getName());
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }

}

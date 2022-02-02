import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ChooseCardsForSwap implements Initializable {
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
    ImageView Card10;
    @FXML
    ImageView Card11;
    @FXML
    ImageView Card12;

    @FXML
    Image Move1 = new Image("assets/Move1.png");
    Image Move2 = new Image("assets/move2Blau.png");
    Image Move3 = new Image("assets/Move3Blau.png");
    Image PowerUp = new Image("assets/PowerUpBlau.png");
    Image RightTurn = new Image("assets/RightTurnBlau.png");
    Image UTurn = new Image("assets/UTurnBlau.png");
    Image LeftTurn = new Image ("assets/leftTurnBlau.png");
    Image BackUp = new Image("assets/MoveBack.png");
    Image Again = new Image("assets/Again.png");

    // Spam Cards
    @FXML
    Image spam = new Image("assets/spam.png");
    Image trojanHorse = new Image("assets/trojanHorse.png");
    Image virus = new Image("assets/virus.png");
    Image worm = new Image("assets/worm.png");

    int RegisterPlatz = 0;


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
        Card10.setImage(getImageForCard(SaveClients.client.getHandcards().get(9).getName()));
        Card11.setImage(getImageForCard(SaveClients.client.getHandcards().get(10).getName()));
        Card12.setImage(getImageForCard(SaveClients.client.getHandcards().get(11).getName()));
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
                return BackUp;

            case "PowerUp":
                return PowerUp;

            case "Again":
                return Again;

            case "Spam":
                return spam;

            case "Worm":
                return worm;

            case "Virus":
                return virus;

            case "Trojan":
                return trojanHorse;
            default:
                return Move1;
        }
    }

   /* public void ChooseCard1(){
        if (RegisterPlatz < 3) {
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(0).getName(), RegisterPlatz);
            Card1.setDisable(true);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(0).getName());
            nullCard[0] = true;
            CardsNames[0] = SaveClients.client.getHandcards().get(0).getName();
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    } */

}

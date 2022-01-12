import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import lombok.ToString;

import java.awt.*;
import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ChooseCards implements Initializable {
    int RegisterPlatz = -1;
    @FXML
    Label timelabel;
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
    Image Move2 = new Image("assets/Move2Blau.png");
    Image Move3 = new Image("assets/Move3Blau.png");
    Image PowerUp = new Image("assets/PowerUpBlau.png");
    Image RightTurn = new Image("assets/RightTurnBlau.png");
    Image UTurn = new Image("assets/UTurnBlau.png");
    Image LeftTurn = new Image ("assets/LeftTurnBlau.png");
    Image BackUp = new Image("assets/MoveBack.png");
    Image Again = new Image("assets/Again.png");




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
                return BackUp;
            case "PowerUp":
                return PowerUp;

            case "Again":
                return Again;
            default:
                return Move1;
        }
    }

    public void ChooseCard1(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(0).getName(), RegisterPlatz);
            Card1.setDisable(true);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(0).getName());
            Card1.setDisable(true);
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard2(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(1).getName(), RegisterPlatz);
            Card1.setDisable(true);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(1).getName());
            Card2.setDisable(true);
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard3(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(2).getName(), RegisterPlatz);
            Card1.setDisable(true);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(2).getName());
            Card3.setDisable(true);
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard4(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(3).getName(), RegisterPlatz);
            Card1.setDisable(true);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(3).getName());
            Card4.setDisable(true);
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard5(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(4).getName(), RegisterPlatz);
            Card1.setDisable(true);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(4).getName());
            Card5.setDisable(true);
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard6(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(5).getName(), RegisterPlatz);
            Card1.setDisable(true);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(5).getName());
            Card6.setDisable(true);
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard7(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(6).getName(), RegisterPlatz);
            Card1.setDisable(true);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(6).getName());
            Card7.setDisable(true);
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard8(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(7).getName(), RegisterPlatz);
            Card1.setDisable(true);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(7).getName());
            Card8.setDisable(true);
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard9(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(8).getName(), RegisterPlatz);
            Card1.setDisable(true);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(8).getName());
            Card9.setDisable(true);
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }

    public void OurTimer(){
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        Runnable runnable = new Runnable() {
            int countdown = 30;
            @Override
            public void run() {
                timelabel.setText(String.valueOf(countdown));
                countdown = countdown - 1;
                if (countdown < 0){

                    countdown = 0;
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.SECONDS);
    }

}

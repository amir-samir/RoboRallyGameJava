import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextFlow;
import javafx.util.Duration;
import lombok.ToString;

import java.awt.*;
import java.net.URL;
import java.sql.Array;
import java.sql.Time;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ChooseCards implements Initializable {
    int RegisterPlatz = -1;
    boolean[] nullCard = new boolean[9];
    String[] CardsNames = new String[9];

    private static final Integer BeginTimerSeconds = 30;
    //private Timeline timeline = new Timeline();
    private Integer currentSeconds = BeginTimerSeconds;
    @FXML
    Label TimerLabelFx;
    @FXML
    VBox TimerBox;
    @FXML
    TextFlow timerText;
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
        Arrays.fill(nullCard,false);


        //timerText.setText(currentSeconds.toString());
        //timerText.setBackground(Color.BLUE);

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
            nullCard[0] = true;
            CardsNames[0] = SaveClients.client.getHandcards().get(0).getName();
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard2(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(1).getName(), RegisterPlatz);
            Card2.setDisable(true);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(1).getName());
            nullCard[1] = true;
            CardsNames[1] = SaveClients.client.getHandcards().get(1).getName();
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard3(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(2).getName(), RegisterPlatz);
            Card3.setDisable(true);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(2).getName());
            nullCard[2] = true;
            CardsNames[2] = SaveClients.client.getHandcards().get(2).getName();
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard4(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(3).getName(), RegisterPlatz);
            Card4.setDisable(true);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(3).getName());
            nullCard[3] = true;
            CardsNames[3] = SaveClients.client.getHandcards().get(3).getName();
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard5(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(4).getName(), RegisterPlatz);
            Card5.setDisable(true);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(4).getName());
            nullCard[4] = true;
            CardsNames[4] = SaveClients.client.getHandcards().get(4).getName();
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard6(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(5).getName(), RegisterPlatz);
            Card6.setDisable(true);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(5).getName());
            nullCard[5] = true;
            CardsNames[5] = SaveClients.client.getHandcards().get(5).getName();
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard7(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(6).getName(), RegisterPlatz);
            Card7.setDisable(true);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(6).getName());
            nullCard[6] = true;
            CardsNames[6] = SaveClients.client.getHandcards().get(6).getName();
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard8(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(7).getName(), RegisterPlatz);
            Card8.setDisable(true);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(7).getName());
            nullCard[7] = true;
            CardsNames[7] = SaveClients.client.getHandcards().get(7).getName();
        }
        else {
            SaveClients.client.printMessage("Dein Register ist voll!");
        }
    }
    public void ChooseCard9(){
        if (RegisterPlatz < 4) {
            RegisterPlatz++;
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(8).getName(), RegisterPlatz);
            Card9.setDisable(true);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(8).getName());
            nullCard[8] = true;
            CardsNames[8] = SaveClients.client.getHandcards().get(8).getName();
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
                TimerLabelFx.setText(String.valueOf(countdown));
                countdown = countdown - 1;
                if (countdown < 0){

                    countdown = 0;
                }
            }
        };
        scheduler.scheduleAtFixedRate(runnable, 0, 5, TimeUnit.SECONDS);
    }

    public void startTimer(ActionEvent event){
        Timeline timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.getKeyFrames().add(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                currentSeconds--;
                TimerLabelFx.setText(currentSeconds.toString());

                        //(currentSeconds.toString());

                if (currentSeconds <= 0) {
                    timeline.stop();
                }
            }
        }));
    }



}

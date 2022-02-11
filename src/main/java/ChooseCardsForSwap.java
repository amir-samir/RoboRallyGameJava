import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Diese Klasse dient als Kontroller und Modell für die ChooseCardForSwap View
 *
 * @author Amir Azim
 * @author Dairen Gonschior
 * @author Mateo
 *
 * @version 2.1
 */
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

    boolean istSchonverschickt = false;
    int RegisterPlatz = 0;
    String[] cards = new String[3];
    int RegisterPlatzFor9 = 0;

    @FXML
    Label timerLabel;
    int time = 30;

    /**
     * set ChooseCardsForSwap für den Client
     */
    public ChooseCardsForSwap(){
        Client.setChooseCardsForSwap(this);
    }

    /**
     * initializiert die View von ChooseCardsForSwap
     */
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

    /**
     * gibt die Bilder für die Karten zurück
     * @param cardName Name von der Karte
     */
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

    /**
     * erste Karte auswählen
     */
   public void ChooseCard1(){
        if (istSchonverschickt && RegisterPlatzFor9 < 5){
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(0).getName(), RegisterPlatzFor9);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(0).getName());
            RegisterPlatzFor9++;
        }
        if (RegisterPlatz < 3) {
            //SaveClients.client.returnCards(SaveClients.client.getHandcards().get(0).getName(), RegisterPlatz);
            cards[RegisterPlatz] = SaveClients.client.getHandcards().get(0).getName();
            RegisterPlatz++;
            Card1.setDisable(true);
            Card1.setVisible(false);
        }
        else {
            if (!istSchonverschickt){
             SaveClients.client.returnCards(cards);
             istSchonverschickt = true;
            }
        }
    }

    /**
     * zweite Karte auswählen
     */
    public void ChooseCard2(){
        if (istSchonverschickt && RegisterPlatzFor9 < 5){
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(1).getName(), RegisterPlatzFor9);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(1).getName());
            RegisterPlatzFor9++;
        }
        if (RegisterPlatz < 3) {
            //SaveClients.client.returnCards(SaveClients.client.getHandcards().get(0).getName(), RegisterPlatz);
            cards[RegisterPlatz] = SaveClients.client.getHandcards().get(1).getName();
            RegisterPlatz++;
            Card2.setDisable(true);
            Card2.setVisible(false);
        }
        else {
            if (!istSchonverschickt){
                SaveClients.client.returnCards(cards);
                istSchonverschickt = true;
            }
        }
    }

    /**
     * dritte Karte auswählen
     */
    public void ChooseCard3(){
        if (istSchonverschickt && RegisterPlatzFor9 < 5){
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(2).getName(), RegisterPlatzFor9);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(2).getName());
            RegisterPlatzFor9++;
        }
        if (RegisterPlatz < 3) {
            //SaveClients.client.returnCards(SaveClients.client.getHandcards().get(0).getName(), RegisterPlatz);
            cards[RegisterPlatz] = SaveClients.client.getHandcards().get(2).getName();
            RegisterPlatz++;
            Card3.setDisable(true);
            Card3.setVisible(false);
        }
        else {
            if (!istSchonverschickt){
                SaveClients.client.returnCards(cards);
                istSchonverschickt = true;
            }
        }
    }

    /**
     * vierte Karte auswählen
     */
    public void ChooseCard4(){
        if (istSchonverschickt && RegisterPlatzFor9 < 5){
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(3).getName(), RegisterPlatzFor9);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(3).getName());
            RegisterPlatzFor9++;
        }
        if (RegisterPlatz < 3) {
            //SaveClients.client.returnCards(SaveClients.client.getHandcards().get(0).getName(), RegisterPlatz);
            cards[RegisterPlatz] = SaveClients.client.getHandcards().get(3).getName();
            RegisterPlatz++;
            Card4.setDisable(true);
            Card4.setVisible(false);
        }
        else {
            if (!istSchonverschickt){
                SaveClients.client.returnCards(cards);
                istSchonverschickt = true;
            }
        }
    }
    /**
     * fünfte Karte auswählen
     */
    public void ChooseCard5(){
        if (istSchonverschickt && RegisterPlatzFor9 < 5){
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(4).getName(), RegisterPlatzFor9);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(4).getName());
            RegisterPlatzFor9++;
        }
        if (RegisterPlatz < 3) {
            //SaveClients.client.returnCards(SaveClients.client.getHandcards().get(0).getName(), RegisterPlatz);
            cards[RegisterPlatz] = SaveClients.client.getHandcards().get(4).getName();
            RegisterPlatz++;
            Card5.setDisable(true);
            Card5.setVisible(false);
        }
        else {
            if (!istSchonverschickt){
                SaveClients.client.returnCards(cards);
                istSchonverschickt = true;
            }
        }
    }

    /**
     * sixte Karte auswählen
     */
    public void ChooseCard6(){
        if (istSchonverschickt && RegisterPlatzFor9 < 5){
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(5).getName(), RegisterPlatzFor9);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(5).getName());
            RegisterPlatzFor9++;
        }
        if (RegisterPlatz < 3) {
            //SaveClients.client.returnCards(SaveClients.client.getHandcards().get(0).getName(), RegisterPlatz);
            cards[RegisterPlatz] = SaveClients.client.getHandcards().get(5).getName();
            RegisterPlatz++;
            Card6.setDisable(true);
            Card6.setVisible(false);
        }
        else {
            if (!istSchonverschickt){
                SaveClients.client.returnCards(cards);
                istSchonverschickt = true;
            }
        }
    }

    /**
     * siebte Karte auswählen
     */
    public void ChooseCard7(){
        if (istSchonverschickt && RegisterPlatzFor9 < 5){
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(6).getName(), RegisterPlatzFor9);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(6).getName());
            RegisterPlatzFor9++;
        }
        if (RegisterPlatz < 3) {
            //SaveClients.client.returnCards(SaveClients.client.getHandcards().get(0).getName(), RegisterPlatz);
            cards[RegisterPlatz] = SaveClients.client.getHandcards().get(6).getName();
            RegisterPlatz++;
            Card7.setDisable(true);
            Card7.setVisible(false);
        }
        else {
            if (!istSchonverschickt){
                SaveClients.client.returnCards(cards);
                istSchonverschickt = true;
            }
        }
    }
    /**
     * achte Karte auswählen
     */
    public void ChooseCard8(){
        if (istSchonverschickt && RegisterPlatzFor9 < 5){
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(7).getName(), RegisterPlatzFor9);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(7).getName());
            RegisterPlatzFor9++;
        }
        if (RegisterPlatz < 3) {
            //SaveClients.client.returnCards(SaveClients.client.getHandcards().get(0).getName(), RegisterPlatz);
            cards[RegisterPlatz] = SaveClients.client.getHandcards().get(7).getName();
            RegisterPlatz++;
            Card8.setDisable(true);
            Card8.setVisible(false);
        }
        else {
            if (!istSchonverschickt){
                SaveClients.client.returnCards(cards);
                istSchonverschickt = true;
            }
        }
    }
    /**
     * neunte Karte auswählen
     */
    public void ChooseCard9(){
        if (istSchonverschickt && RegisterPlatzFor9 < 5){
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(8).getName(), RegisterPlatzFor9);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(8).getName());
            RegisterPlatzFor9++;
        }
        if (RegisterPlatz < 3) {
            //SaveClients.client.returnCards(SaveClients.client.getHandcards().get(0).getName(), RegisterPlatz);
            cards[RegisterPlatz] = SaveClients.client.getHandcards().get(8).getName();
            RegisterPlatz++;
            Card9.setDisable(true);
            Card9.setVisible(false);
        }
        else {
            if (!istSchonverschickt){
                SaveClients.client.returnCards(cards);
                istSchonverschickt = true;
            }
        }
    }
    /**
     * zehnte Karte auswählen
     */
    public void ChooseCard10(){
        if (istSchonverschickt && RegisterPlatzFor9 < 5){
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(9).getName(), RegisterPlatzFor9);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(9).getName());
            RegisterPlatzFor9++;
        }
        if (RegisterPlatz < 3) {
            //SaveClients.client.returnCards(SaveClients.client.getHandcards().get(0).getName(), RegisterPlatz);
            cards[RegisterPlatz] = SaveClients.client.getHandcards().get(9).getName();
            RegisterPlatz++;
            Card10.setDisable(true);
            Card10.setVisible(false);
        }
        else {
            if (!istSchonverschickt){
                SaveClients.client.returnCards(cards);
                istSchonverschickt = true;
            }
        }
    }
    /**
     * elfte Karte auswählen
     */
    public void ChooseCard11(){
        if (istSchonverschickt && RegisterPlatzFor9 < 5){
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(10).getName(), RegisterPlatzFor9);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(10).getName());
            RegisterPlatzFor9++;
        }
        if (RegisterPlatz < 3) {
            //SaveClients.client.returnCards(SaveClients.client.getHandcards().get(0).getName(), RegisterPlatz);
            cards[RegisterPlatz] = SaveClients.client.getHandcards().get(10).getName();
            RegisterPlatz++;
            Card11.setDisable(true);
            Card11.setVisible(false);
        }
        else {
            if (!istSchonverschickt){
                SaveClients.client.returnCards(cards);
                istSchonverschickt = true;
            }
        }
    }
    /**
     * zwölfte Karte auswählen
     */
    public void ChooseCard12(){
        if (istSchonverschickt && RegisterPlatzFor9 < 5){
            SaveClients.client.sendCardToRegister(SaveClients.client.getHandcards().get(11).getName(), RegisterPlatzFor9);
            SaveClients.client.setCardOfGui(SaveClients.client.getHandcards().get(11).getName());
            RegisterPlatzFor9++;
        }
        if (RegisterPlatz < 3) {
            //SaveClients.client.returnCards(SaveClients.client.getHandcards().get(0).getName(), RegisterPlatz);
            cards[RegisterPlatz] = SaveClients.client.getHandcards().get(11).getName();
            RegisterPlatz++;
            Card12.setDisable(true);
            Card12.setVisible(false);
        }
        else {
            if (!istSchonverschickt){
                SaveClients.client.returnCards(cards);
                istSchonverschickt = true;
            }
        }
    }

    /**
     * Timer starten
     */
    public void startTimer() {

        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {

                if(time > 0)
                {
                    Platform.runLater(() -> timerLabel.setText(Integer.toString(time)));
                    time--;
                    if(time < 6)
                    {
                        timerLabel.setTextFill(Color.web("red"));
                    }
                }
                else
                    timer.cancel();
            }
        }, 1000,1000);
        time = 30;
        timerLabel.setText("");
        timerLabel.setTextFill(Color.web("black"));
    }

}

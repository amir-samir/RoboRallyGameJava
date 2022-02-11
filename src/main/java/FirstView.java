import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

import lombok.Data;
/**
 * View Class of FirstView Window
 * @author Dairen Gonschior, Amir Azim
 */
@Data
public class FirstView implements Initializable {
   private Thread clientThread;
   private Client client;
   private List<Integer> choosenBots = new ArrayList<Integer>();

   Client client1;
   private int figure = 100;
   @FXML
   private ImageView roboRallyImageView;
   @FXML
   private ImageView smashBotImageView;
   @FXML
   private ImageView twinkyImageView;
   @FXML
   private ImageView hulkBotImageView;
   @FXML
   private ImageView hammerBotImageView;
   @FXML
   private ImageView spinBotImageView;
   @FXML
   private ImageView zoomBotImageView;
   @FXML
   private Label errorLabel;
   private int time = 30;
   @FXML
   private AnchorPane rootSignIn;
   @FXML
   private TextField insertUsername;
   @FXML
   private Button signInButton;
   @FXML
   private Label nameExists;
   private  GetID getID;
   private final FirstViewModel viewModel = new FirstViewModel();

   private final SignIn signIn = new SignIn();
   /**
    * Overrides the initialize class to show all necessary images and animations when the window starts
    */
   @Override
   @FXML
   public void initialize(URL arg0, ResourceBundle arg1)  {

      insertUsername.textProperty()
              .bindBidirectional(viewModel.getUsernameProperty());
      signInButton.defaultButtonProperty()
              .bindBidirectional(viewModel.getSignInButton());
      insertUsername.setOnKeyPressed( event -> {
         if( event.getCode() == KeyCode.ENTER ) {
            submitUserName();
         }
      } );

      FadeTransition fade = new FadeTransition();
      fade.setNode(roboRallyImageView);
      fade.setDuration(Duration.millis(3000));
      fade.setCycleCount(2);
      fade.setInterpolator(Interpolator.LINEAR);
      fade.setFromValue(0);
      fade.setToValue(1);
      fade.play();

      // translate: move the Photo from the Left Side tho the Right Side
      TranslateTransition translate = new TranslateTransition();
      translate.setNode(smashBotImageView);
      translate.setDuration(Duration.millis(5000));
      translate.setCycleCount(1);
      translate.setByX(420);
      translate.setAutoReverse(true);
      translate.play();

      // rotate: rotate the Photo Linear
      RotateTransition rotate = new RotateTransition();
      rotate.setNode(smashBotImageView);
      rotate.setDuration(Duration.millis(2000));
      rotate.setCycleCount(2);
      rotate.setInterpolator(Interpolator.LINEAR);
      rotate.setByAngle(360);
      rotate.play();

      // translate
      TranslateTransition translate1 = new TranslateTransition();
      translate1.setNode(twinkyImageView);
      translate1.setDuration(Duration.millis(5000));
      translate1.setCycleCount(1);
      translate1.setByX(420);
      translate1.setAutoReverse(true);
      translate1.play();

      // rotate: rotate the Photo Linear
      RotateTransition rotate1 = new RotateTransition();
      rotate1.setNode(twinkyImageView);
      rotate1.setDuration(Duration.millis(2000));
      rotate1.setCycleCount(2);
      rotate1.setInterpolator(Interpolator.LINEAR);
      rotate1.setByAngle(360);
      rotate1.play();

      // translate
      TranslateTransition translate2 = new TranslateTransition();
      translate2.setNode(hulkBotImageView);
      translate2.setDuration(Duration.millis(5000));
      translate2.setCycleCount(1);
      translate2.setByX(420);
      translate2.setAutoReverse(true);
      translate2.play();

      // rotate: rotate the Photo Linear
      RotateTransition rotate2 = new RotateTransition();
      rotate2.setNode(hulkBotImageView);
      rotate2.setDuration(Duration.millis(2000));
      rotate2.setCycleCount(2);
      rotate2.setInterpolator(Interpolator.LINEAR);
      rotate2.setByAngle(360);
      rotate2.play();

      // translate
      TranslateTransition translate3 = new TranslateTransition();
      translate3.setNode(hammerBotImageView);
      translate3.setDuration(Duration.millis(5000));
      translate3.setCycleCount(1);
      translate3.setByX(420);
      translate3.setAutoReverse(true);
      translate3.play();

      // rotate: rotate the Photo Linear
      RotateTransition rotate3 = new RotateTransition();
      rotate3.setNode(hammerBotImageView);
      rotate3.setDuration(Duration.millis(2000));
      rotate3.setCycleCount(2);
      rotate3.setInterpolator(Interpolator.LINEAR);
      rotate3.setByAngle(360);
      rotate3.play();

      // translate
      TranslateTransition translate4 = new TranslateTransition();
      translate4.setNode(spinBotImageView);
      translate4.setDuration(Duration.millis(5000));
      translate4.setCycleCount(1);
      translate4.setByX(420);
      translate4.setAutoReverse(true);
      translate4.play();

      // rotate: rotate the Photo Linear
      RotateTransition rotate4 = new RotateTransition();
      rotate4.setNode(spinBotImageView);
      rotate4.setDuration(Duration.millis(2000));
      rotate4.setCycleCount(2);
      rotate4.setInterpolator(Interpolator.LINEAR);
      rotate4.setByAngle(360);
      rotate4.play();

      // translate
      TranslateTransition translate5 = new TranslateTransition();
      translate5.setNode(zoomBotImageView);
      translate5.setDuration(Duration.millis(5000));
      translate5.setCycleCount(1);
      translate5.setByX(420);
      translate5.setAutoReverse(true);
      translate5.play();

      // rotate: rotate the Photo Linear
      RotateTransition rotate5 = new RotateTransition();
      rotate5.setNode(zoomBotImageView);
      rotate5.setDuration(Duration.millis(2000));
      rotate5.setCycleCount(2);
      rotate5.setInterpolator(Interpolator.LINEAR);
      rotate5.setByAngle(360);
      rotate5.play();
   }
   /**
    * Sets the firstview for the client
    */
   public FirstView(){
      Client.setFirstView(this);
   }
   /**
    * Reads robots.txt file and looks for choosen bots
    */
   public void readFile() {
      try {
         FileReader reader = new FileReader("robots.txt");
         int character;

         while ((character = reader.read()) != -1) {
            choosenBots.add(character);
            System.out.print((char) character);
            System.out.print((char) character);

         }
         reader.close();
      } catch (IOException e) {
         e.printStackTrace();
      }
   }
   /**
    * Writes the choosen bot to robots.txt file
    */
   public static void writeFile(int figure) {
      try {
         FileWriter writer = new FileWriter("robots.txt", true);
         writer.write(String.valueOf(figure));
         writer.close();
      } catch (IOException e) {
         e.printStackTrace();
      }

   }
   /**
    * Wählt den TwinkyBot aus und Disabled alle anderen Bots
    */
   public void TwinkyRoboterPressed(){

      readFile();
      if (choosenBots.contains(48)){
         Warning();
      }
      else {
         figure = 0;
         writeFile(figure);
         Server.setChoosenBots(figure);
         twinkyImageView.setDisable(true);
         smashBotImageView.setDisable(true);
         smashBotImageView.setVisible(false);
         hulkBotImageView.setDisable(true);
         hulkBotImageView.setVisible(false);
         zoomBotImageView.setDisable(true);
         zoomBotImageView.setVisible(false);
         spinBotImageView.setDisable(true);
         spinBotImageView.setVisible(false);
         hammerBotImageView.setDisable(true);
         hammerBotImageView.setVisible(false);
         SaveClients.client.figurenForGui.add(0);
         SaveClients.ausgewaehlteRoboter[0] = 0;
      }
   }
   /**
    * Wählt den SmashBot aus und Disabled alle anderen Bots
    */
   public void SmashRoboterPressed(){
      readFile();
      if (choosenBots.contains(49)){
         Warning();
      }
      else {
         figure = 1;
         writeFile(figure);
         smashBotImageView.setDisable(true);
         twinkyImageView.setDisable(true);
         twinkyImageView.setVisible(false);
         hulkBotImageView.setDisable(true);
         hulkBotImageView.setVisible(false);
         zoomBotImageView.setDisable(true);
         zoomBotImageView.setVisible(false);
         spinBotImageView.setDisable(true);
         spinBotImageView.setVisible(false);
         hammerBotImageView.setDisable(true);
         hammerBotImageView.setVisible(false);
         SaveClients.client.figurenForGui.add(1);
         SaveClients.ausgewaehlteRoboter[1] = 1;
      }
   }
   /**
    * Wählt den Hulkbot aus und Disabled alle anderen Bots
    */
   public void HulkRoboterPressed(){
      readFile();
      if (choosenBots.contains(50)){
         Warning();
      }
      else {
         figure = 2;
         writeFile(figure);
         twinkyImageView.setDisable(true);
         twinkyImageView.setVisible(false);
         smashBotImageView.setDisable(true);
         smashBotImageView.setVisible(false);
         hulkBotImageView.setDisable(true);
         zoomBotImageView.setDisable(true);
         zoomBotImageView.setVisible(false);
         spinBotImageView.setDisable(true);
         spinBotImageView.setVisible(false);
         hammerBotImageView.setDisable(true);
         hammerBotImageView.setVisible(false);
         SaveClients.client.figurenForGui.add(2);
         SaveClients.ausgewaehlteRoboter[2] = 2;
      }
   }
   /**
    * Wählt den ZoomBots aus und Disabled alle anderen Bots
    */
   public void ZoomRoboterPressed() {
      readFile();
      if (choosenBots.contains(51)) {
         Warning();
      } else {
         figure = 3;
         writeFile(figure);
         twinkyImageView.setDisable(true);
         twinkyImageView.setVisible(false);
         smashBotImageView.setDisable(true);
         smashBotImageView.setVisible(false);
         hulkBotImageView.setDisable(true);
         hulkBotImageView.setVisible(false);
         zoomBotImageView.setDisable(true);
         spinBotImageView.setDisable(true);
         spinBotImageView.setVisible(false);
         hammerBotImageView.setDisable(true);
         hammerBotImageView.setVisible(false);
         SaveClients.client.figurenForGui.add(3);
         SaveClients.ausgewaehlteRoboter[3] = 3;
      }
   }
   /**
    * Wählt den SpinBot aus und Disabled alle anderen Bots
    */
   public void SpinRoboterPressed() {
      readFile();
      if (choosenBots.contains(52)) {
         Warning();
      } else {
         figure = 4;
         writeFile(figure);
         twinkyImageView.setDisable(true);
         twinkyImageView.setVisible(false);
         smashBotImageView.setDisable(true);
         smashBotImageView.setVisible(false);
         hulkBotImageView.setDisable(true);
         hulkBotImageView.setVisible(false);
         zoomBotImageView.setDisable(true);
         zoomBotImageView.setVisible(false);
         spinBotImageView.setDisable(true);
         hammerBotImageView.setDisable(true);
         hammerBotImageView.setVisible(false);
         SaveClients.client.figurenForGui.add(4);
         SaveClients.ausgewaehlteRoboter[4] = 4;
      }
   }
   /**
    * Wählt den HammerBot aus und Disabled alle anderen Bots
    */
   public void HammerRoboterPressed() {
      readFile();
      if (choosenBots.contains(53)) {
         Warning();
      } else {
         figure = 5;
         writeFile(figure);
         twinkyImageView.setDisable(true);
         twinkyImageView.setVisible(false);
         smashBotImageView.setDisable(true);
         smashBotImageView.setVisible(false);
         hulkBotImageView.setDisable(true);
         hulkBotImageView.setVisible(false);
         zoomBotImageView.setDisable(true);
         zoomBotImageView.setVisible(false);
         spinBotImageView.setDisable(true);
         spinBotImageView.setVisible(false);
         hammerBotImageView.setDisable(true);
         SaveClients.client.figurenForGui.add(5);
         SaveClients.ausgewaehlteRoboter[5] = 5;
      }
   }

   /**
    * Getter für figure
    */
   public int getFigure(){
      return figure;
   }

   /**
    * Diese Funktion wird nicht verwendet
    */
   public void chooseUserName() throws IOException {
      viewModel.chooseUsername();
   }
   /**
    * Setter für Client
    */
   public void setClient(Client client){
      this.client = client;
   }


   /**
    * Speichert den ausgewählten Usernamen und Bot und versendet die PlayerValues Message im Client
    */
   public void submitUserName() {

      try {
         if (viewModel.getUsername() != null && getFigure() != 100) {
            setClient(SaveClients.client);
            SaveClients.client.configuration(viewModel.getUsername(), getFigure());
            //Passing the current stage to the ViewModel
            Stage stage = (Stage) signInButton.getScene().getWindow();
            viewModel.takeUsername(stage);
         } else {
            WarningUserName();
         }

      } catch (IOException e) {
        e.printStackTrace();
      }
   }
   /**
    * Diese Funktion nicht verwendet
    */
   public void setunvisible(int[] figuren){
      for (int i = 0; i < figuren.length; i++){
         switch (figuren[i]){
            case 0:
               twinkyImageView.setVisible(false);
               break;
            case 1:
               smashBotImageView.setVisible(false);
               break;
            case 2:
               hulkBotImageView.setVisible(false);
               break;
            case 3:
               zoomBotImageView.setVisible(false);
               break;
            case 4:
               spinBotImageView.setVisible(false);
               break;
            case 5:
               hammerBotImageView.setVisible(false);
               break;
            case 6:
               return;
         }
      }
   }

   /**
    * Gibt eine alertBox aus, wenn ein Roboter gewählt wird, der bereits vergeben ist
    */
   @FXML
   private void Warning(){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Fehler bei der Roboter Auswahl");
      alert.setContentText("Wähle bitte einen anderen Roboter aus.");
      alert.setHeaderText("Roboter ist vergeben!");
      alert.showAndWait();
   }
   /**
    * Gibt eine alertBox aus, wenn entweder Username oder Bot nicht ausgewählt wurden und ein sign in versucht wird
    */
   @FXML
   private void WarningUserName(){
      Alert alert = new Alert(Alert.AlertType.WARNING);
      alert.setTitle("Nicht alle Pflichtfelder wurden ausgefüllt");
      alert.setContentText("Bitte wähle einen Roboter und einen Username.");
      alert.setHeaderText("Roboter oder Username fehlt");
      alert.showAndWait();
   }





}

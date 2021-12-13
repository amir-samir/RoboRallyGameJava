import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.animation.RotateTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import Messages.*;


public class FirstView implements Initializable {
   private Thread clientThread;
   private Client client;

   Client client1;
   private int figure;
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







   }

   public void TwinkyRoboterPressed(){
      figure = 0;
      System.out.println("funktioniert");
      System.out.println(figure);
      twinkyImageView.setDisable(true);
      twinkyImageView.setVisible(false);
   }
   public void SmashRoboterPressed(){
      figure = 1;
      System.out.println("funktioniert");
      System.out.println(figure);
      smashBotImageView.setDisable(true);
      smashBotImageView.setVisible(false);
   }
   public void HulkRoboterPressed(){
      figure = 2;
      System.out.println("funktioniert");
      System.out.println(figure);
      hulkBotImageView.setDisable(true);
      hulkBotImageView.setVisible(false);
   }
   public void ZoomRoboterPressed(){
      figure = 3;
      System.out.println("funktioniert");
      System.out.println(figure);
      zoomBotImageView.setDisable(true);
      zoomBotImageView.setVisible(false);
   }
   public void SpinRoboterPressed(){
      figure = 4;
      System.out.println("funktioniert");
      System.out.println(figure);
      spinBotImageView.setDisable(true);
      spinBotImageView.setVisible(false);
   }
   public void HammerRoboterPressed(){
      figure = 5;
      System.out.println("funktioniert");
      System.out.println(figure);
      hammerBotImageView.setDisable(true);
      hammerBotImageView.setVisible(false);
   }
   public int getFigure(){
      return figure;
   }




   public void chooseUserName() throws IOException {
      viewModel.chooseUsername();
      // Fade: Hide and Show the Title Photo
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

   public void setClient(Client client){
      this.client = client;
      this.clientThread = new Thread(client);
      clientThread.start();
   }



   public void submitUserName() {

      try {

         setClient(SaveClients.client);
         SaveClients.client.configuration(viewModel.getUsername(), getFigure());
         //viewModel.client1.configuration(viewModel.getUsername(), getFigure());
         System.out.println(viewModel.getUsername() + getFigure());
         //Passing the current stage to the ViewModel
         Stage stage = (Stage) signInButton.getScene().getWindow();
         viewModel.takeUsername(stage);

         //Client-Constructor throws DuplicateNameException if name already taken
      } catch (IOException e) {
        e.printStackTrace();
      }
   }


}

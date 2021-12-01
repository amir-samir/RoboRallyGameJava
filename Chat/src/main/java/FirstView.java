package main.java;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class FirstView {

   @FXML
   private AnchorPane rootSignIn;
   @FXML
   private TextField insertUsername;
   @FXML
   private Button signInButton;
   @FXML
   private Label nameExists;

   @FXML
   ImageView imageView = new ImageView();



   private final FirstViewModel viewModel = new FirstViewModel();

   private final SignIn signIn = new SignIn();



   @FXML


   void initialize()  {


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



   public void submitUserName() {

      try {
         //Passing the current stage to the ViewModel
         Stage stage = (Stage) signInButton.getScene().getWindow();
         viewModel.takeUsername(stage);

         //Client-Constructor throws DuplicateNameException if name already taken
      } catch (IOException e) {
        e.printStackTrace();
      }
   }

   @FXML

   /**
    * Hint label / notification can only be seen if userName is already taken
    */
   public void hideNameTakenLabel() {
      nameExists.setVisible(false);
   }

}

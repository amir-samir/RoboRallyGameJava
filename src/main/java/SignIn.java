import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SignIn extends Application {

    public static void main(String[] args){
     Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
      try {
          stage.setTitle("Sign In");
          Parent signIn = FXMLLoader.load(getClass().getResource("fxml/MaybeMap.fxml"));
          Scene signInScene = new Scene(signIn);
          stage.setScene(signInScene);
          stage.show();
          stage.setOnCloseRequest(e -> Platform.exit());
      } catch (IOException e){
          e.printStackTrace();
      }
    }
}

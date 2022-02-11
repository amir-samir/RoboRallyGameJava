import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
/**
 * Model Class of GetID Window
 * @author Amir Azim, Dairen Gonschior
 */

public class GetID extends Application {
    private Thread clientThread;

    public Client client2;
    public Stage stageGetID;

    /**
     * Launches the Application
     */
    public static void main(String[] args){

        Application.launch(args);
    }
    /**
     * Überschreibt die Start Funktion von Application, öffnet das GetID Fenster
     */
    @Override
    public void start(Stage stage) throws Exception {
        try {
            SaveClients.client = Client.getClient();
            stage.setTitle("Get Id");
            StageSaver.getStageSaver().setStageSaver(stage);
            Parent getID = FXMLLoader.load(getClass().getResource("fxml/GetID.fxml"));
            Scene getIdScene = new Scene(getID);
            stage.setScene(getIdScene);
            stage.show();
            stage.setOnCloseRequest(e -> stage.close());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Starts the firstView Window and closes the GetID Window
     */
    public void GetIDPressed() throws IOException {
        StageSaver.getStageSaver().getCurrentStage().close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/FirstView.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage1 = new Stage();
        stage1.setScene(scene);

        FirstView chatView = loader.getController();
        //chatView.setClient(client1);
        stage1.show();


        //Close if Bye
        stage1.setOnCloseRequest(e -> {
            Platform.exit();
            //clientHandler.writer.equals("bye");
            stage1.close();
            System.exit(0);
        });




    }

}

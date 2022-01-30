import com.sun.tools.javac.Main;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class GetID extends Application {
    private Thread clientThread;

    public Client client2;
    public Stage stageGetID;




    public static void main(String[] args){

        Application.launch(args);
    }

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


    public void GetIDPressed() throws IOException {
        StageSaver.getStageSaver().getCurrentStage().close();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/FirstView.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage1 = new Stage();
        stage1.setScene(scene);
        scene.getStylesheets().add("fxml/SignInStyle.css");

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

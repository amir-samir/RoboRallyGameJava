import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class GetID extends Application {
    private Thread clientThread;

    public Client client2;

    public static void main(String[] args){
        Application.launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        try {
            Client client = new Client();
            this.clientThread = new Thread(client);
            clientThread.start();
            SaveClients.client = client;
            stage.setTitle("Get Id");
            Parent getID = FXMLLoader.load(getClass().getResource("GetID.fxml"));
            Scene getIdScene = new Scene(getID);
            stage.setScene(getIdScene);
            stage.show();
            stage.setOnCloseRequest(e -> Platform.exit());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void GetIDPressed() throws IOException {
        //Client client = new Client();
        //SaveClients.client = client;
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FirstView.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = new Stage();
        stage.setScene(scene);
        scene.getStylesheets().add("SignInStyle.css");

        FirstView chatView = loader.getController();
        //chatView.setClient(client1);
        stage.show();


        //Close if Bye
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            //clientHandler.writer.equals("bye");
            stage.close();
            System.exit(0);
        });




    }

}

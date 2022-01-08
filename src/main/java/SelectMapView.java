import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectMapView {

    public void ChooseExtraCrispyMap() throws IOException {
        Stage stage = new Stage();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("fxml/MaybeMap.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.show();

        //Close if Bye
        stage.setOnCloseRequest(e -> {
            Platform.exit();
            //clientHandler.writer.equals("bye");
            stage.close();
            System.exit(0);
        });
    }

    public void ChooseDizzyHighwayMap(){
        SaveClients.client.mapSelected("DizzyHighwayMap");
    }
    public void ChooseDeathTrapMap(){
        SaveClients.client.mapSelected("DeathTrapMap");
    }
    public void ChooseExtraCrispymap(){
        SaveClients.client.mapSelected("ExtraCrispyMap");
    }
    public void ChooseLostBearingMap(){
        SaveClients.client.mapSelected("LostBearingMap");
    }

}

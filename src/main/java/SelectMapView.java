import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SelectMapView {
public SelectMapView(){
    Client.setSelectMapView(this);
}

    public void ChooseExtraCrispyMap() throws IOException {
       SaveClients.client.mapSelected("ExtraCrispy");
       StageSaver.getStageSaver().getCurrentStage().close();
    }

    public void ChooseDizzyHighwayMap(){
        SaveClients.client.mapSelected("DizzyHighway");
        StageSaver.getStageSaver().getCurrentStage().close();
    }
    public void ChooseDeathTrapMap(){
        SaveClients.client.mapSelected("DeathTrap");
        StageSaver.getStageSaver().getCurrentStage().close();
    }
    public void ChooseLostBearingMap(){
        SaveClients.client.mapSelected("LostBearing");
        StageSaver.getStageSaver().getCurrentStage().close();
    }

    public void RunMap() throws Exception{
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

}

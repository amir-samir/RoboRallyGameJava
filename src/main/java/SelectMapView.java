import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Diese Klasse dient als Kontroller und Modell für die SelectMap View
 *
 * @author Amir Azim
 * @author Dairen Gonschior
 * @author Mateo
 *
 * @version 2.1
 */
public class SelectMapView {

    public SelectMapView() {
        Client.setSelectMapView(this);
    }

    /**
     * Extra Crispy Map auswählen
     */
    public void ChooseExtraCrispyMap() throws IOException {
        SaveClients.client.mapSelected("ExtraCrispy");
        StageSaver.getStageSaver().getCurrentStage().close();
    }

    /**
     * Dizzy Highway Map auswählen
     */
    public void ChooseDizzyHighwayMap() {
        SaveClients.client.mapSelected("DizzyHighway");
        StageSaver.getStageSaver().getCurrentStage().close();
    }

/**
     *
     * Death Trap Map auswählen
     */    public void ChooseDeathTrapMap() {
        SaveClients.client.mapSelected("DeathTrap");
        StageSaver.getStageSaver().getCurrentStage().close();
    }

/**
     * Lost Bearings Map auswählen
     */    public void ChooseLostBearingMap() {
        SaveClients.client.mapSelected("LostBearings");
        StageSaver.getStageSaver().getCurrentStage().close();
    }

/**
     * Twister Map auswählen
     */    public void ChooseTwisterMap() {
        SaveClients.client.mapSelected("Twister");
        StageSaver.getStageSaver().getCurrentStage().close();
    }
}

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Diese Klasse dient als Kontroller und Modell für die GameFinished View
 *
 * @author Amir Azim
 * @author Dairen Gonschior
 * @author Mateo
 *
 * @Version: 2.1
 */

public class GameFinishedController implements Initializable {
    @FXML
    ImageView gewonnenBild;

    Image hulkGewonnen = new Image("assets/gameFinishedHulk.gif");
    Image smashGewonnen = new Image("assets/gewonnenSmash.gif");
    Image zoomGewonnen = new Image("assets/gewonnenZoom.gif");
    Image twinkyGewonnen = new Image("assets/gewonnenTwinky.gif");
    Image spinGewonnen = new Image("assets/gewonnenSpin.gif");
    Image hammerGewonnen = new Image("assets/gewonnenHammer.gif");
    Image loser = new Image("assets/loser.gif");

    /**
     * initialisiert die View von Game Finished
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (SaveClients.client.getIstGewinner()) {
            if (SaveClients.client.getRobterGewonnen() == 0) {
                gewonnenBild.setImage(twinkyGewonnen);
            }
            if (SaveClients.client.getRobterGewonnen() == 1) {
                gewonnenBild.setImage(smashGewonnen);
            }
            if (SaveClients.client.getRobterGewonnen() == 2) {
                gewonnenBild.setImage(hulkGewonnen);
            }
            if (SaveClients.client.getRobterGewonnen() == 3) {
                gewonnenBild.setImage(zoomGewonnen);
            }
            if (SaveClients.client.getRobterGewonnen() == 4) {
                gewonnenBild.setImage(spinGewonnen);
            }
            if (SaveClients.client.getRobterGewonnen() == 5) {
                gewonnenBild.setImage(hammerGewonnen);
            }
        }
        else{
            gewonnenBild.setImage(loser);
        }

    }
}

import javafx.fxml.Initializable;
import javafx.scene.image.Image;

import java.net.URL;
import java.util.ResourceBundle;


/**
 *  Wenn die DamageKarten leer sind, dann wird dieses Fenster gerufen.
 *
 * @author Amir Azim
 * @author Dairen Gonschior
 *
 *
 *
 */
public class SpamExtraViewController implements Initializable {
    Image spam = new Image("assets/spam.png");
    Image trojanHorse = new Image("assets/trojanHorse.png");
    Image virus = new Image("assets/virus.png");
    Image worm = new Image("assets/worm.png");
    String[] cards = new String[1];
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
    /**
     * Die erste Kategorie verschicken
     */
    public void addToArray1(){
        cards[0] = "Spam";
        SaveClients.client.sendSelectedDamage(cards);
    }
    /**
     * Die zweite Kategorie verschicken
     */
    public void addToArray2(){
        cards[0] = "Trojan";
        SaveClients.client.sendSelectedDamage(cards);
    }
    /**
     * Die dritte Kategorie verschicken
     */
    public void addToArray3(){
        cards[0] = "virus";
        SaveClients.client.sendSelectedDamage(cards);
    }
    /**
     * Die vierte Kategorie verschicken
     */
    public void addToArray4(){
        cards[0] = "worm";
        SaveClients.client.sendSelectedDamage(cards);
    }

}

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
/**
 * Controller Class of UpgradeCards Window
 * @author Dairen Gonschior, Amir Azim
 */

public class UpgradeCardsController implements Initializable {

    @FXML
    ImageView Card1;
    @FXML
    ImageView Card2;
    @FXML
    ImageView Card3;
    @FXML
    ImageView Card4;
    @FXML
    ImageView Card5;
    @FXML
    ImageView Card6;
    @FXML
    Label CubesText;



    Image AdminPrivilege = new Image("assets/AdminPrivilege.png");
    Image RearLaser = new Image("assets/RearLaser.png");
    Image MemorySwap = new Image("assets/MemorySwap.png");
    Image SpamBlocker = new Image("assets/SpamBlocker.png");

    String[] upgradeCardsName = new String[6];


    /**
     * Override of the initialize function, to display the right amount of cards and energy cubes
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        for (int i = 0; i < SaveClients.client.getUpgradeShop().size(); i++){
            upgradeCardsName[i] = SaveClients.client.getUpgradeShop().get(i);
        }
        setDisabled();
        setCards(SaveClients.client.getUpgradeShop());
        CubesText.setText("Energy cubes: " + SaveClients.client.getCubesZahl());
    }
    /**
     * Sets the Images of the Cards
     */
    public void setCards(ArrayList<String> upgradecards){
        for (int i = 0; i < upgradecards.size(); i++){
            getCardView(i).setDisable(false);
            getCardView(i).setImage(getImageForCard(upgradecards.get(i)));
        }
    }
    /**
     * Returns the right imageviwe for the given card ID
     * @param k The Card ID
     */
    public ImageView getCardView(int k){
        switch (k){
            case 0 :
                return Card1;
            case 1:
                return Card2;
            case 2:
                return Card3;
            case 3:
                return Card4;
            case 4:
                return Card5;
            case 5:
                return Card6;
            default:
                return Card1;
        }
    }
    /**
     * Gets the right image for the given Card name
     * @param cardImage Cardname as String
     */
    public Image getImageForCard(String cardImage){
        switch (cardImage){
            case "AdminPrivilege" :
                return AdminPrivilege;
            case "RearLaser" :
                return RearLaser;
            case "MemorySwap" :
                return MemorySwap;
            case  "SpamBlocker" :
                return SpamBlocker;
            default:
                return SpamBlocker;
        }
    }
    /**
     * Saves Upgradecard which was bought in the Client
     */
    public void Card1Pressed(){
        SaveClients.client.buyUpgrade(true, upgradeCardsName[0]);
    }

    /**
     * Saves Upgradecard which was bought in the Client
     */
    public void Card2Pressed(){
        SaveClients.client.buyUpgrade(true, upgradeCardsName[1]);
    }
    /**
     * Saves Upgradecard which was bought in the Client
     */
    public void Card3Pressed(){
        SaveClients.client.buyUpgrade(true,  upgradeCardsName[2]);
    }
    /**
     * Saves Upgradecard which was bought in the Client
     */
    public void Card4Pressed(){
        SaveClients.client.buyUpgrade(true, upgradeCardsName[3]);
    }
    /**
     * Saves Upgradecard which was bought in the Client
     */
    public void Card5Pressed(){
        SaveClients.client.buyUpgrade(true, upgradeCardsName[4]);
    }
    /**
     * Saves Upgradecard which was bought in the Client
     */
    public void Card6Pressed(){
        SaveClients.client.buyUpgrade(true, upgradeCardsName[5]);
    }
    /**
     * Saves Upgradecard which was bought in the Client
     */
    public void NoCardChoossen(){
        SaveClients.client.buyUpgrade(false, null);
    }

    /**
     * Disables all CardSlots
     */
    public void setDisabled(){
        Card1.setDisable(true);
        Card2.setDisable(true);
        Card3.setDisable(true);
        Card4.setDisable(true);
        Card5.setDisable(true);
        Card6.setDisable(true);

    }

}

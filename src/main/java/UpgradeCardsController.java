import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setCards(SaveClients.client.getUpgradeShop());
        CubesText.setText("Energy cubes: " + SaveClients.client.getCubesZahl());
    }

    public void setCards(ArrayList<String> upgradecards){
        for (int i = 0; i < upgradecards.size(); i++){
            getCardView(i).setImage(getImageForCard(upgradecards.get(i)));
        }
    }

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

    public void Card1Pressed(){
        SaveClients.client.buyUpgrade(true, SaveClients.client.getUpgradeShop().get(0));
    }

    public void Card2Pressed(){
        SaveClients.client.buyUpgrade(true, SaveClients.client.getUpgradeShop().get(1));
    }

    public void Card3Pressed(){
        SaveClients.client.buyUpgrade(true, SaveClients.client.getUpgradeShop().get(2));
    }

    public void Card4Pressed(){
        SaveClients.client.buyUpgrade(true, SaveClients.client.getUpgradeShop().get(3));
    }

    public void Card5Pressed(){
        SaveClients.client.buyUpgrade(true, SaveClients.client.getUpgradeShop().get(4));
    }

    public void Card6Pressed(){
        SaveClients.client.buyUpgrade(true, SaveClients.client.getUpgradeShop().get(5));
    }

    public void NoCardChoossen(){
        SaveClients.client.buyUpgrade(false, null);
    }

}

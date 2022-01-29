import java.util.ArrayList;

public class UpgradeShop {

    private UpgradeCards[] upgradeCards;
    private boolean somebodyBoughtOne;

    public UpgradeShop(int playerCount) {
        upgradeCards = new UpgradeCards[playerCount];
        somebodyBoughtOne = false;
    }

    public ArrayList<UpgradeCards> exchangeShop(ArrayList<UpgradeCards> listWithCards){
        for (int i = 0; i < upgradeCards.length; i++){
            upgradeCards[i] = listWithCards.remove(0);
        }
        return listWithCards;
    }

    public boolean isSomebodyBoughtOne() {
        return somebodyBoughtOne;
    }

    public UpgradeCards[] getUpgradeCards() {
        return upgradeCards;
    }

    public void setUpgradeCards(UpgradeCards[] upgradeCards) {
        this.upgradeCards = upgradeCards;
    }

    public void setSomebodyBoughtOne(boolean somebodyBoughtOne) {
        this.somebodyBoughtOne = somebodyBoughtOne;
    }
}

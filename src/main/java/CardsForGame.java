import java.util.ArrayList;
import java.util.Collections;

public class CardsForGame {

    ArrayList<Cards> spamCards = new ArrayList<>();
    ArrayList<Cards> virusCards = new ArrayList<>();
    ArrayList<Cards> wormCards = new ArrayList<>();
    ArrayList<Cards> trojanHorse = new ArrayList<>();
    ArrayList<UpgradeCards> upgradeCards = new ArrayList<>();

    public CardsForGame(){
        initSpamCard();
        initTrojanCard();
        initWormCard();
        initVirusCard();
        initUpgradeCards();
    }

    public void initSpamCard() {
        for (int i = 0; i < 38; i++) {
            spamCards.add(new Spam());
        }
    }

    public void initVirusCard() {
        for (int i = 0; i < 18; i++) {
            virusCards.add(new VirusCard());
        }
    }

    public void initWormCard() {
        for (int i = 0; i < 6; i++) {
            wormCards.add(new Worm());
        }
    }

    public void initTrojanCard() {
        for (int i = 0; i < 12; i++) {
            trojanHorse.add(new Trojan());
        }
    }

    public void initUpgradeCards(){
        for (int i = 0; i < 10; i++){
            upgradeCards.add(new AdminPrivilege());
            upgradeCards.add(new RearLaser());
            upgradeCards.add(new MemorySwap());
            upgradeCards.add(new SpamBlocker());
        }
        Collections.shuffle(this.upgradeCards);
        Collections.shuffle(this.upgradeCards);
        Collections.shuffle(this.upgradeCards);
    }
}

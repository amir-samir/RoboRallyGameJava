import java.util.ArrayList;
import java.util.Collections;

/**
 * Diese Klasse repräsentiert alle Kartenstapel, die zentral vom Spiel verwaltet werden.
 *
 * @author Chen Zhaohang
 * @author Luca Weyhofen
 *
 * @version 2.1
 */
public class CardsForGame {

    ArrayList<Cards> spamCards = new ArrayList<>();
    ArrayList<Cards> virusCards = new ArrayList<>();
    ArrayList<Cards> wormCards = new ArrayList<>();
    ArrayList<Cards> trojanHorse = new ArrayList<>();
    ArrayList<UpgradeCards> upgradeCards = new ArrayList<>();

    /**
     * Das ist der Konstruktor.
     * Hier werden alle Kartenstapel initialisiert.
     */
    public CardsForGame(){
        initSpamCard();
        initTrojanCard();
        initWormCard();
        initVirusCard();
        initUpgradeCards();
    }

    /**
     * Die Spam-Karten werden erstellt.
     */
    public void initSpamCard() {
        for (int i = 0; i < 38; i++) {
            spamCards.add(new Spam());
        }
    }

    /**
     * Die Virus-Karten werden erstellt.
     */
    public void initVirusCard() {
        for (int i = 0; i < 18; i++) {
            virusCards.add(new VirusCard());
        }
    }

    /**
     * Die Wurm-Karten werden erstellt.
     */
    public void initWormCard() {
        for (int i = 0; i < 6; i++) {
            wormCards.add(new Worm());
        }
    }

    /**
     * Die Trojaner-Karten werden erstellt.
     */
    public void initTrojanCard() {
        for (int i = 0; i < 12; i++) {
            trojanHorse.add(new Trojan());
        }
    }

    /**
     * Die Upgrade-Karten werden erstellt.
     */
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

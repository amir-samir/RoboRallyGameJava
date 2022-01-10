import java.util.ArrayList;

public class CardsForGame {

    ArrayList<Cards> spamCards = new ArrayList<>();
    ArrayList<Cards> virusCards = new ArrayList<>();
    ArrayList<Cards> wormCards = new ArrayList<>();
    ArrayList<Cards> trojanHorse = new ArrayList<>();

    public CardsForGame(){
        initSpamCard();
        initTrojanCard();
        initWormCard();
        initVirusCard();
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
}

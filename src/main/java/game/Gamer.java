package game;

import game.Card.Cards;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author:Yilu
 **/

@Data
public class Gamer {
    private int gamerID;
    private Game game;
    private Robot robot;
    private List<Cards> register;
    private int registerCount = 0;
    private Boolean inRoboting = false;
    private List<Cards> cards;
    private List<Cards> handCards;
    private List<Cards> playedCards;
    private List<Integer> points;
    private int energyCubes = 0;
    ArrayList<Cards> programmingCardDeck = new ArrayList<>();

    public void reboot() {
        this.inRoboting = true;
        this.handCards.addAll(game.getTwoSpamCards());
        clearCards();
    }

    /**
     *
    public void boot() {
        this.inRebooting = false;
    }

     */


    public void clearCards() {
        this.playedCards.addAll(this.handCards);
        this.handCards.clear();
    }

    public void pushCard(Cards cards) {
        this.handCards.add(cards);
    }


    public void getNewEnergycube() {
        energyCubes++;
    }



}

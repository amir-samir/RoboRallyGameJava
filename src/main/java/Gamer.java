import game.Card.Cards;
import game.Robot;
import lombok.Data;
import java.util.List;

/**
 * @author:Yilu
 **/

@Data
public class Gamer {
    private Game game;
    private Robot robot;
    private Boolean inRoboting = false;
    private List<Cards> cards;
    private List<Cards> handCards;
    private List<Cards> playedCards;
    private List<Integer> points;

    public void reboot() {
        this.inRoboting = true;
        this.handCards.addAll(game.getTwoDamageCards());
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
}

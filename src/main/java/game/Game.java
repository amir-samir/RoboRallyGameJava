package game;

import game.Card.DamageCards;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:Yilu
 *
 */
@Data

public class Game {
    private List<DamageCards> damageCards;

    public List<DamageCards> getTwoDamageCards() {
        List<DamageCards> damageCards = new ArrayList<>();
        return damageCards;
    }
}


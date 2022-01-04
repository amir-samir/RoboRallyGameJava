package game;
import game.Card.Spam;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * @author:Yilu
 *
 */
@Data

public class Game {
    private Gamer gamer;
    private Robot robot;
    private List<Spam> spams;

    public List<Spam> getTwoSpamCards() {
        List<Spam> spams = new ArrayList<>();
        return spams;
    }

}


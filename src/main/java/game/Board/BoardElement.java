package game.Board;
import game.Gamer;
import lombok.Data;

/**
 * @author yiluye
 */


public abstract class BoardElement {
    private String type;
    private String isOnBoard;
    public abstract void effect(Gamer gamer);
}

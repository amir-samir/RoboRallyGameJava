package game.Board;
import game.Gamer;

public class CheckPoint extends BoardElement {

    int count;

    public void CheckPoint(String isOnBoard, int count) {
        this.setType("CheckPoint");
        this.setIsOnBoard(isOnBoard);
        this.count = count;
    }

    @Override
    public void effect(Gamer gamer) {
        gamer.getPoints().add(this.count);
    }

}


package game.Card;
import game.Robot;

public class Move1Card extends Cards {
    final String description = "Cards.Move1Card moves your robot in the direction it is facing by 1";

    public Move1Card(){
        this.setName("MoveI");
    }

@Override
    public void effect(){
        //gamer.getRobot().forward(1);
    }
}

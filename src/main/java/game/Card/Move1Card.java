package game.Card;

import game.Robot;

public class Move1Card extends Cards {
    final String description = "Cards.Move1Card moves your robot in the direction it is facing by 1";
    final String name = "Move1";


    public void effect(){
        Robot robot = new Robot();
        robot.forward(1);
    }

    @Override
    public String getName() {
        return name;
    }
}

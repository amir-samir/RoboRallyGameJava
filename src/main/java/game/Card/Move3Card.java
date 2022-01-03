package game.Card;

import game.Robot;

public class Move3Card extends Cards {
    final String description = "Cards.Move3Card moves your robot in the direction it is facing by 3";
    final String name = "Move3";


    public void effect(){
        Robot robot = new Robot();
        robot.forward(3);
    }

    @Override
    public String getName() {
        return name;
    }
}
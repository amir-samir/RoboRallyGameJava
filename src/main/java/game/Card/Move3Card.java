package game.Card;

import game.Robot;

public class Move3Card extends Cards {
    final String description = "Cards.Move3Card moves your robot in the direction it is facing by 3";
    final String name = "Move3";

@Override
public void effect(){
    //gamer.getRobot().forward(1);
}

    @Override
    public String getName() {
        return name;
    }
}
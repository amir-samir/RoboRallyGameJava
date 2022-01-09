package game.Card;

import game.Robot;

public class Move2Card extends Cards {
        final String description = "Cards.Move2Card moves your robot in the direction it is facing by 2";
        final String name = "Move2";

@Override
public void effect(){
                //gamer.getRobot().forward(2);
        }

@Override
 public String getName() {
                return name;
        }
}
package game.Card;

import game.Gamer;
import game.Robot;

public class BackUpCard extends Cards {
    final String description = "Cards.Cards.BackUpCard moves your robot 1 space back without changing its facing direction.";
    final String name = "BackUp";

    @Override
    public void effect(Gamer gamer){
       gamer.getRobot().forward(-1);
    }
}

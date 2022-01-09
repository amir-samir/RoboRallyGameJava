package game.Card;

import game.Robot;

public class BackUpCard extends Cards {

    final String description = "Cards.Cards.BackUpCard moves your robot 1 space back without changing its facing direction.";

    public BackUpCard(){
        this.setName("BackUp");
    }

    @Override
    public void effect(){
       //gamer.getRobot().forward(-1);
    }
}

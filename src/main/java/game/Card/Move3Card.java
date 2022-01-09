package game.Card;

import game.Robot;

public class Move3Card extends Cards {

    final String description = "Cards.Move3Card moves your robot in the direction it is facing by 3";
    final String name = "MoveIII";

    public Move3Card(){
        this.setName("MoveIII");
    }

    @Override
    public void effect() {
        //gamer.getRobot().forward(1);
    }

}
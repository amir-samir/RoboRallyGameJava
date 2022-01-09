package game.Card;

import game.Robot;

public class RightTurnCard extends Cards {

    final String description = "Cards.RightTurnCard moves your robot turn 90 degrees to the right. The roboter remains in his current position.";

    public RightTurnCard(){
        this.setName("TurnRight");
    }

    @Override
    public void effect(){
      //gamer.getRobot().rotate(90);
    }

}

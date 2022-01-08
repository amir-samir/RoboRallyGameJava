package game.Card;

import game.Gamer;

public class LeftTurnCard extends Cards {
    final String description = "Cards.LeftTurnCard moves your robot turn 90 degrees to the left. The roboter remains in his current position.";
    final String name = "LeftTurn";


@Override
public void effect(Gamer gamer){
       gamer.getRobot().rotate(-90);
    }

    @Override
public String getName() {
        return name;
    }
}

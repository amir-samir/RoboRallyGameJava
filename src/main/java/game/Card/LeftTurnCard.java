package game.Card;

import game.Robot;

public class LeftTurnCard extends Cards {
    final String description = "Cards.LeftTurnCard moves your robot turn 90 degrees to the left. The roboter remains in his current position.";
    final String name = "LeftTurn";


@Override
public void effect(){
        //Robot robot = new Robot();
        //robot.rotate(-90);
    }

    @Override
public String getName() {
        return name;
    }
}

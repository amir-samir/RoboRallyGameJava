package game.Card;

import game.Robot;

public class RightTurnCard extends Cards {
    final String description = "Cards.RightTurnCard moves your robot turn 90 degrees to the right. The roboter remains in his current position.";
    final String name = "RightTurn";

    public void effect(){
        Robot robot = new Robot();
        robot.RIGHT();
    }

    @Override
    public String getName() {
        return name;
    }
}

package game.Card;

import game.Robot;

public class UTurnCard extends Cards {
    final String description = "The Cards.UTurnCard turns your robot 180 degrees so it faces the opposite direction. The robot remains in its current space.";
    final String name = "RightTurn";


    public void effect(){
        Robot robot = new Robot();
        robot.Opposite();
    }

    @Override
    public String getName() {
        return name;
    }
}

package game.Card;

import game.Robot;

public class UTurnCard extends Cards {

    final String description = "The Cards.UTurnCard turns your robot 180 degrees so it faces the opposite direction. The robot remains in its current space.";

    public UTurnCard(){
        this.setName("UTurn");
    }

    @Override
    public void effect(){
       //gamer.getRobot().rotate(180);
    }

}

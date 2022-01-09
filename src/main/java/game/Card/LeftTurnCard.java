package game.Card;

//import game.Gamer;

public class LeftTurnCard extends Cards {

    final String description = "Cards.LeftTurnCard moves your robot turn 90 degrees to the left. The roboter remains in his current position.";

    public LeftTurnCard(){
        this.setName("TurnLeft");
    }

    @Override
    public void effect(){
       //gamer.getRobot().rotate(-90);
    }
}

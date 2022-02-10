//import game.game.Gamer;

import Messages.Actions.PlayerTurning;
/**
 * class for programmingcard leftTurnCard
 * @author chen
 */
public class LeftTurnCard extends Cards {

    final String description = "Cards.LeftTurnCard moves your robot turn 90 degrees to the left. The roboter remains in his current position.";
    /**
     * Constructor
     */
    public LeftTurnCard(){
        this.setName("TurnLeft");
    }

    /**
     * robot's turning after activating leftturncard
     * @param robot affected robot
     * @param server connect to server
     */
    @Override
    public void effect(Robot robot, Server server){
        switch (robot.getDirection()){
            case "top":
                robot.setDirection("left");
                break;
            case "bottom":
                robot.setDirection("right");
                break;
            case "left":
                robot.setDirection("bottom");
                break;
            case "right":
                robot.setDirection("top");
                break;
        }

        PlayerTurning playerTurning = new PlayerTurning(robot.getGamerID(), "counterclockwise");
        playerTurning.getMessageBody().setKeys(new String[] {"clientID", "rotation"});
        server.sendMessageForAllUsers(playerTurning);

    }
}
import Messages.Actions.PlayerTurning;

/**
 * class for programmingcard uTurnCard
 * @author chen
 */
public class UTurnCard extends Cards {

    final String description = "The Cards.UTurnCard turns your robot 180 degrees so it faces the opposite direction. The robot remains in its current space.";
    /**
     * Constructor
     */
    public UTurnCard(){
        this.setName("UTurn");
    }

    /**
     * robot will turn 180 degrees after activating uturncard
     * @param robot affected robot
     * @param server connect to server
     */
    @Override
    public void effect(Robot robot, Server server){
        switch (robot.getDirection()){
            case "top":
                robot.setDirection("bottom");
                break;
            case "bottom":
                robot.setDirection("top");
                break;
            case "left":
                robot.setDirection("right");
                break;
            case "right":
                robot.setDirection("left");
                break;
        }

        PlayerTurning playerTurning = new PlayerTurning(robot.getGamerID(), "clockwise");
        playerTurning.getMessageBody().setKeys(new String[]{"clientID", "direction"});
        server.sendMessageForAllUsers(playerTurning);
        server.sendMessageForAllUsers(playerTurning);
    }

}


import Messages.Actions.Movement;
/**
 * class for programmingCard Move1card
 * @author chen
 */
public class Move1Card extends Cards {
    final String description = "Cards.Move1Card moves your robot in the direction it is facing by 1";
    /**
     * Constructor
     */
    public Move1Card(){
        this.setName("MoveI");
    }

    /**
     * robot movement after activating move1card
     * @param robot affected robot
     * @param server connect to server
     */
    @Override
    public void effect(Robot robot, Server server) {
        try {
            server.getGame().checkMovement(robot, robot.getDirection(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


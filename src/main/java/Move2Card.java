import Messages.Actions.Movement;
/**
 * class for programmingCard Move2card
 * @author chen
 */
public class Move2Card extends Cards {

    final String description = "Cards.Move2Card moves your robot in the direction it is facing by 2";
    /**
     * Constructor
     */
    public Move2Card(){
        this.setName("MoveII");
    }

    /**
     * robot movement after activating move2card
     * @param robot affected robot
     * @param server connect to server
     */
    @Override
    public void effect(Robot robot, Server server) {
        try {
            server.getGame().checkMovement(robot, robot.getDirection(), false);
            server.getGame().checkMovement(robot, robot.getDirection(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
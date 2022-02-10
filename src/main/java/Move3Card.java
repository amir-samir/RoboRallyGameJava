import Messages.Actions.Movement;
/**
 * class for programmingCard Move3card
 * @author chen
 */
public class Move3Card extends Cards {

    final String description = "Cards.Move3Card moves your robot in the direction it is facing by 3";
    final String name = "MoveIII";

    /**
     * Constructor
     */
    public Move3Card(){
        this.setName("MoveIII");
    }

    /**
     * robot movement after activating move3card
     * @param robot affected robot
     * @param server connect to server
     */
    @Override
    public void effect(Robot robot, Server server) {
        try {
            server.getGame().checkMovement(robot, robot.getDirection(), false);
            server.getGame().checkMovement(robot, robot.getDirection(), false);
            server.getGame().checkMovement(robot, robot.getDirection(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
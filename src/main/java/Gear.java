import Messages.Actions.PlayerTurning;
/**
 * Class for the BoardElement type "Gear".
 * @author yiluye
 */
public class Gear extends BoardElement {

    String[] orientations;

    /**
     * Constructor
     * @param isOnBoard on which board is this element
     * @param orientations which oreintation does this element have
     */
    public Gear(String isOnBoard, String[] orientations) {
        this.setType("Gear");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
    }

    @Override
    public String[] getOrientations() {
        return orientations;
    }

    /**
     * robot movement when the effect of Gear is activated
     * effect of Gear: turn clockwise or counterclockwise
     * because the robot may have different orientations, we use switch case in this situation
     * @param robot robot who is on a Gear
     * @param server connected to server
     */
    @Override
    public void effect(Robot robot, Server server) {
        if (this.orientations[0].equals("clockwise")) {
            switch (robot.getDirection()) {
                case "top":
                    robot.setDirection("right");
                    break;
                case "bottom":
                    robot.setDirection("left");
                    break;
                case "left":
                    robot.setDirection("top");
                    break;
                case "right":
                    robot.setDirection("bottom");
            }
        } else {
            switch (robot.getDirection()) {
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
            }
        }
        PlayerTurning playerTurning = new PlayerTurning(robot.getGamerID(), this.orientations[0]);
        playerTurning.getMessageBody().setKeys(new String[] {"clientID", "rotation"});
        server.sendMessageForAllUsers(playerTurning);
    }
}

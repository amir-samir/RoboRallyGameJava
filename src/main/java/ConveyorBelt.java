/**
 * Class for the BoardElement type "ConveyorBelt".
 * @author yiluye
 */
public class ConveyorBelt extends BoardElement {

    private String[] orientations;
    private int speed;

    /**
     * Constructor
     * @param isOnBoard
     * @param orientations
     * @param speed
     */
    public ConveyorBelt(String isOnBoard, String[] orientations, int speed) {
        this.setType("ConveyorBelt");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
        this.speed = speed;
    }

    /**
     * robot movement when conveyor belt has speed 2
     * @param robot
     * @param server
     */
    @Override
    public void effect(Robot robot, Server server) {
        server.getGame().checkConveyor(robot);
        if (speed == 2){
            server.getGame().checkConveyor(robot);
        }
    }

    /**
     * getter
     * @return
     */
    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public String[] getOrientations() {
        return orientations;
    }
}

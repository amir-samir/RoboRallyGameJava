/**
 * Class for the BoardElement type "ConveyorBelt".
 * @author yiluye
 */
public class ConveyorBelt extends BoardElement {

    private String[] orientations;
    private int speed;

    /**
     * Constructor
     * @param isOnBoard which board is this Element on
     * @param orientations which orientations does this belt have
     * @param speed which speed does this belt have
     */
    public ConveyorBelt(String isOnBoard, String[] orientations, int speed) {
        this.setType("ConveyorBelt");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
        this.speed = speed;
    }

    /**
     * robot movement when he activated a conveyor belt which has speed 2
     * @param robot affected robot in this situation
     * @param server connected to server
     */
    @Override
    public void effect(Robot robot, Server server) {
        server.getGame().checkConveyor(robot);
        if (speed == 2){
            server.getGame().checkConveyor(robot);
        }
    }

    /**
     * getter from speed
     * @return speed from this belt
     */
    @Override
    public int getSpeed() {
        return speed;
    }

    /**
     * getter from orientations
     * @return the local Orientations from this affected robot
     */
    @Override
    public String[] getOrientations() {
        return orientations;
    }
}

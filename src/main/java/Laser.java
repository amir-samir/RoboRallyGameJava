/**
 * Class for the BoardElement type "Laser".
 * @author yiluye
 */
public class Laser extends BoardElement {

    String[] orientations;
    int count;

    /**
     * Constructor
     * @param isOnBoard
     * @param orientations
     * @param count
     */
    public Laser(String isOnBoard, String[] orientations, int count){
        this.setType("Laser");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
        this.count = count;
    }

    /**
     * The action of the robot when the laser is triggered
     * Laser effect: get 2 spamCard
     * @param robot
     * @param server
     */
    @Override
    public void effect(Robot robot, Server server){
        server.getGame().drawDamageSpam(robot, count);
    }

    /**
     * getter
     * @return
     */
    @Override
    public String[] getOrientations() {
        return orientations;
    }

}

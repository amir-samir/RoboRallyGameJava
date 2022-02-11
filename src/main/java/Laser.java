/**
 * Class for the BoardElement type "Laser".
 * @author yiluye
 */
public class Laser extends BoardElement {

    String[] orientations;
    int count;

    /**
     * Constructor
     * @param isOnBoard which board is this laser on
     * @param orientations which orientations does this laser have
     * @param count how many lines does this laser have
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
     * @param robot robot who is hit by a laser
     * @param server connected to server
     */
    @Override
    public void effect(Robot robot, Server server){
        server.getGame().drawDamageSpam(robot, count);
    }

    /**
     * getter
     * @return the orientation from laser
     */
    @Override
    public String[] getOrientations() {
        return orientations;
    }

}

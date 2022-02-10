/**
 * Class for the BoardElement type "RestartPoint".
 * @author yiluye
 */
public class RestartPoint extends BoardElement {

    private String[] orientations;

    /**
     * Constructor
     * @param isOnBoard which board is this element on
     * @param orientations which orientation does RestartPoint have
     */
    public RestartPoint(String isOnBoard, String[] orientations){
        this.setType("RestartPoint");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
    }

    @Override
    public void effect(Robot robot, Server server){

    }

    /**
     * getter from RestartPoint orientations
     * @return the orientation for robot when he has to restart
     */
    @Override
    public String[] getOrientations() {
        return orientations;
    }
}


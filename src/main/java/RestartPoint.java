/**
 * Class for the BoardElement type "RestartPoint".
 * @author yiluye
 */
public class RestartPoint extends BoardElement {

    private String[] orientations;

    /**
     * Constructor
     * @param isOnBoard
     * @param orientations
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
     * getter
     * @return
     */
    @Override
    public String[] getOrientations() {
        return orientations;
    }
}


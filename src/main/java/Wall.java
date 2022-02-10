/**
 * Class for the BoardElement type "Wall".
 * @author yiluye
 */
public class Wall extends BoardElement {

    String[] orientations;

    /**
     * Constructor
     * @param isOnBoard
     * @param orientations
     */
    public Wall(String isOnBoard, String[] orientations) {
        this.setType("Wall");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
    }

    @Override
    public void effect(Robot robot, Server server) {

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


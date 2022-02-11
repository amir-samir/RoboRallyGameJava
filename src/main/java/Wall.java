/**
 * Class for the BoardElement type "Wall".
 * @author yiluye
 */
public class Wall extends BoardElement {

    String[] orientations;

    /**
     * Constructor
     * @param isOnBoard on which board is this element
     * @param orientations which orientation does this element have
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
     * @return orientation of this wall
     */
    @Override
    public String[] getOrientations() {
        return orientations;
    }
}


/**
 * Class for the BoardElement type "Empty"
 * @author yiluye
 */
public class Empty extends BoardElement {
    /**
     * Constructor
     * @param isOnBoard on which board is this element
     */
    public Empty(String isOnBoard) {
        this.setType("Empty");
        this.setIsOnBoard(isOnBoard);
    }

    @Override
    public void effect(Robot robot, Server server) {

    }

}


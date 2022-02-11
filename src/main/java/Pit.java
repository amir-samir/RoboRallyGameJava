/**
 * Class for the BoardElement type "Pit".
 * @author yiluye
 */
public class Pit extends BoardElement {
    /**
     * Constructor
     * @param isOnBoard on which board is this element
     */
    public Pit(String isOnBoard){
        this.setType("Pit");
        this.setIsOnBoard(isOnBoard);
    }

    @Override
    public void effect(Robot robot, Server server){

    }
}


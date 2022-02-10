/**
 * Class for the BoardElement type "StartPoint".
 * @author yiluye
 */
public class StartPoint extends BoardElement {

    /**
     * Constructor
     * @param isOnBoard
     */
    public StartPoint(String isOnBoard){
        this.setType("StartPoint");
        this.setIsOnBoard(isOnBoard);
    }

    @Override
    public void effect(Robot robot, Server server){

    }
}


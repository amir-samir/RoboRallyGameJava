/**
 * Overarching class for all BoardElement Types.
 * @author yiluye
 */
public abstract class BoardElement {

    private String type;
    private String isOnBoard;

    /**
     * robot movement after activating BoardElements effect
     * @param robot affected robot in this situation
     * @param server connected to server
     */
    public abstract void effect(Robot robot, Server server);

    /**
     * setter and getter
     */
    public void setType(String type){
        this.type = type;
    }

    public void setIsOnBoard(String isOnBoard){
        this.isOnBoard = isOnBoard;
    }

    public String getIsOnBoard() {
        return isOnBoard;
    }

    public String getType() {
        return type;
    }

    public int getSpeed() {
        return 0;
    }

    public int[] getRegisters() {
        return null;
    }

    public String[] getOrientations(){
        return null;
    }

    public int getCount(){
        return 0;
    }
}

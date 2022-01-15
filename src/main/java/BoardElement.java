/**
 * @author yiluye
 */


public abstract class BoardElement {

    private String type;
    private String isOnBoard;

    public abstract void effect(Robot robot, Server server);

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
}

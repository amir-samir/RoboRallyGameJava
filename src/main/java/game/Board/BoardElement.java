package game.Board;

/**
 * @author yiluye
 */


public abstract class BoardElement {
    private String type;
    private String isOnBoard;
    public abstract void effect();

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

}

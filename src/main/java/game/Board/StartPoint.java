package game.Board;

public class StartPoint extends BoardElement {

    public StartPoint(String isOnBoard){
        this.setType("StartPoint");
        this.setIsOnBoard(isOnBoard);
    }

    @Override
    public void effect(){

    }
}


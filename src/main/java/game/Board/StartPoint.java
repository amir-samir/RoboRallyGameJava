package game.Board;

public class StartPoint extends BoardElement {

    public void StartPoint(String isOnBoard){
        this.setType("StartPoint");
        this.setIsOnBoard(isOnBoard);
    }

    @Override
    public void effect(){

    }
}


package game.Board;

public class CheckPoint extends BoardElement {

    int count;

    public void CheckPoint(String isOnBoard, int count) {
        this.setType("CheckPoint");
        this.setIsOnBoard(isOnBoard);
        this.count = count;
    }

    @Override
    public void effect() {

    }

}


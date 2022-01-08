package game.Board;


public class Empty extends BoardElement {

    public Empty(String isOnBoard) {
        this.setType("Empty");
        this.setIsOnBoard(isOnBoard);
    }

    @Override
    public void effect() {

    }

}


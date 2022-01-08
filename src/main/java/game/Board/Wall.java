package game.Board;

public class Wall extends BoardElement {

    String[] orientations;

    public Wall(String isOnBoard, String[] orientations) {
        this.setType("Wall");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
    }

    @Override
    public void effect() {
        //gamer.getRobot().forward(0);
    }
}


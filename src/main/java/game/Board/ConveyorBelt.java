package game.Board;


public class ConveyorBelt extends BoardElement {

    private String[] orientations;
    private int speed;

    public ConveyorBelt(String isOnBoard, String[] orientations, int speed) {
        this.setType("ConveyorBelt");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
        this.speed = speed;
    }

    @Override
    public void effect() {

    }

}

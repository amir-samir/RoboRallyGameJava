public class Wall extends BoardElement {

    String[] orientations;

    public Wall(String isOnBoard, String[] orientations) {
        this.setType("Wall");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
    }

    @Override
    public void effect(Robot robot, Server server) {
        //gamer.getRobot().forward(0);
    }
}


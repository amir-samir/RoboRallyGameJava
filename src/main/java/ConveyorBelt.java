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
    public void effect(Robot robot, Server server) {
        server.getGame().checkConveyor(robot);
        if (speed == 2){
            server.getGame().checkConveyor(robot);
        }
    }

    @Override
    public int getSpeed() {
        return speed;
    }

    @Override
    public String[] getOrientations() {
        return orientations;
    }
}

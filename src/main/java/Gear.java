public class Gear extends BoardElement {

    String[] orientations;

    public Gear(String isOnBoard, String[] orientations) {
        this.setType("Gear");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
    }

    @Override
    public void effect(Robot robot, Server server) {
        /*if (orientations.equals(new String[]{"counterclockwise"})) {
            gamer.getRobot().rotate(-90);
        } else if (orientations.equals(new String[]{"clockwise"})) {
            gamer.getRobot().rotate(90);
        }

         */
    }
}

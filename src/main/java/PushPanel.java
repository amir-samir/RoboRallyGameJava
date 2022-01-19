public class PushPanel extends BoardElement {

    private String[] orientations;
    private int[] registers;

    public PushPanel(String isOnBoard, String[] orientations, int[] registers) {
        this.setType("PushPanel");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
        this.registers = registers;
    }

    @Override
    public void effect(Robot robot, Server server) {
        server.game.checkMovement(robot, this.orientations[0], true);
    }

    @Override
    public int[] getRegisters() {
        return registers;
    }

    @Override
    public String[] getOrientations() {
        return orientations;
    }
}

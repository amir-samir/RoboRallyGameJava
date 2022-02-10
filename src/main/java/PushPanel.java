/**
 * Class for the BoardElement type "PushPanel".
 * @author yiluye
 */
public class PushPanel extends BoardElement {

    private String[] orientations;
    private int[] registers;

    /**
     * Constructor
     * @param isOnBoard
     * @param orientations
     * @param registers
     */
    public PushPanel(String isOnBoard, String[] orientations, int[] registers) {
        this.setType("PushPanel");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
        this.registers = registers;
    }

    /**
     * Robot movement when the effect of PushPanel is triggered
     * depends on the Register nummer on the PushPanel
     * @param robot
     * @param server
     */
    @Override
    public void effect(Robot robot, Server server) {
        server.getGame().checkMovement(robot, this.orientations[0], true);
    }

    /**
     * getter
     * @return
     */
    @Override
    public int[] getRegisters() {
        return registers;
    }

    @Override
    public String[] getOrientations() {
        return orientations;
    }
}

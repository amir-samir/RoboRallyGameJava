/**
 * Class for the BoardElement type "PushPanel".
 * @author yiluye
 */
public class PushPanel extends BoardElement {

    private String[] orientations;
    private int[] registers;

    /**
     * Constructor
     * @param isOnBoard on which board is this element
     * @param orientations which orientation does this element have
     * @param registers which register is on the PushPanel
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
     * @param robot robot who is activated the effect of PushPanel
     * @param server connected to server
     */
    @Override
    public void effect(Robot robot, Server server) {
        server.getGame().checkMovement(robot, this.orientations[0], true);
    }

    /**
     * getter
     * @return number of register
     */
    @Override
    public int[] getRegisters() {
        return registers;
    }

    /**
     * getter
     * @return robot will be pushed in which orientation
     */
    @Override
    public String[] getOrientations() {
        return orientations;
    }
}

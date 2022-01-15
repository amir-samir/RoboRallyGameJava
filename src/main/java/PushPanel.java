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
        /*if (Arrays.asList(registers).contains(gamer.getRegisterCount() + 1)) {
            gamer.getRobot().forward(1);
        }
        */
    }

    @Override
    public int[] getRegisters() {
        return registers;
    }
}

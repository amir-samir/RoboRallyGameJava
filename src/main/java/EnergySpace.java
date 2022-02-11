import Messages.Actions.Energy;
/**
 * Class for the BoardElement type "EnergySpace".
 * @author yiluye
 */
public class EnergySpace extends BoardElement {

    int count;

    /**
     * Constructor
     * @param isOnBoard on which board is this element
     * @param count how many EnergyCube in this EnergySpace
     */
    public EnergySpace(String isOnBoard, int count){
        this.setType("EnergySpace");
        this.setIsOnBoard(isOnBoard);
        this.count = count;
    }

    /**
     * robot get an EnergyCube when he activated EnergySpace and there is an EnergyCube in the EnergySpace
     * every robot will get an EnergyCube when he arrived in Register 5
     * @param robot affected robot in this situation
     * @param server connected to server
     */
    @Override
    public void effect(Robot robot, Server server) {
        if (count > 0) {
            robot.setEnergyCube(robot.getEnergyCube() + 1);
            this.count -= 1;
            Energy energy = new Energy(robot.getGamerID(), robot.getEnergyCube(), "EnergySpace");
            energy.getMessageBody().setKeys(new String[]{"clientID", "count", "source"});
            server.sendMessageForAllUsers(energy);
        } else if (server.getGame().getActiveRegister() == 5){
            robot.setEnergyCube(robot.getEnergyCube() + 1);
            Energy energy = new Energy(robot.getGamerID(), robot.getEnergyCube(), "EnergySpace");
            energy.getMessageBody().setKeys(new String[]{"clientID", "count", "source"});
            server.sendMessageForAllUsers(energy);
        }
    }
}

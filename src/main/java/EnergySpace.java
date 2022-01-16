import Messages.Actions.Energy;

public class EnergySpace extends BoardElement {

    int count;

    public EnergySpace(String isOnBoard, int count){
        this.setType("EnergySpace");
        this.setIsOnBoard(isOnBoard);
        this.count = count;
    }

    @Override
    public void effect(Robot robot, Server server) {
        robot.setEnergyCube(robot.getEnergyCube() + 1);
        Energy energy = new Energy(robot.getGamerID(), robot.getEnergyCube(), "EnergySpace");
    }
}

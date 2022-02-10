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

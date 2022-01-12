public class EnergySpace extends BoardElement {

    int count;
    Boolean isThereAnEnergyCube;

    public EnergySpace(String isOnBoard, int count){
        this.setType("EnergySpace");
        this.setIsOnBoard(isOnBoard);
        this.count = count;
    }

    @Override
    public void effect(Robot robot, Server server) {

    }
}

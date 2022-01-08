package game.Board;

import game.Gamer;

public class EnergySpace extends BoardElement {

    int count;
    Boolean isThereAnEnergyCube;

    public EnergySpace(String isOnBoard, int count){
        this.setType("EnergySpace");
        this.setIsOnBoard(isOnBoard);
        this.count = count;
    }

    @Override
    public void effect(Gamer gamer) {
        if (gamer.getRobot().isOnEnergySpace() == true && isThereAnEnergyCube == true) {
            gamer.getNewEnergycube();
        }  else if (gamer.getRobot().isOnEnergySpace() == true && isThereAnEnergyCube == false && gamer.getRegisterCount() == 5) {
            gamer.getNewEnergycube();
        }
    }
}

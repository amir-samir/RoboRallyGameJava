package game.Board;

public class EnergySpaces extends BoardElement {
    private String name = "EnergySpaces";
    private int energyCubeInEnergySpace;

    public EnergySpaces(int energyCubeInEnergySpace) {
        this.energyCubeInEnergySpace = energyCubeInEnergySpace;
    }

    public int getEnergyCubeInEnergySpace() {
        return energyCubeInEnergySpace;
    }

}

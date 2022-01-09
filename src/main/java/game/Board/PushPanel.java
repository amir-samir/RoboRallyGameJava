package game.Board;

import game.Gamer;

import java.util.Arrays;

public class PushPanel extends BoardElement {

    String orientations;
    int[] registers;

    public PushPanel(String isOnBoard, String orientations, int[] registers) {
        this.setType("PushPanel");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
        this.registers = registers;
    }

    @Override
    public void effect(Gamer gamer) {
        if (Arrays.asList(registers).contains(gamer.getRegisterCount() + 1)) {
            gamer.getRobot().moveTo(this.orientations, 1, false);
        }

    }
}

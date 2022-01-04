package game.Board;
import game.Card.Spam;
import game.Gamer;
import lombok.Data;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author yiluye
 */
@Data
public abstract class BoardElement {
    private String name;
    private int x;
    private int y;
    //private int distance = 0;
    private int point = 0;
    private boolean isThereAnEnergyCube = true;
    // 0 upward; 1 face to right; 2 face to down; 3 face to left;
    // boardelements direction
    private String direction;
    private String conditionDirection;
    private List<Integer> pushPanelLabel;
    private Map<String, BeltEffect> beltEffect;

    /**
     * @param: gamer/user
     */
    public void effect(Gamer gamer) {
        switch (this.name) {
            case "Pits":
                //method from gamer wiederrufen
                gamer.reboot();
                gamer.pushCard(new Spam());
                break;
            case "Belt":
                gamer.getRobot().moveInBelt(this.beltEffect.get(gamer.getRobot().getDirection()));
                break;
            case "BoardLaser":
                gamer.pushCard(new Spam());
                break;
            case "Checkpoints":
                gamer.getPoints().add(this.point);
                break;
            case "Empty":
                //todo: Leere Felder (z.B. bei nicht rechteckigen Spielfeldern) werden mit null belegt.
                break;
            case "EnergySpaces":
                if (gamer.getRobot().isOnEnergySpace() == true && isThereAnEnergyCube == true) {
                    gamer.getNewEnergycube();
                }  else if (gamer.getRobot().isOnEnergySpace() == true && isThereAnEnergyCube == false && gamer.getRegisterCount() == 5) {
                    gamer.getNewEnergycube();
                }
                break;
            case "Gears":
                Gears gears = new Gears();
                if (gears.getColor().equals("red")) {
                    gamer.getRobot().rotate(-90);
                } else if (gears.getColor().equals("green")) {
                    gamer.getRobot().rotate(90);
                }
                break;
            case "PushPanels":
                if (Arrays.asList(pushPanelLabel).contains(gamer.getRegisterCount() + 1)) {
                    gamer.getRobot().moveTo(this.direction, 1, false);
                }
                break;
            case "RestartPoint":
                break;
            case "RotatingBelt":
                break;
            case "StartPoint":
                break;
            case "Walls":
                gamer.getRobot().forward(0);
                break;

            default:  //todo: catch exception
                break;
        }
    }
}

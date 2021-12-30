package game.Board;
import game.Card.CardUtil;
import game.Card.Spam;
import game.Gamer;
import lombok.Data;

/**
 * @author yiluye
 */
@Data
public abstract class BoardElement {
    private String name;
    private int x;
    private int y;
    private int distance = 0;
    private int point = 0;

    /**
     * @param: gamer/user
     */
    public void effect(Gamer gamer) {
        switch (this.name) {
            case "Pits":
                //method from gamer wiederrufen
                gamer.reboot();
                gamer.pushCard(CardUtil.getDamageCard());
                break;
            case "Belt":
                gamer.getRobot().forward(this.distance);
                break;
            case "BoardLaser":
                gamer.pushCard(new Spam());
                break;
            case "Checkpoints":
                gamer.getPoints().add(this.point);
                break;
            case "Empty":
                break;
            case "EnergySpaces":
                break;
            case "Gears":
                break;
            case "PushPanels":
                break;
            case "RestartPoint":
                break;
            case "RotatingBelt":
                break;
            case "StartPoint":
                break;
            case "Walls":
                break;

            default:  //todo: catch exception
                break;
        }
    }
}

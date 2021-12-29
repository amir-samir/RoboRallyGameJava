package game.Board;

/**
 * @author yiluye
 */
public abstract class BoardElement {
    private String name;
    private int x;
    private int y;

    /**
     * @param: gamer/user
     */
    public void effect() {
        switch (this.name) {
            case "Pits":
                //todo: get new damagecard
                //method from gamer wiederrufen
                break;
            case "Belt":
                //todo:
                break;
            case "BoardLaser":
                break;
            case "Checkpoints":
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

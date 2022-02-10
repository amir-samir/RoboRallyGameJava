//import game.game.Gamer;
/**
 * class for Cards
 * @author chen
 */
public abstract class Cards {

    public String name;

    public abstract void effect(Robot robot, Server server);

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
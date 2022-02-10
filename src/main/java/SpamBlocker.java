/**
 * class for upgradeCard spamBlocker
 * @author chen
 */
public class SpamBlocker extends UpgradeCards{
    /**
     * Constructor
     */
    public SpamBlocker(){
        this.setName("SpamBlocker");
        this.setCost(3);
    }

    /**
     * @param robot affected robot
     * @param server connect to server
     */
    @Override
    public void effect(Robot robot, Server server) {

    }
}

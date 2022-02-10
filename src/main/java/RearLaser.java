/**
 * class for upgradecards RearLaser
 * @author chen
 */
public class RearLaser extends UpgradeCards{
    /**
     * Constructor
     */
    public RearLaser(){
        this.setName("RearLaser");
        this.setCost(2);
    }

    /**
     * @param robot affected robot
     * @param server connect to server
     */
    @Override
    public void effect(Robot robot, Server server) {

    }

    @Override
    public String getName() {
        return super.getName();
    }
}

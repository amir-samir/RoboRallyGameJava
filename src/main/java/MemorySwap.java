/**
 * class for upgradeCards MemorySwap
 * @author chen
 */
public class MemorySwap extends UpgradeCards{
    /**
     * Constructor
     */
    public MemorySwap(){
        this.setName("MemorySwap");
        this.setCost(1);
    }

    /**
     * @param robot affected robot
     * @param server connect to server
     */
    @Override
    public void effect(Robot robot, Server server) {

    }

}

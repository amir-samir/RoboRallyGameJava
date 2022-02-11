/**
 * class for programmingcard AgainCard
 * @author chen
 */
public class AgainCard extends Cards {
    /**
     * Constructor
     */
    public AgainCard(){
        this.setName("Again");
    }


    /**
     * robot will repeat the effect from previous register after activating againcard
     * @param robot affected robot
     * @param server connect to server
     */
    @Override
    public void effect(Robot robot, Server server){
        if (server.getGame().getActiveRegister() != 0) {
            Cards card = robot.getRegister()[server.getGame().getActiveRegister() - 1];
            card.effect(robot, server);
        }
    }
}
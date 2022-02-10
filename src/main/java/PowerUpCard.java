import Messages.Actions.Energy;
/**
 * class for programmingcard powerupcard
 * @author chen
 */
public class PowerUpCard extends Cards {

    final String description = "Cards.PowerUpCard makes you take 1 energy cube and place it on your player mat.";

    /**
     * Constructor
     */
    public PowerUpCard(){
        this.setName("PowerUp");
    }

    /**
     * robot's energy will change after activating powerupCard
     * @param robot affected robot
     * @param server connect to server
     */
    @Override
    public void effect(Robot robot, Server server) {
        robot.setEnergyCube(robot.getEnergyCube() + 1);
        Energy energy = new Energy(robot.getGamerID(), robot.getEnergyCube(), "PowerUpCard");
        energy.getMessageBody().setKeys(new String[] {"clientID", "count", "source"});
        server.sendMessageForAllUsers(energy);
    }
}

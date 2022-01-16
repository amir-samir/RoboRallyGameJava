import Messages.Actions.Energy;

public class PowerUpCard extends Cards {

    final String description = "Cards.PowerUpCard makes you take 1 energy cube and place it on your player mat.";

    public PowerUpCard(){
        this.setName("PowerUp");
    }

    @Override
    public void effect(Robot robot, Server server) {
        robot.setEnergyCube(robot.getEnergyCube() + 1);
        Energy energy = new Energy(robot.getGamerID(), robot.getEnergyCube(), "PowerUpCard");
    }
}

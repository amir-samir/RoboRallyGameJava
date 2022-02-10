import Messages.Phase.ReplaceCard;
/**
 * class for damageCard Trojan
 * @author chen
 */
public class Trojan extends DamageCards {
    /**
     * Constructor
     */
    public Trojan(){
        this.setName("Trojan");
    }

    /**
     * immediately take two SPAM damage cards and play the firstcard of your deck in current register after activating Trojan
     * @param robot affected robot
     * @param server connect to server
     */
    @Override
    public void effect(Robot robot, Server server) {
        Cards activateCard = robot.getFirstCard();
        robot.getRegister()[server.getGame().getActiveRegister()] = activateCard;
        server.getGame().drawDamageSpam(robot, 2);

        ReplaceCard replaceCard = new ReplaceCard(server.getGame().getActiveRegister(), activateCard.getName(), robot.getGamerID());
        replaceCard.getMessageBody().setKeys(new String[]{"register", "newCard", "clientID"});
        server.sendMessageForAllUsers(replaceCard);

        activateCard.effect(robot,server);
        server.getGame().getCardsForGame().trojanHorse.add(new Trojan());
    }

}


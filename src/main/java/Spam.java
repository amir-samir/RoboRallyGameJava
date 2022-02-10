import Messages.Phase.ReplaceCard;
/**
 * class for damagecard SpamCard
 * @author chen
 */
public class Spam extends DamageCards {
    /**
     * Constructor
     */
    public Spam(){
        this.setName("Spam");
    }

    /**
     * robot will play the firstcard of the deck in current register after activating spamcard.
     * @param robot affected robot
     * @param server connect to server
     */
    @Override
    public void effect (Robot robot, Server server) {
        Cards activateCard = robot.getFirstCard();
        robot.getRegister()[server.getGame().getActiveRegister()] = activateCard;

        ReplaceCard replaceCard = new ReplaceCard(server.getGame().getActiveRegister(), activateCard.getName(), robot.getGamerID());
        replaceCard.getMessageBody().setKeys(new String[]{"register", "newCard", "clientID"});
        server.sendMessageForAllUsers(replaceCard);

        activateCard.effect(robot,server);
        server.getGame().getCardsForGame().spamCards.add(new Spam());
    }
}

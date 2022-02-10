import Messages.Phase.ReplaceCard;

import Messages.Phase.ReplaceCard;
/**
 * class for damageCard VirusCard
 * @author chen
 */
public class VirusCard extends DamageCards {

    /**
     * Constructor
     */
    public VirusCard(){
        this.setName("Virus");
    }

    /**
     * any robot on the board within a six-space radius take a virus card after activating viruscard
     * @param robot affected robot
     * @param server connect to server
     */
    @Override
    public void effect(Robot robot, Server server) {
        Cards activateCard = robot.getFirstCard();
        robot.getRegister()[server.getGame().getActiveRegister()] = activateCard;

        ReplaceCard replaceCard = new ReplaceCard(server.getGame().getActiveRegister(), activateCard.getName(), robot.getGamerID());
        replaceCard.getMessageBody().setKeys(new String[]{"register", "newCard", "clientID"});
        server.sendMessageForAllUsers(replaceCard);

        server.getGame().sendVirus(robot);
        activateCard.effect(robot,server);
        server.getGame().getCardsForGame().virusCards.add(new VirusCard());
    }
}

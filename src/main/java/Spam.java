import Messages.Phase.ReplaceCard;

public class Spam extends DamageCards {

    public Spam(){
        this.setName("Spam");
    }

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

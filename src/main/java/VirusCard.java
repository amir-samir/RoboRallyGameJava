import Messages.Phase.ReplaceCard;

public class VirusCard extends DamageCards {

    public VirusCard(){
        this.setName("Virus");
    }

    @Override
    public void effect(Robot robot, Server server) {
        Cards activateCard = robot.getFirstCard();
        robot.getRegister()[server.game.activeRegister] = activateCard;

        ReplaceCard replaceCard = new ReplaceCard(server.game.activeRegister, activateCard.getName(), robot.getGamerID());
        replaceCard.getMessageBody().setKeys(new String[]{"register", "newCard", "clientID"});
        server.sendMessageForAllUsers(replaceCard);

        activateCard.effect(robot,server);
        server.game.getCardsForGame().spamCards.add(new Spam());
    }
}

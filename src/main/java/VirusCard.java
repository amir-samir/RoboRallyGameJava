import Messages.Phase.ReplaceCard;

public class VirusCard extends DamageCards {

    public VirusCard(){
        this.setName("Virus");
    }

    @Override
    public void effect(Robot robot, Server server) {
        Cards activateCard = robot.getFirstCard();
        robot.getRegister()[server.game.getActiveRegister()] = activateCard;

        ReplaceCard replaceCard = new ReplaceCard(server.game.getActiveRegister(), activateCard.getName(), robot.getGamerID());
        replaceCard.getMessageBody().setKeys(new String[]{"register", "newCard", "clientID"});
        server.sendMessageForAllUsers(replaceCard);

        server.game.sendVirus(robot);
        activateCard.effect(robot,server);
        server.game.getCardsForGame().virusCards.add(new VirusCard());
    }
}

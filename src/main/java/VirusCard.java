import Messages.Phase.ReplaceCard;

public class VirusCard extends DamageCards {

    public VirusCard(){
        this.setName("Virus");
    }

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

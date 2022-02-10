import Messages.Phase.ReplaceCard;

public class Worm extends DamageCards {

    public Worm(){
        this.setName("Worm");
    }

    @Override
    public void effect(Robot robot, Server server) {
        Cards activateCard = robot.getFirstCard();
        robot.getRegister()[server.getGame().getActiveRegister()] = activateCard;

        ReplaceCard replaceCard = new ReplaceCard(server.getGame().getActiveRegister(), activateCard.getName(), robot.getGamerID());
        replaceCard.getMessageBody().setKeys(new String[]{"register", "newCard", "clientID"});
        server.sendMessageForAllUsers(replaceCard);

        server.getGame().reboot(robot, server.getGame().board.getMap()[robot.getX()][robot.getY()].get(0).getIsOnBoard());
        server.getGame().getCardsForGame().wormCards.add(new Worm());
    }

}

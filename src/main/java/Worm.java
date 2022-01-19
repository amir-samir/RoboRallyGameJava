import Messages.Phase.ReplaceCard;

public class Worm extends DamageCards{

    public Worm(){
        this.setName("Worm");
    }

    @Override
    public void effect(Robot robot, Server server) {
        Cards activateCard = robot.getFirstCard();
        robot.getRegister()[server.game.activeRegister] = activateCard;

        ReplaceCard replaceCard = new ReplaceCard(server.game.activeRegister, activateCard.getName(), robot.getGamerID());
        replaceCard.getMessageBody().setKeys(new String[]{"register", "newCard", "clientID"});
        server.sendMessageForAllUsers(replaceCard);

        server.game.reboot(robot, server.game.board.getMap()[robot.getX()][robot.getY()].get(0).getIsOnBoard());
        server.game.getCardsForGame().wormCards.add(new Worm());
    }

}

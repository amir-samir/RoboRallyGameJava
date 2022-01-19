import Messages.Phase.ReplaceCard;

public class Trojan extends DamageCards{

    public Trojan(){
        this.setName("Trojan");
    }

    @Override
    public void effect(Robot robot, Server server) {
        Cards activateCard = robot.getFirstCard();
        robot.getRegister()[server.game.activeRegister] = activateCard;
        server.game.drawDamage(robot, 2);

        ReplaceCard replaceCard = new ReplaceCard(server.game.activeRegister, activateCard.getName(), robot.getGamerID());
        replaceCard.getMessageBody().setKeys(new String[]{"register", "newCard", "clientID"});
        server.sendMessageForAllUsers(replaceCard);

        activateCard.effect(robot,server);
        server.game.getCardsForGame().trojanHorse.add(new Trojan());
    }

}

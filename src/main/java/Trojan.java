import Messages.Phase.ReplaceCard;

public class Trojan extends DamageCards{

    public Trojan(){
        this.setName("Trojan");
    }

    @Override
    public void effect(Robot robot, Server server) {


        ReplaceCard replaceCard = new ReplaceCard(server.game.activeRegister, activateCard.getName(), robot.getGamerID());
        replaceCard.getMessageBody().setKeys(new String[]{"register", "newCard", "clientID"});
        server.sendMessageForAllUsers(replaceCard);
    }

}

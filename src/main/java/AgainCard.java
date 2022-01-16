public class AgainCard extends Cards {

    public AgainCard(){
        this.setName("Again");
    }

    @Override
    public void effect(Robot robot, Server server){
        if (server.game.getActiveRegister() != 0) {
            Cards card = robot.getRegister()[server.game.getActiveRegister() - 1];
            card.effect(robot, server);
        }
    }
}

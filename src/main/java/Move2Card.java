import Messages.Actions.Movement;

public class Move2Card extends Cards {

    final String description = "Cards.Move2Card moves your robot in the direction it is facing by 2";

    public Move2Card(){
        this.setName("MoveII");
    }

    @Override
    public void effect(Robot robot, Server server) {
        try {
            server.game.checkMovement(robot, robot.getDirection(), false);
            server.game.checkMovement(robot, robot.getDirection(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
import Messages.Actions.Movement;

public class Move1Card extends Cards {
    final String description = "Cards.Move1Card moves your robot in the direction it is facing by 1";

    public Move1Card(){
        this.setName("MoveI");
    }

    @Override
    public void effect(Robot robot, Server server) {
        try {
            server.getGame().checkMovement(robot, robot.getDirection(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

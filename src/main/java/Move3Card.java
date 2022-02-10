import Messages.Actions.Movement;

public class Move3Card extends Cards {

    final String description = "Cards.Move3Card moves your robot in the direction it is facing by 3";
    final String name = "MoveIII";

    public Move3Card(){
        this.setName("MoveIII");
    }

    @Override
    public void effect(Robot robot, Server server) {
        try {
            server.getGame().checkMovement(robot, robot.getDirection(), false);
            server.getGame().checkMovement(robot, robot.getDirection(), false);
            server.getGame().checkMovement(robot, robot.getDirection(), false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
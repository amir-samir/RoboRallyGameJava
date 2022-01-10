import Messages.Actions.Movement;

public class Move1Card extends Cards {
    final String description = "Cards.Move1Card moves your robot in the direction it is facing by 1";

    public Move1Card(){
        this.setName("MoveI");
    }

    @Override
    public void effect(Robot robot, Server server) {
        switch (robot.getDirection()) {
            case "top":
                robot.setY(robot.getY() - 1);
                break;
            case "bottom":
                robot.setY(robot.getX() + 1);
                break;
            case "left":
                robot.setX(robot.getX() - 1);
                break;
            case "right":
                robot.setX(robot.getX() + 1);
                break;
        }

        Movement movement = new Movement(robot.getGamerID(), robot.getX(), robot.getY());
        movement.getMessageBody().setKeys(new String[]{"clientID", "x", "y"});
        server.sendMessageForAllUsers(movement);
    }
}

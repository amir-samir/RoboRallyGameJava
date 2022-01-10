import Messages.Actions.Movement;

public class Move2Card extends Cards {

    final String description = "Cards.Move2Card moves your robot in the direction it is facing by 2";

    public Move2Card(){
        this.setName("MoveII");
    }

    @Override
    public void effect(Robot robot, Server server) {
        switch (robot.getDirection()){
            case "top":
                robot.setY(robot.getY() - 2);
                break;
            case "bottom":
                robot.setY(robot.getX() + 2);
                break;
            case "left":
                robot.setX(robot.getX() - 2);
                break;
            case "right":
                robot.setX(robot.getX() + 2);
                break;
        }

        Movement movement = new Movement(robot.getGamerID(), robot.getX(), robot.getY());
        movement.getMessageBody().setKeys(new String[]{"clientID", "x", "y"});
        server.sendMessageForAllUsers(movement);
    }
}
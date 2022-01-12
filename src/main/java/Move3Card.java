import Messages.Actions.Movement;

public class Move3Card extends Cards {

    final String description = "Cards.Move3Card moves your robot in the direction it is facing by 3";
    final String name = "MoveIII";

    public Move3Card(){
        this.setName("MoveIII");
    }

    @Override
    public void effect(Robot robot, Server server) {
        switch (robot.getDirection()){
            case "top":
                robot.setX(robot.getX() - 3);
                break;
            case "bottom":
                robot.setX(robot.getX() + 3);
                break;
            case "left":
                robot.setY(robot.getY() - 3);
                break;
            case "right":
                robot.setY(robot.getY() + 3);
                break;
        }
        Movement movement = new Movement(robot.getGamerID(), robot.getX(), robot.getY());
        movement.getMessageBody().setKeys(new String[]{"clientID", "x", "y"});
        server.sendMessageForAllUsers(movement);
    }

}
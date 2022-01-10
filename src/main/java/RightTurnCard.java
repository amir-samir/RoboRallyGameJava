import Messages.Actions.PlayerTurning;

public class RightTurnCard extends Cards {

    final String description = "Cards.RightTurnCard moves your robot turn 90 degrees to the right. The roboter remains in his current position.";

    public RightTurnCard(){
        this.setName("TurnRight");
    }

    @Override
    public void effect(Robot robot, Server server){
        switch (robot.getDirection()){
            case "top":
                robot.setDirection("right");
                break;
            case "bottom":
                robot.setDirection("left");
                break;
            case "left":
                robot.setDirection("top");
                break;
            case "right":
                robot.setDirection("bottom");
                break;
        }

        PlayerTurning playerTurning = new PlayerTurning(robot.getGamerID(), "clockwise");
        playerTurning.getMessageBody().setKeys(new String[] {"clientID", "rotation"});
        server.sendMessageForAllUsers(playerTurning);
    }

}

//import game.game.Gamer;

import Messages.Actions.PlayerTurning;

public class LeftTurnCard extends Cards {

    final String description = "Cards.LeftTurnCard moves your robot turn 90 degrees to the left. The roboter remains in his current position.";

    public LeftTurnCard(){
        this.setName("TurnLeft");
    }

    @Override
    public void effect(Robot robot, Server server){
        switch (robot.getDirection()){
            case "top":
                robot.setDirection("left");
                break;
            case "bottom":
                robot.setDirection("right");
                break;
            case "left":
                robot.setDirection("bottom");
                break;
            case "right":
                robot.setDirection("top");
                break;
        }

        PlayerTurning playerTurning = new PlayerTurning(robot.getGamerID(), "counterclockwise");
        playerTurning.getMessageBody().setKeys(new String[] {"clientID", "rotation"});
        server.sendMessageForAllUsers(playerTurning);

    }
}

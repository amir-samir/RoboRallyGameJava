import Messages.Actions.PlayerTurning;

public class UTurnCard extends Cards {

    final String description = "The Cards.UTurnCard turns your robot 180 degrees so it faces the opposite direction. The robot remains in its current space.";

    public UTurnCard(){
        this.setName("UTurn");
    }

    @Override
    public void effect(Robot robot, Server server){
        switch (robot.getDirection()){
            case "top":
                robot.setDirection("bottom");
                break;
            case "bottom":
                robot.setDirection("top");
                break;
            case "left":
                robot.setDirection("right");
                break;
            case "right":
                robot.setDirection("left");
                break;
        }

        PlayerTurning playerTurning = new PlayerTurning(robot.getGamerID(), "clockwise");
        playerTurning.getMessageBody().setKeys(new String[]{"clientID", "direction"});
        server.sendMessageForAllUsers(playerTurning);
        server.sendMessageForAllUsers(playerTurning);
    }

}

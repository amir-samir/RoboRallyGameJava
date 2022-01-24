import Messages.Actions.PlayerTurning;

public class Gear extends BoardElement {

    String[] orientations;

    public Gear(String isOnBoard, String[] orientations) {
        this.setType("Gear");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
    }

    @Override
    public String[] getOrientations() {
        return orientations;
    }

    @Override
    public void effect(Robot robot, Server server) {
        if (this.orientations[0].equals("clockwise")) {
            switch (robot.getDirection()) {
                case "top":
                    robot.setDirection("right");
                    break;
                case "bottom":
                    robot.setDirection("left");
                    break;
                case "left":
                    robot.setDirection("top");
                    break;
                case "rigth":
                    robot.setDirection("bottom");
            }
        } else {
            switch (robot.getDirection()) {
                case "top":
                    robot.setDirection("left");
                    break;
                case "bottom":
                    robot.setDirection("right");
                    break;
                case "left":
                    robot.setDirection("bottom");
                    break;
                case "rigth":
                    robot.setDirection("top");
            }
        }
        PlayerTurning playerTurning = new PlayerTurning(robot.getGamerID(), this.orientations[0]);
        playerTurning.getMessageBody().setKeys(new String[] {"clientID", "rotation"});
        server.sendMessageForAllUsers(playerTurning);
    }
}

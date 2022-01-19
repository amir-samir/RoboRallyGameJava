import Messages.Actions.CheckPointReached;

public class CheckPoint extends BoardElement {

    int count;

    public CheckPoint(String isOnBoard, int count) {
        this.setType("CheckPoint");
        this.setIsOnBoard(isOnBoard);
        this.count = count;
    }

    @Override
    public void effect(Robot robot, Server server) {
        if (this.count - 1 == robot.getCollectedCheckpoints()){
            robot.setCollectedCheckpoints(this.count);
            CheckPointReached checkPointReached = new CheckPointReached(robot.getGamerID(), this.count);
            checkPointReached.getMessageBody().setKeys(new String[]{"clientID", "number"});
            server.sendMessageForAllUsers(checkPointReached);
            if (server.game.getNeededCheckpoints()-1 == count){
                server.endGame(robot);
            }
        }
    }
}


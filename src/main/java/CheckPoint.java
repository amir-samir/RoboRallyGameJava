import Messages.Actions.CheckPointReached;
/**
 * Class for the BoardElement type "CheckPoint".
 * @author yiluye
 */
public class CheckPoint extends BoardElement {

    int count;

    /**
     * Constructor
     * @param isOnBoard
     * @param count
     */
    public CheckPoint(String isOnBoard, int count) {
        this.setType("CheckPoint");
        this.setIsOnBoard(isOnBoard);
        this.count = count;
    }

    /**
     * Robot action when he gets arrive in a CheckPoint
     * @param robot
     * @param server
     */
    @Override
    public void effect(Robot robot, Server server) {
        if (this.count - 1 == robot.getCollectedCheckpoints()){
            robot.setCollectedCheckpoints(this.count);
            CheckPointReached checkPointReached = new CheckPointReached(robot.getGamerID(), this.count);
            checkPointReached.getMessageBody().setKeys(new String[]{"clientID", "number"});
            server.sendMessageForAllUsers(checkPointReached);
            if (server.getGame().getNeededCheckpoints()-1 == count){
                server.endGame(robot);
                server.getGame().endGame();
            }
        }
    }

    @Override
    public int getCount() {
        return count;
    }
}


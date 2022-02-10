/**
 * class for programmingcard BackUpCard
 * @author chen
 */
public class BackUpCard extends Cards {
    final String description = "Cards.Cards.BackUpCard moves your robot 1 space back without changing its facing direction.";
    /**
     * Constructor
     */
    public BackUpCard(){
        this.setName("BackUp");
    }

    /**
     * robot movement after activating backupcard
     * @param robot affected robot
     * @param server connect to server
     */
    @Override
    public void effect(Robot robot, Server server){
        try {
            String richtung;
            switch (robot.getDirection()){
                case "top":
                    richtung = "bottom";
                    break;
                case "bottom":
                    richtung = "top";
                    break;
                case "right":
                    richtung = "left";
                    break;
                case "left":
                    richtung = "right";
                default:
                    richtung = null;
                    break;
            }
            server.getGame().checkMovement(robot, richtung, false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
public class BackUpCard extends Cards {

    final String description = "Cards.Cards.BackUpCard moves your robot 1 space back without changing its facing direction.";

    public BackUpCard(){
        this.setName("BackUp");
    }

    @Override
    public void effect(Robot robot, Server server){
       switch (robot.getDirection()){
           case "top":
               robot.setY(robot.getY() + 1);
               break;
           case "bottom":
               robot.setY(robot.getX() - 1);
               break;
           case "left":
               robot.setX(robot.getX() + 1);
               break;
           case "right":
               robot.setX(robot.getX() - 1);
               break;
       }
    }
}

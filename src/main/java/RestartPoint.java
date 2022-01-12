public class RestartPoint extends BoardElement {

    public RestartPoint(String isOnBoard){
        this.setType("RestartPoint");
        this.setIsOnBoard(isOnBoard);
    }

    @Override
    public void effect(Robot robot, Server server){

    }
}


public class RestartPoint extends BoardElement {

    private String[] orientations;

    public RestartPoint(String isOnBoard, String[] orientations){
        this.setType("RestartPoint");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
    }

    @Override
    public void effect(Robot robot, Server server){

    }

    @Override
    public String[] getOrientations() {
        return orientations;
    }
}


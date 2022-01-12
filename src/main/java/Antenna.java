public class Antenna extends BoardElement {

    String[] orientations;

    public Antenna(String isOnBoard, String[] orientations){
        this.setType("Antenna");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
    }

    @Override
    public void effect(Robot robot, Server server){


    }

}


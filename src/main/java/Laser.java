public class Laser extends BoardElement {

    String[] orientations;
    int count;

    public Laser(String isOnBoard, String[] orientations, int count){
        this.setType("Laser");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
        this.count = count;
    }

    @Override
    public void effect(Robot robot, Server server){
        //gamer.pushCard(new Spam());
    }
}
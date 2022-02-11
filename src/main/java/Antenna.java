/**
 * Class for the BoardElement type "Antenna".
 * @author yiluye
 */
public class Antenna extends BoardElement {

    private String[] orientations;

    /**
     * Constructor
     * @param isOnBoard on which board is this element
     * @param orientations which orientation does this element have
     */
    public Antenna(String isOnBoard, String[] orientations){
        this.setType("Antenna");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
    }

    @Override
    public void effect(Robot robot, Server server){

    }

}


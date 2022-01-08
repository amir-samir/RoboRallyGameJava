package game.Board;

import game.Card.Spam;
import game.Gamer;

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
    public void effect(Gamer gamer){
        gamer.pushCard(new Spam());
    }
}

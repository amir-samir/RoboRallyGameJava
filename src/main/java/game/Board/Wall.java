package game.Board;

import game.Gamer;

public class Wall extends BoardElement {

    String[] orientations;

    public Wall(String isOnBoard, String[] orientations) {
        this.setType("Wall");
        this.setIsOnBoard(isOnBoard);
        this.orientations = orientations;
    }

    @Override
    public void effect(Gamer gamer) {
        gamer.getRobot().forward(0);
    }
}


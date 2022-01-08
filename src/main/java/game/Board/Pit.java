package game.Board;

import game.Gamer;

public class Pit extends BoardElement {

    public Pit(String isOnBoard){
        this.setType("Pit");
        this.setIsOnBoard(isOnBoard);
    }

    @Override
    public void effect(Gamer gamer){

    }
}


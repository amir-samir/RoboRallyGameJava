package game.Board;

import game.Gamer;

public class StartPoint extends BoardElement {

    public StartPoint(String isOnBoard){
        this.setType("StartPoint");
        this.setIsOnBoard(isOnBoard);
    }

    @Override
    public void effect(Gamer gamer){

    }
}


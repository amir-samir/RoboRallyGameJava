package game.Board;

import game.Gamer;

public class RestartPoint extends BoardElement {

    public RestartPoint(String isOnBoard){
        this.setType("RestartPoint");
        this.setIsOnBoard(isOnBoard);
    }

    @Override
    public void effect(Gamer gamer){

    }
}


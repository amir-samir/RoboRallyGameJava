package game.Board;

import game.Card.Spam;
import game.Gamer;

public class Pit extends BoardElement {

    public Pit(String isOnBoard){
        this.setType("Pit");
        this.setIsOnBoard(isOnBoard);
    }

    @Override
    public void effect(Gamer gamer){
        gamer.reboot();
        gamer.pushCard(new Spam());
        gamer.pushCard(new Spam());
    }
}


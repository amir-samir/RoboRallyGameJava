package game;

import game.Card.Cards;
import game.Card.ProgrammingCardsForPlayer;
import lombok.Data;

import java.util.ArrayList;

/**
 * @author: yilu ,chen
 * @Date: 2021/12/29
 */

public class Robot {

    private String name;
    private int gamerID;

    private String direction;
    private int x;
    private int y;

    private ProgrammingCardsForPlayer deck;
    private ArrayList<Cards> handCards;
    private Cards[] register;
    private boolean ableToFillRegisters;


    public Robot(int ID){
        deck = new ProgrammingCardsForPlayer();
        handCards = new ArrayList<Cards>();
        register = new Cards[5];
        ableToFillRegisters = true;

        x = -1;
        y = -1;
        this.gamerID = ID;
    }

    public void drawHandCards(){
        for (int i = handCards.size(); i < 9; i++){
            handCards.add(deck.getDeck().remove(i));
        }
    }

    public boolean cardIntoRegister(String card, int register){
        if(ableToFillRegisters) {
            if (card == null) {
                if (this.register != null) {
                    handCards.add(this.register[register]);
                }
                this.register[register] = null;
                return true;
            }
            for (int i = 0; i < handCards.size(); i++) {
                if (handCards.get(i).equals(card)) {
                    this.register[register] = handCards.get(i);
                    handCards.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public boolean allRegistersFilled(){
        for (Cards card: this.register){
            if (card == null){
                return false;
            }
        }
        ableToFillRegisters = false;
        return true;
    }


    // 0 upward; 1 face to right; 2 face to down; 3 face to left;
    // forward(-1) = backup(1)
    public void forward(int step) {
        switch (this.direction) {
            case "0":
                x += step;
                break;
            case "1":
                x += step;
                break;
            case "2":
                y -= step;
                break;
            case "3":
                y -= step;
                break;
        }
    }

    //rotate
    // @param: right rotate angle
    public void rotate(int angle) {
        this.direction = String.valueOf((Integer.parseInt(this.direction) + (angle / 90)) % 4);
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setHandCards(ArrayList<Cards> handCards) {
        this.handCards = handCards;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getGamerID() {
        return gamerID;
    }

    public ArrayList<Cards> getHandCards() {
        return handCards;
    }

    /**
    public void moveTo(String Direction,String int step, ) {
        String temp = this.direction;
        this.direction = newDirection;
        forward(step);
        if (keepDirection) {
            this.direction = temp;
        }
    }

    /**

    public void moveInBelt(BeltEffect effect) {
        String temp = this.direction;
        this.direction = effect.getDirection();
        forward(effect.getStep());
        if (effect.isKeepDirection()) {
            this.direction = temp;
        }
    }
     */
}

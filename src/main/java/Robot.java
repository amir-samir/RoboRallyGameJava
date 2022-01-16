import java.util.ArrayList;
import java.util.Collections;

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
    private int entfernungZurAntenne;

    private ProgrammingCardsForPlayer deck;
    private ArrayList<Cards> handCards;
    private Cards[] register;
    private boolean ableToFillRegisters;
    private boolean isDead;
    private int energyCube;
    private int collectedCheckpoints;


    public Robot(int ID){
        deck = new ProgrammingCardsForPlayer();
        handCards = new ArrayList<Cards>();
        register = new Cards[5];
        ableToFillRegisters = true;
        isDead = false;
        energyCube = 0;
        collectedCheckpoints = -1;

        x = -1;
        y = -1;
        this.gamerID = ID;
    }

    public void drawHandCards(){
        for (int i = 0; i < 9; i++){
            if (deck.getDeck().get(0) == null){
                mischen();
            }
            handCards.add(deck.getDeck().remove(0));
        }
    }

    public boolean cardIntoRegister(String card, int register){
        if(ableToFillRegisters) {
            if (card == null) {
                if (this.register[register] != null) {
                    handCards.add(this.register[register]);
                }
                this.register[register] = null;
                return true;
            } else {
                int zähler = handCards.size();
                for (int i = 0; i < zähler; i++) {
                    if (handCards.get(i).getName().equals(card)) {
                        this.register[register] = handCards.get(i);
                        handCards.remove(i);
                        return true;
                    }
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

    public void fillRegisters(){
        int zähler = this.handCards.size();
        for (int i = 0; i < zähler; i++){
            if (handCards.get(0) != null) {
                Cards card = handCards.remove(0);
                this.deck.getDiscard().add(card);
            }
        }
        for (int i = 0; i < register.length; i++){
            if (register[i] == null){
                if (deck.getDeck().get(0) == null) {
                    mischen();
                }
                register[i] = deck.getDeck().remove(0);
            }
        }
    }

    public void mischen(){
        ArrayList<Cards> cards = deck.getDiscard();
        Collections.shuffle(cards);
        Collections.shuffle(cards);
        Collections.shuffle(cards);
        deck.setDeck(cards);
        deck.setDiscard(new ArrayList<Cards>());
    }

    public void clearRegister(){
        for (int i = 0; i < register.length; i++){
            deck.getDiscard().add(register[i]);
            register[i] = null;
        }
    }

    public void clearHandcards(){
        int zähler = handCards.size();
        for (int i = 0; i < handCards.size(); i++){
            this.deck.getDiscard().add(handCards.remove(0));
        }
    }

    public void shoot(){

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

    public void setDirection(String direction) {
        this.direction = direction;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setDead(boolean dead) {
        isDead = dead;
    }

    public void setHandCards(ArrayList<Cards> handCards) {
        this.handCards = handCards;
    }

    public void setAbleToFillRegisters(boolean ableToFillRegisters) {
        this.ableToFillRegisters = ableToFillRegisters;
    }

    public void setEntfernungZurAntenne(int entfernungZurAntenne) {
        this.entfernungZurAntenne = entfernungZurAntenne;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setEnergyCube(int energyCube) {
        this.energyCube = energyCube;
    }

    public void setCollectedCheckpoints(int collectedCheckpoints) {
        this.collectedCheckpoints = collectedCheckpoints;
    }

    public int getGamerID() {
        return gamerID;
    }

    public ArrayList<Cards> getHandCards() {
        return handCards;
    }

    public boolean getAbleToFillRegisters(){
        return ableToFillRegisters;
    }

    public Cards[] getRegister() {
        return register;
    }

    public int getEntfernungZurAntenne() {
        return entfernungZurAntenne;
    }

    public String getDirection() {
        return direction;
    }

    public boolean getDead(){
        return isDead;
    }

    public ProgrammingCardsForPlayer getDeck() {
        return deck;
    }

    public int getEnergyCube() {
        return energyCube;
    }

    public int getCollectedCheckpoints() {
        return collectedCheckpoints;
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

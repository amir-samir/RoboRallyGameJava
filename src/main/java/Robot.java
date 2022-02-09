import java.util.ArrayList;
import java.util.Collections;

public class Robot {


    private int gamerID;
    private String direction;
    private int x;
    private int y;
    private int entfernungZurAntenne;

    private ProgrammingCardsForPlayer deck;
    private ArrayList<Cards> handCards;
    private Cards[] register;
    private UpgradeCards[] temporaryCards;
    private UpgradeCards[] permanentCards;
    private boolean ableToFillRegisters;
    private boolean ableToChooseRestartDirection;
    private boolean ableToChooseDamageCard;
    private boolean ableToReturnCard;
    private boolean isDead;
    private boolean receivedPunishment;
    private int energyCube;
    private int collectedCheckpoints;
    private int startPointX;
    private int startPointY;


    public Robot(int ID){
        deck = new ProgrammingCardsForPlayer();
        handCards = new ArrayList<Cards>();
        register = new Cards[5];
        temporaryCards = new UpgradeCards[3];
        permanentCards = new UpgradeCards[3];
        ableToFillRegisters = true;
        ableToChooseRestartDirection = false;
        ableToChooseDamageCard = false;
        ableToReturnCard = false;
        isDead = false;
        receivedPunishment = false;
        energyCube = 5;
        collectedCheckpoints = -1;

        x = -1;
        y = -1;
        this.gamerID = ID;
    }

    public int drawHandCards(){
        boolean spamBlocker = false;
        boolean memorySwap = false;
        for (int i = 0; i < temporaryCards.length; i++){
            if (temporaryCards[i] != null){
                if (temporaryCards[i].getName().equals("SpamBlocker")){
                    spamBlocker = true;
                    temporaryCards[i] = null;
                } else if (temporaryCards[i].getName().equals("MemorySwap")){
                    memorySwap = true;
                    this.ableToReturnCard = true;
                    temporaryCards[i] = null;
                }
            }
        }
        if (memorySwap){
            for (int i = 0; i < 12; i++){
                if (deck.getDeck().size() == 0) {
                    mischen();
                }
                handCards.add(deck.getDeck().remove(0));
            }
        } else {
            for (int i = 0; i < 9; i++) {
                if (deck.getDeck().size() == 0) {
                    mischen();
                }
                handCards.add(deck.getDeck().remove(0));
            }
        }
        if (spamBlocker){
            int counter = 0;
            for (int i = 0; i < handCards.size(); i++){
                if (handCards.get(i).getName().equals("Spam")){
                    counter += 1;
                    handCards.remove(i);
                    handCards.add(deck.getDeck().remove(0));
                }
            }
            return counter;
        } else return 0;
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
            if (handCards.size() > 0) {
                Cards card = handCards.remove(0);
                this.deck.getDiscard().add(card);
            }
        }
        for (int i = 0; i < register.length; i++){
            if (register[i] == null){
                if (deck.getDeck().size() == 0) {
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

    public boolean addPermUpgrade(UpgradeCards card){
        boolean doubleCard = false;
        for (int i = 0; i < permanentCards.length; i++){
            if (permanentCards[i] != null){
                if (permanentCards[i].getName() == card.getName()){
                    doubleCard = true;
                }
            }
        }
        if (!doubleCard) {
            for (int i = 0; i < permanentCards.length; i++) {
                if (permanentCards[i] == null) {
                    permanentCards[i] = card;
                    return true;
                }
            }
        } else return false;
        return true;
    }

    public boolean addTempUpgrade(UpgradeCards card){
        boolean doubleCard = false;
        for (int i = 0; i < temporaryCards.length; i++){
            if (temporaryCards[i] != null){
                if (temporaryCards[i].getName() == card.getName()){
                    doubleCard = true;
                }
            }
        }
        if (!doubleCard) {
            for (int i = 0; i < temporaryCards.length; i++) {
                if (temporaryCards[i] == null) {
                    temporaryCards[i] = card;
                    return true;
                }
            }
        } else return false;
        return true;
    }

    public void clearHandcards(){
        int zähler = handCards.size();
        for (int i = 0; i < zähler; i++){
            this.deck.getDiscard().add(handCards.remove(0));
        }
    }

    public Cards getFirstCard(){
        if (this.getDeck().getDeck().size() == 0){
            mischen();
        }
        return this.getDeck().getDeck().remove(0);
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

    public void setStartPointX(int startPointX) {
        this.startPointX = startPointX;
    }

    public void setStartPointY(int startPointY) {
        this.startPointY = startPointY;
    }

    public void setReceivedPunishment(boolean receivedPunishment) {
        this.receivedPunishment = receivedPunishment;
    }

    public void setAbleToChooseRestartDirection(boolean ableToChooseRestartDirection) {
        this.ableToChooseRestartDirection = ableToChooseRestartDirection;
    }

    public void setAbleToChooseDamageCard(boolean ableToChooseDamageCard) {
        this.ableToChooseDamageCard = ableToChooseDamageCard;
    }

    public void setAbleToReturnCard(boolean ableToReturnCard) {
        this.ableToReturnCard = ableToReturnCard;
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

    public int getStartPointX() {
        return startPointX;
    }

    public int getStartPointY() {
        return startPointY;
    }

    public boolean getAbleForRestart() {
        return ableToChooseRestartDirection;
    }

    public boolean isAbleToChooseDamageCard() {
        return ableToChooseDamageCard;
    }

    public UpgradeCards[] getPermanentCards() {
        return permanentCards;
    }

    public UpgradeCards[] getTemporaryCards() {
        return temporaryCards;
    }

    public boolean isAbleToReturnCard() {
        return ableToReturnCard;
    }

    public boolean isReceivedPunishment() {
        return receivedPunishment;
    }
}

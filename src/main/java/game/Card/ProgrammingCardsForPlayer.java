package game.Card;

import java.util.ArrayList;
import java.util.Collections;

public class ProgrammingCardsForPlayer {

    private ArrayList<Cards> deck = new ArrayList<Cards>();

    public ProgrammingCardsForPlayer(){
        for (int i = 0; i < 5; i++) {
            deck.add(new Move1Card());
        }

        for (int i = 0; i < 3; i++) {
            deck.add(new Move2Card());
        }
        deck.add(new Move3Card());
        for (int i = 0; i < 3; i++) {
            deck.add(new LeftTurnCard());
        }
        for (int i = 0; i < 3; i++) {
            deck.add(new RightTurnCard());
        }
        deck.add(new BackUpCard());
        deck.add(new PowerUpCard());
        deck.add(new AgainCard());
        deck.add(new AgainCard());
        deck.add(new UTurnCard());
        Collections.shuffle(deck);
        Collections.shuffle(deck);
        Collections.shuffle(deck);
    }

    public ArrayList<Cards> getDeck() {
        return deck;
    }
}

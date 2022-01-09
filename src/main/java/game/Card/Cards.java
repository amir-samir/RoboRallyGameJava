package game.Card;

//import game.Gamer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public abstract class Cards {
     public String name;
     int playerId;
     List<Cards> register;
     ArrayList<Cards> programmingCardDeck = new ArrayList<>();
     ArrayList<Cards> discardPile = new ArrayList<>();
     ArrayList<Cards> handCards = new ArrayList<>();
     ArrayList<UpgradeCards> upgradeCards = new ArrayList<>();
     ArrayList<Cards> spamCards = new ArrayList<>();
     ArrayList<Cards> virusCards = new ArrayList<>();
     ArrayList<Cards> wormCards = new ArrayList<>();
     ArrayList<Cards> trojanHorse = new ArrayList<>();
     boolean isCurrentRegister ;
     int currentregister;

    public int getCurrentregister() {
        return currentregister;
    }

    public void setCurrentregister(int currentregister) {
        this.currentregister = currentregister;
    }

    public void addAllCardForPlayer() {

         for (int i = 0; i < 5; i++) {
             programmingCardDeck.add(new Move1Card());
         }
         for (int i = 0; i < 3; i++) {
             programmingCardDeck.add(new Move2Card());
         }
         programmingCardDeck.add(new Move3Card());

         for (int i = 0; i < 3; i++) {
             programmingCardDeck.add(new LeftTurnCard());
         }
         for (int i = 0; i < 3; i++) {
             programmingCardDeck.add(new RightTurnCard());
         }
         programmingCardDeck.add(new BackUpCard());
         programmingCardDeck.add(new PowerUpCard());
         programmingCardDeck.add(new AgainCard());
         programmingCardDeck.add(new AgainCard());
         programmingCardDeck.add(new UTurnCard());
         Collections.shuffle(programmingCardDeck);
     }

     public void initSpamCard() {
         for (int i = 0; i < 38; i++) {
             spamCards.add(new Spam());
         }
     }
    public void initVirusCard() {
        for (int i = 0; i < 18; i++) {
            virusCards.add(new VirusCard());
        }
    }
    public void initWormCard() {
        for (int i = 0; i < 6; i++) {
            wormCards.add(new Worm());
        }
    }
    public void initTrojanCard() {
        for (int i = 0; i < 12; i++) {
            trojanHorse.add(new Trojan());
        }
    }



     public void drawCard() {
         if (programmingCardDeck.size() < 9) {
             for (int i = 0; i < programmingCardDeck.size(); i++) {
                 handCards.add(programmingCardDeck.remove(0));
             }
             Collections.shuffle(discardPile);
             for (int i = 0; i < 9 - handCards.size(); i++) {
                 handCards.add(discardPile.remove(0));
             }
         } else for (int i = 0; i < 9; i++) {
             handCards.add(programmingCardDeck.remove(0));
         }
     }


     public abstract void effect();

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

 }

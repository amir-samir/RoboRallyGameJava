package game.Card;

import game.Gamer;

public class Spam extends DamageCards{
    String name = "SPAM";

    @Override
    public void effect (Gamer gamer) {
        for (int i = 0 ; i < register.size() ; i++) {
            if (register.get(i).getName() .equals( "SPAM")) {
                spamCards.add(Spam.this);
                register.remove(i);
                break;
            }
        }
     Cards currentCard = programmingCardDeck.remove(0);
     register.set(currentregister,currentCard);

    }



    @Override
    public String getName (){return name ;}
}

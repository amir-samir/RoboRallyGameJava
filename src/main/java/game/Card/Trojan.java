package game.Card;

import java.util.concurrent.Callable;

public class Trojan extends DamageCards{
    @Override
    public void effect() {
      Cards card1 = spamCards.remove(0);
      Cards card2 = spamCards.remove(0);
      handCards.add(card1);
      handCards.add(card2);
    }


}

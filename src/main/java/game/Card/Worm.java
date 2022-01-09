package game.Card;

import game.Gamer;

public class Worm extends DamageCards{
    String name = "WORM";
    @Override
    public void effect(Gamer gamer) {
      gamer.reboot();
    }
}

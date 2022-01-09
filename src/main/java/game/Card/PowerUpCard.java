package game.Card;

import game.Gamer;

public class PowerUpCard extends Cards {
    final String description = "Cards.PowerUpCard makes you take 1 energy cube and place it on your player mat.";
    final String name = "PowerUp";



    @Override
    public void effect(Gamer gamer) {
        gamer.getNewEnergycube();

    }
}

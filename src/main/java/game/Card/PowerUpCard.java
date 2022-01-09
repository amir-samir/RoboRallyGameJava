package game.Card;

public class PowerUpCard extends Cards {

    final String description = "Cards.PowerUpCard makes you take 1 energy cube and place it on your player mat.";

    public PowerUpCard(){
        this.setName("PowerUp");
    }

    @Override
    public void effect() {
        //gamer.getNewEnergycube();
    }

}

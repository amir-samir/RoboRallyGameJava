//import game.game.Gamer;

public abstract class Cards {

    public String name;


    /* public void drawCard() {
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

     */


     public abstract void effect(Robot robot, Server server);

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

 }

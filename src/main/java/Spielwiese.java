import java.util.ArrayList;
import java.util.Collections;

public class Spielwiese {

    private ArrayList<BoardElement>[][] map;
    private ArrayList<Cards> cards;

    public Spielwiese(ArrayList<BoardElement>[][] map, ArrayList<String> cards){
        this.map = map;
        this.cards = arrayToList(cards);
    }

    public ArrayList<String> simulate(Robot robot){
        Robot TestRoboter = robot;
        ArrayList<ArrayList<Cards>> possibilities = generatePossibilities();
        ArrayList<Robot> robotResults = new ArrayList<>();
        for (ArrayList<Cards> list: possibilities){
            robotResults.add(testMoveRobot(list, TestRoboter));
        }

        return null;
    }

    public Robot testMoveRobot(ArrayList<Cards> procedure, Robot robot){
        return null;
    }

    public ArrayList<ArrayList<Cards>> generatePossibilities(){
        ArrayList<ArrayList<Cards>> possibilities = new ArrayList<>();
        for (int i = 0; i < 10; i++){
            ArrayList<Cards> playedCards = new ArrayList<>();
            Collections.shuffle(this.cards);
            for (int u = 0; u < 5; u++){
                playedCards.add(cards.get(u));
            }
            if (playedCards.get(0).getName() != "Again") {
                possibilities.add(playedCards);
            }
        }
        return possibilities;
    }

    public ArrayList<Cards> arrayToList (ArrayList<String> array){
        ArrayList<Cards> handCards = new ArrayList<>();
        for (String a: array) {
            switch (a){
                case "MoveI":
                    handCards.add(new Move1Card());
                    break;
                case "MoveII":
                    handCards.add(new Move2Card());
                    break;
                case "MoveIII":
                    handCards.add(new Move3Card());
                    break;
                case "TurnLeft":
                    handCards.add(new LeftTurnCard());
                    break;
                case "TurnRight":
                    handCards.add(new RightTurnCard());
                    break;
                case "UTurn":
                    handCards.add(new UTurnCard());
                    break;
                case "BackUp":
                    handCards.add(new BackUpCard());
                    break;
                case "PowerUp":
                    handCards.add(new PowerUpCard());
                    break;
                case "Again":
                    handCards.add(new AgainCard());
                    break;
                case "Spam":
                    handCards.add(new Spam());
                    break;
                case "Trojan":
                    handCards.add(new Trojan());
                    break;
                case "Worm":
                    handCards.add(new Worm());
                    break;
                case "Virus":
                    handCards.add(new VirusCard());
                    break;
                default:
                    break;
            }
        }
        return handCards;
    }


}

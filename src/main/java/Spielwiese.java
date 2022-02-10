import Messages.Actions.Movement;
import Messages.Actions.Reboot;

import java.util.ArrayList;
import java.util.Collections;

import static java.lang.Math.abs;


/**
 * Diese Klasse wird von der KI verwendet, um die beste Abfolge von spielbaren Karten zu finden.
 * Hierfür werden in dieser Klasse verschiedene Varianten generiert und getestet.
 *
 * @author Amir Azim
 * @author Dairen Gonschior
 * @author Luca Weyhofen
 *
 * @version 2.1
 */
public class Spielwiese {

    private ArrayList<BoardElement>[][] map;
    private ArrayList<Cards> cards;

    /**
     * Dies ist der Konstruktor
     * @param map Die Karte, auf der die Züge simuliert werden sollen
     * @param cards Die Karten, die in einem Zug zur Verfügung stehen
     */
    public Spielwiese(ArrayList<BoardElement>[][] map, ArrayList<String> cards){
        this.map = map;
        this.cards = arrayToList(cards);
    }

    /**
     * Diese Methode berechnet aus einer Menge von Karten die beste Möglichkeit den Zug durchzuführen.
     * @param robot Der Roboter, für den der beste Zug gefunden werden soll
     * @param nextCheckpoint Der nächste CheckPoint, der angesteuert werden soll
     * @return Eine Liste an Karten, die von der KI gespielt werden sollen
     */
    public ArrayList<String> simulate(Robot robot, int nextCheckpoint){
        Robot TestRoboter = robot;
        ArrayList<ArrayList<Cards>> possibilities = generatePossibilities();
        ArrayList<Robot> robotResults = new ArrayList<>();
        if (countDamageCards() >= 4){
            ArrayList<String> ergebnis = new ArrayList<>();
            for (Cards karte: this.cards){
                if (karte.getName().equals("Spam") || karte.getName().equals("Trojan") || karte.getName().equals("Virus") || karte.getName().equals("Worm")){
                    ergebnis.add(karte.getName());
                }
            }
            while (ergebnis.size() < 5){
                ergebnis.add(this.cards.remove(0).getName());
            }
            return ergebnis;
        } else {
            int anzahl = this.cards.size();
            for (int u = 0; u < anzahl; u++){
                if (cards.get(u).getName().equals("Spam") || cards.get(u).getName().equals("Trojan") || cards.get(u).getName().equals("Virus") || cards.get(u).getName().equals("Worm")){
                    this.cards.remove(u);
                    anzahl -= 1;
                    u -= 1;
                }
            }
        }
        for (ArrayList<Cards> list: possibilities){
            Robot robotToTest = copyRobot(robot);
            robotResults.add(testMoveRobot(list, robotToTest));
        }
        int groesse = robotResults.size();
        int besteVersion = 0;
        int checkpointX = searchX("CheckPoint", nextCheckpoint);
        int checkpointY = searchY("CheckPoint", nextCheckpoint);
        for (int i = 1; i < groesse; i++){
            int abstandBesteVersion = abstandBerechnen(robotResults.get(besteVersion), checkpointX, checkpointY);
            int abstandAktuelleVers = abstandBerechnen(robotResults.get(i), checkpointX, checkpointY);
            if (abstandAktuelleVers < abstandBesteVersion && !robotResults.get(i).isReceivedPunishment()){
                besteVersion = i;
            }
        }
        ArrayList<Cards> bestesDeck = possibilities.get(besteVersion);
        ArrayList<String> result = new ArrayList<>();
        for (Cards karte: bestesDeck){
            result.add(karte.getName());
        }
        return result;
    }

    /**
     * Diese Methode berechnet den Abstand zwischen einem Roboter und einer Zielkoordinate
     * @param robot Der Roboter, für den der Abstand bestimmt werden soll
     * @param x Die x-Koordinate des Zielpunktes
     * @param y Die y-Koordinate des Zielpunktes
     * @return Der Abstand zwischen Roboter und Zielpunkt
     */
    public int abstandBerechnen(Robot robot, int x, int y){
        int robotX = robot.getX();
        int robotY = robot.getY();
        int entfernungX = abs(robotX - x);
        int entfernungY = abs(robotY - y);
        int entfernungGesamt = entfernungX + entfernungY;
        return entfernungGesamt;
    }

    /**
     * Diese Methode ermittelt einen Roboter, der bei Durchführung der Kartensequenz herauskommen würde
     * @param procedure Die fünf Karten, die gespielt wurden (und simuliert werden sollen)
     * @param robot Der Roboter, für den der Zug simuliert werden soll
     * @return Der Roboter, der nach der Simulation herauskommt
     */
    public Robot testMoveRobot(ArrayList<Cards> procedure, Robot robot){
        Robot testRoboter = robot;
        for (int i = 0; i < 5; i++){
            Cards activeCard = procedure.get(i);
            testRoboter = manageCard(procedure, activeCard, testRoboter, i);
            testRoboter = manageBoardElement(testRoboter, i);
        }
        return testRoboter;
    }

    /**
     * Hier werden einzelne Karten simuliert.
     * @param list Die gesamte Kartensequenz
     * @param activeCard Die Karte, die simuliert werden soll
     * @param robot Der Roboter, für den die karte simuliert wird
     * @param i Die Stelle der Karte
     * @return Der Roboter, der nach der Simulation herauskommt
     */
    public Robot manageCard(ArrayList<Cards> list, Cards activeCard, Robot robot, int i){
        switch (activeCard.getName()){
            case "MoveI":
                robot = checkMovement(robot, robot.getDirection(), false);
                break;
            case "MoveII":
                robot = checkMovement(robot, robot.getDirection(), false);
                robot = checkMovement(robot, robot.getDirection(), false);
                break;
            case "MoveIII":
                robot = checkMovement(robot, robot.getDirection(), false);
                robot = checkMovement(robot, robot.getDirection(), false);
                robot = checkMovement(robot, robot.getDirection(), false);
                break;
            case "TurnLeft":
                robot = turnRobot("counterclockwise", robot);
                break;
            case "TurnRight":
                robot = turnRobot("clockwise", robot);
                break;
            case "UTurn":
                robot = turnRobot("clockwise", robot);
                robot = turnRobot("clockwise", robot);
                break;
            case "BackUp":
                robot = turnRobot("clockwise", robot);
                robot = turnRobot("clockwise", robot);
                robot = checkMovement(robot, robot.getDirection(), false);
                robot = turnRobot("clockwise", robot);
                robot = turnRobot("clockwise", robot);
                break;
            case "PowerUp":
                break;
            case "Again":
                robot = manageCard(list, list.get(i-1), robot, i-1);
                break;
        }
        return robot;
    }

    /**
     * Hier werden einzelne Elemente der Map simuliert.
     * @param robot Der Roboter, für den das Element der Map simuliert werden soll
     * @param runde Das aktuell aktive Register
     * @return Der Roboter, der nach der Simulation herauskommt
     */
    public Robot manageBoardElement(Robot robot, int runde){
        ArrayList<BoardElement> liste = map[robot.getX()][robot.getY()];
        for (BoardElement element: liste){
            switch (element.getType()){
                case "ConveyorBelt":
                    int count = element.getSpeed();
                    robot = checkConveyor(robot);
                    if (count > 1){
                        robot = checkConveyor(robot);
                    }
                    break;
                case "Pit":
                    robot = reboot(robot, "egal");
                    break;
                case "Gear":
                    robot = turnRobot(element.getOrientations()[0], robot);
                    break;
                case "PushPanel":
                    boolean correct = false;
                    for (int zahl: element.getRegisters()){
                        if (zahl == runde){
                            correct = true;
                        }
                    }
                    if (correct){
                        robot = checkMovement(robot, element.getOrientations()[0], true);
                    }
                    break;
                default:
                    return robot;
            }
        }
        return robot;
    }

    /**
     *
     * @param direction
     * @param robot
     * @return
     */
    public Robot turnRobot(String direction, Robot robot){
        switch (robot.getDirection()){
            case "top":
                if (direction.equals("clockwise")){
                    robot.setDirection("right");
                } else robot.setDirection("left");
                break;
            case "bottom":
                if (direction.equals("clockwise")){
                    robot.setDirection("left");
                } else robot.setDirection("right");
                break;
            case "left":
                if (direction.equals("clockwise")){
                    robot.setDirection("top");
                } else robot.setDirection("bottom");
                break;
            case "right":
                if (direction.equals("clockwise")){
                    robot.setDirection("bottom");
                } else robot.setDirection("top");
                break;
        }
        return robot;
    }

    public Robot checkMovement(Robot robot, String direction, boolean necessary){
        switch (direction) {
            case "top":
                if (!robot.getDead() || necessary) {
                    for (BoardElement list : map[robot.getX()][robot.getY()]) {
                        if (list.getType().equals("Wall")) {
                            for (String s : list.getOrientations()) {
                                if (s.equals("top")) return robot;
                            }
                        }
                    }
                    if (robot.getX() - 1 < 0) {
                        robot = reboot(robot, null);
                        return robot;
                    }
                    for (BoardElement element : map[robot.getX() - 1][robot.getY()]) {
                        if (element.getType().equals("Wall")) {
                            for (String s : element.getOrientations()) {
                                if (s.equals("bottom")) return robot;
                            }
                        } else if (element.getType().equals("Antenna")) return robot;
                        else if (element.getType().equals("Pit")) {
                            robot = reboot(robot, null);
                            return robot;
                        }
                    }
                    robot.setX(robot.getX() - 1);
                    return robot;
                } else return robot;
            case "bottom":
                if (!robot.getDead() || necessary) {
                    for (BoardElement list : map[robot.getX()][robot.getY()]) {
                        if (list.getType().equals("Wall")) {
                            for (String s : list.getOrientations()) {
                                if (s.equals("bottom")) return robot;
                            }
                        }
                    }
                    if (robot.getX() + 1 >= 10) {
                        robot = reboot(robot, null);
                        return robot;
                    }
                    for (BoardElement element : map[robot.getX() + 1][robot.getY()]) {
                        if (element.getType().equals("Wall")) {
                            for (String s : element.getOrientations()) {
                                if (s.equals("top")) return robot;
                            }
                        } else if (element.getType().equals("Antenna")) return robot;
                        else if (element.getType().equals("Pit")) {
                            reboot(robot, null);
                            return robot;
                        }
                    }
                    robot.setX(robot.getX() + 1);
                    return robot;
                } else return robot;
            case "left":
                if (!robot.getDead() || necessary) {
                    for (BoardElement list : map[robot.getX()][robot.getY()]) {
                        if (list.getType().equals("Wall")) {
                            for (String s : list.getOrientations()) {
                                if (s.equals("left")) return robot;
                            }
                        }
                    }
                    if (robot.getY() - 1 < 0) {
                        reboot(robot, null);
                        return robot;
                    }
                    for (BoardElement element : map[robot.getX()][robot.getY() - 1]) {
                        if (element.getType().equals("Wall")) {
                            for (String s : element.getOrientations()) {
                                if (s.equals("right")) return robot;
                            }
                        } else if (element.getType().equals("Antenna")) return robot;
                        else if (element.getType().equals("Pit")) {
                            robot = reboot(robot, null);
                            return robot;
                        }
                    }
                    robot.setY(robot.getY() - 1);
                    return robot;
                } else return robot;
            case "right":
                if (!robot.getDead() ||necessary) {
                    for (BoardElement list : map[robot.getX()][robot.getY()]) {
                        if (list.getType().equals("Wall")) {
                            for (String s : list.getOrientations()) {
                                if (s.equals("right")) return robot;
                            }
                        }
                    }
                    if (robot.getY() + 1 >= 13) {
                        robot = reboot(robot, "egal");
                        return robot;
                    }
                    for (BoardElement element : map[robot.getX()][robot.getY() + 1]) {
                        if (element.getType().equals("Wall")) {
                            for (String s : element.getOrientations()) {
                                if (s.equals("left")) return robot;
                            }
                        } else if (element.getType().equals("Antenna")) return robot;
                        else if (element.getType().equals("Pit")) {
                            robot = reboot(robot, null);
                            return robot;
                        }
                    }
                    robot.setY(robot.getY() + 1);
                    return robot;
                } else return robot;
            default:
                return robot;
        }
    }

    public Robot checkConveyor(Robot robot){
        try {
            String robotDirection = robot.getDirection();
            BoardElement conveyor = null;
            for (BoardElement element : map[robot.getX()][robot.getY()]) {
                if (element.getType().equals("ConveyorBelt")) {
                    conveyor = element;
                }
            }
            BoardElement next = null;
            if (conveyor != null) {
                switch (conveyor.getOrientations()[0]) {
                    case "top":
                        if (robot.getX() - 1 < 0) {
                            robot = reboot(robot, conveyor.getIsOnBoard());
                            return robot;
                        }
                        for (BoardElement boardElement : map[robot.getX() - 1][robot.getY()]) {
                            if (boardElement.getType().equals("ConveyorBelt") || boardElement.getType().equals("Pit")) {
                                next = boardElement;
                            }
                        }
                        if (next != null) {
                            if (next.getType().equals("Pit")) {
                                robot = reboot(robot, next.getIsOnBoard());
                                return robot;
                            } else {
                                if (next.getOrientations()[0].equals("top")) {
                                    robot.setX(robot.getX() - 1);
                                    return robot;
                                } else if (next.getOrientations()[0].equals("left")) {
                                    robot.setX(robot.getX() - 1);
                                    robot = turnRobot("counterclockwise", robot);
                                    return robot;
                                } else if (next.getOrientations()[0].equals("right")) {
                                    robot.setX(robot.getX() - 1);
                                    robot = turnRobot("clockwise", robot);
                                    return robot;
                                } else {
                                    //Pfeile zueinander?! was passiert?
                                    //--> Simon!! Existiert der Fall überhaupt? Fehler in der Map?
                                }
                            }
                        } else {
                            robot.setX(robot.getX() - 1);
                            return robot;
                        }
                        break;
                    case "bottom":
                        if (robot.getX() + 1 >= 10) {
                            robot = reboot(robot, conveyor.getIsOnBoard());
                            return robot;
                        }
                        for (BoardElement boardElement : map[robot.getX() + 1][robot.getY()]) {
                            if (boardElement.getType().equals("ConveyorBelt") || boardElement.getType().equals("Pit")) {
                                next = boardElement;
                            }
                        }
                        if (next != null) {
                            if (next.getType().equals("Pit")) {
                                robot = reboot(robot, next.getIsOnBoard());
                                return robot;
                            } else {
                                if (next.getOrientations()[0].equals("bottom")) {
                                    robot.setX(robot.getX() + 1);
                                    return robot;
                                } else if (next.getOrientations()[0].equals("right")) {
                                    robot.setX(robot.getX() + 1);
                                    robot = turnRobot("counterclockwise", robot);
                                    return robot;
                                } else if (next.getOrientations()[0].equals("left")) {
                                    robot.setX(robot.getX() + 1);
                                    robot = turnRobot("clockwise", robot);
                                    return robot;
                                } else {
                                    //Pfeile zueinander?! was passiert?
                                    //--> Simon!! Existiert der Fall überhaupt? Fehler in der Map?
                                }
                            }
                        } else {
                            robot.setX(robot.getX() + 1);
                            return robot;
                        }
                        break;
                    case "left":
                        if (robot.getY() - 1 < 0) {
                            robot = reboot(robot, conveyor.getIsOnBoard());
                            return robot;
                        }
                        for (BoardElement boardElement : map[robot.getX()][robot.getY() - 1]) {
                            if (boardElement.getType().equals("ConveyorBelt") || boardElement.getType().equals("Pit")) {
                                next = boardElement;
                            }
                        }
                        if (next != null) {
                            if (next.getType().equals("Pit")) {
                                robot = reboot(robot, next.getIsOnBoard());
                                return robot;
                            } else {
                                if (next.getOrientations()[0].equals("left")) {
                                    robot.setY(robot.getY() - 1);
                                    return robot;
                                } else if (next.getOrientations()[0].equals("bottom")) {
                                    robot.setY(robot.getY() - 1);
                                    robot = turnRobot("counterclockwise", robot);
                                    return robot;
                                } else if (next.getOrientations()[0].equals("top")) {
                                    robot.setY(robot.getX() - 1);
                                    robot = turnRobot("clockwise", robot);
                                    return robot;
                                } else {
                                    //Pfeile zueinander?! was passiert?
                                    //--> Simon!! Existiert der Fall überhaupt? Fehler in der Map?
                                }
                            }
                        } else {
                            robot.setY(robot.getY() - 1);
                            return robot;
                        }
                        break;
                    case "right":
                        if (robot.getY() + 1 >= 13) {
                            robot = reboot(robot, conveyor.getIsOnBoard());
                            return robot;
                        }
                        for (BoardElement boardElement : map[robot.getX()][robot.getY() + 1]) {
                            if (boardElement.getType().equals("ConveyorBelt") || boardElement.getType().equals("Pit")) {
                                next = boardElement;
                            }
                        }
                        if (next != null) {
                            if (next.getType().equals("Pit")) {
                                robot = reboot(robot, next.getIsOnBoard());
                                return robot;
                            } else {
                                if (next.getOrientations()[0].equals("right")) {
                                    robot.setY(robot.getY() + 1);
                                    return robot;
                                } else if (next.getOrientations()[0].equals("top")) {
                                    robot.setY(robot.getY() + 1);
                                    robot = turnRobot("counterclockwise", robot);
                                    return robot;
                                } else if (next.getOrientations()[0].equals("bottom")) {
                                    robot.setY(robot.getX() + 1);
                                    robot = turnRobot("clockwise", robot);
                                    return robot;
                                } else {
                                    //Pfeile zueinander?! was passiert?
                                    //--> Simon!! Existiert der Fall überhaupt? Fehler in der Map?
                                }
                            }
                        } else {
                            robot.setY(robot.getY() + 1);
                            return robot;
                        }
                        break;
                    default:
                        return robot;
                }
            } else return robot;
            return robot;
        } catch (Exception e) {
            e.printStackTrace();
            return robot;
        }
    }

    public Robot reboot(Robot robot, String isOnBoard){
        robot.setX(searchX("RestartPoint"));
        robot.setY(searchY("RestartPoint"));
        robot.setDead(true);
        robot.setReceivedPunishment(true);
        return robot;
    }

    public ArrayList<ArrayList<Cards>> generatePossibilities(){
        ArrayList<ArrayList<Cards>> possibilities = new ArrayList<>();
        for (int i = 0; i < 150000; i++){
            ArrayList<Cards> playedCards = new ArrayList<>();
            Collections.shuffle(this.cards);
            for (int u = 0; u < 5; u++){
                playedCards.add(this.cards.get(u));
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

    public int countDamageCards(){
        int zähler = 0;
        for (Cards card: this.cards){
            if (card.getName().equals("Spam") || card.getName().equals("Trojan") || card.getName().equals("Virus") || card.getName().equals("Worm")){
                zähler += 1;
            }
        }
        return zähler;
    }

    public int searchX(String element) {
        for (int i = 0; i < map.length; i++) {
            for (int u = 0; u < map[i].length; u++) {
                for (BoardElement boardElement : map[i][u]) {
                    if (boardElement.getType().equals(element)) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public int searchX(String element, int index) {
        for (int i = 0; i < map.length; i++) {
            for (int u = 0; u < map[i].length; u++) {
                for (BoardElement boardElement : map[i][u]) {
                    if (boardElement.getType().equals(element) && boardElement.getCount() == index) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public int searchY(String element) {
        for (int i = 0; i < map.length; i++) {
            for (int u = 0; u < map[i].length; u++) {
                for (BoardElement boardElement : map[i][u]) {
                    if (boardElement.getType().equals(element)) {
                        return u;
                    }
                }
            }
        }
        return -1;
    }

    public int searchY(String element, int index) {
        for (int i = 0; i < map.length; i++) {
            for (int u = 0; u < map[i].length; u++) {
                for (BoardElement boardElement : map[i][u]) {
                    if (boardElement.getType().equals(element) && boardElement.getCount() == index) {
                        return u;
                    }
                }
            }
        }
        return -1;
    }

    public Robot copyRobot(Robot toCopy){
        Robot ergebnis = new Robot(toCopy.getGamerID());
        ergebnis.setDirection(toCopy.getDirection());
        ergebnis.setX(toCopy.getX());
        ergebnis.setY(toCopy.getY());
        ergebnis.setDead(toCopy.getDead());
        ergebnis.setReceivedPunishment(toCopy.isReceivedPunishment());
        return ergebnis;
    }
}

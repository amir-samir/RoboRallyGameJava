public class Empty extends BoardElement {

    public Empty(String isOnBoard) {
        this.setType("Empty");
        this.setIsOnBoard(isOnBoard);
    }

    @Override
    public void effect(Robot robot, Server server) {

    }

}


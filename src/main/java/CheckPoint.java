public class CheckPoint extends BoardElement {

    int count;

    public CheckPoint(String isOnBoard, int count) {
        this.setType("CheckPoint");
        this.setIsOnBoard(isOnBoard);
        this.count = count;
    }

    @Override
    public void effect(Robot robot, Server server) {

    }

    @Override
    public int getCount() {
        return count;
    }
}


package game;

import lombok.Data;

/**
 * @author: yilu ,chen
 * @Date: 2021/12/29
 */
@Data

public class Robot {

    private String name;
    private int gamerID;
    private int x;
    private int y;
    private int startingPoint;
    private String direction;
    private boolean isDead;
    private boolean isOnEnergySpace;
    private boolean isOnBelt;

    // 0 upward; 1 face to right; 2 face to down; 3 face to left;
    // forward(-1) = backup(1)
    public void forward(int step) {
        switch (this.direction) {
            case "0":
                x += step;
                break;
            case "1":
                x += step;
                break;
            case "2":
                y -= step;
                break;
            case "3":
                y -= step;
                break;
        }
    }

    //rotate
    // @param: right rotate angle
    public void rotate(int angle) {
        this.direction = String.valueOf((Integer.parseInt(this.direction) + (angle / 90)) % 4);
    }


    public void moveTo(String newDirection, int step, boolean keepDirection) {
        String temp = this.direction;
        this.direction = newDirection;
        forward(step);
        if (keepDirection) {
            this.direction = temp;
        }
    }

    /**

    public void moveInBelt(BeltEffect effect) {
        String temp = this.direction;
        this.direction = effect.getDirection();
        forward(effect.getStep());
        if (effect.isKeepDirection()) {
            this.direction = temp;
        }
    }
     */
}

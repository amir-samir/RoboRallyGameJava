package game.Board;

import lombok.Data;

/**
 * @Author: yilu ye
 * @Date: 2022/1/4 9:08
 */
@Data
public class BeltEffect {
    private String direction;
    private int step;
    private boolean keepDirection;

    public BeltEffect(String direction, int step, boolean keepDirection) {
        this.direction = direction;
        this.step = step;
        this.keepDirection = keepDirection;
    }
}


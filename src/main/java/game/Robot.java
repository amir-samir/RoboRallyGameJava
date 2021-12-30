package game;

import lombok.Data;

/**
 * @author: yiluye
 * @Date: 2021/12/29
 */
@Data

public class Robot {
    private int x;
    private int y;
    private String direction;

    // 0→ 1← 2↑ 3↓
    public void forward(int step) {
        switch (this.direction) {
            case "0":
                x += step;
                break;
            case "1":
                x -= step;
                break;
            case "2":
                y += step;
                break;
            case "3":
                y -= step;
                break;
        }
    }
}

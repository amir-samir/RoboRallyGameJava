package game.Card;

import lombok.Data;

/**
 * @Author: Yilu Ye
 * @Date: 2021/12/22 21:18
 */
@Data
public class CardUtil {

    public static DamageCards getDamageCard() {
        double random = Math.random() * 4;
        // [0,4)

        if (random <= 1) {
            // [0,1]
            return new VirusCard();
        } else if (random <= 2) {
            //(1,2]
            return new Worm();
        } else if (random <= 3) {
            //(2,3]
            return new Spam();
        } else { //(3,4)
            return new Trojan();
        }
    }
}

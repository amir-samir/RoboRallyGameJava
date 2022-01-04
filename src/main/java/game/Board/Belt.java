package game.Board;
import java.util.Map;

public class Belt extends BoardElement {
    private String name = "Belt";

    public Belt(Map<String, BeltEffect> beltEffect) {
        this.setBeltEffect(beltEffect);
    }

}

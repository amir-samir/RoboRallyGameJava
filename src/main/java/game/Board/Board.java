package game.Board;

import com.google.gson.Gson;
import lombok.Data;

@Data
public abstract class Board {
    private String name;
    private int width;
    private int height;
    private BoardElement[][] map;

    public Board() {
        this.map = new BoardElement[this.width][this.height];
    }

    public Board(String jsonStr) {
        // verwandlte in client
        Gson gson = new Gson();
    }

}

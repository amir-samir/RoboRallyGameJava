package game.Board;

import com.google.gson.Gson;
import lombok.Data;

import java.util.ArrayList;

@Data
public abstract class Board {
    private String name;
    private int width;
    private int height;
    private ArrayList<BoardElement>[][] map;
    public String json;

    public void setMap(ArrayList<BoardElement>[][] map) {
        this.map = map;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setJson(String json) {
        this.json = json;
    }
}


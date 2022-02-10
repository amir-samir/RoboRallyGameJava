import lombok.Data;

import java.util.ArrayList;

/**
 * Class to create a board from a map object
 *Overarching class for maps.
 * @author yiluye
 */
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

    /**
     * Gibt die X Koordinate von einem BoardElement zurück
     * @param element
     * @return X
     */
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

    /**
     * Gibt die Y Koordinate von einem BoardElement zurück
     * @param element
     * @return Y
     */
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
}



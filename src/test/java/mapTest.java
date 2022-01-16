import com.google.gson.internal.LinkedTreeMap;
import Messages.Adopter;
import Messages.Message;
import org.junit.Test;

import java.util.ArrayList;

public class mapTest {

    private ArrayList<BoardElement>[][] map;

    public void generateMap(Message m){
        ArrayList<BoardElement>[][] map = new ArrayList[5][5];
        int i = 0;
        while (i < map.length){
            int u = 0;
            while (u < map[i].length){
                map[i][u] = new ArrayList<BoardElement>();
                u++;
            }
            i++;
        }

        ArrayList<Object> list = (ArrayList<Object>) m.getMessageBody().getContent()[0];
        int x = 0;
        while (x < list.size()){
            ArrayList<Object> y_list = (ArrayList<Object>) list.get(x);
            int y = 0;
            while (y < y_list.size()){
                ArrayList<Object> field = (ArrayList<Object>) y_list.get(y);
                int z = 0;
                while (z < field.size()){
                    LinkedTreeMap<String, Object> typ = (LinkedTreeMap<String, Object>) field.get(z);
                    if (typ == null){
                        map[x][y].add(new Empty("A"));
                    } else {
                        String zuPrüfen = (String) typ.get("type");
                        switch (zuPrüfen) {
                            case "StartPoint":
                                break;
                            case "ConveyorBelt":
                                map[x][y].add(new Empty("A"));
                                break;
                            case "PushPanel":
                                map[x][y].add(new Empty("A"));
                                break;
                            case "Gear":
                                break;
                            case "Pit":
                                break;
                            case "EnergySpace":
                                break;
                            case "Wall":
                                map[x][y].add(new Empty("A"));
                                break;
                            case "Laser":
                                map[x][y].add(new Empty("A"));
                                break;
                            case "Antenna":
                                break;
                            case "CheckPoint":
                                break;
                            case "RestartPoint":
                                break;
                            default:
                        }
                    }
                    z += 1;
                }
                y += 1;
            }
            x += 1;
        }

        System.out.println();
    }

    @Test
    public void Test(){
        String map = "{ \"messageType\": \"GameStarted\",\"messageBody\": {\"gameMap\": [[[{ " +
                "\"type\": \"ConveyorBelt\",\"isOnBoard\": \"1B\",\"speed\": 2,\"orientations\": [" +
                "\"top\",\"right\",\"bottom\"]}],[{\"type\": \"PushPanel\",\"isOnBoard\": \"1B\"," +
                "\"orientations\": [\"left\"],\"registers\": [2, 4]}]],[[{\"type\":\"Wall\"," +
                "\"isOnBoard\":\"4A\",\"orientations\":[\"top\",\"right\"]},{\"type\":\"Laser\"," +
                "\"isOnBoard\":\"4A\",\"orientations\":[\"bottom\"],\"count\":2}],[null]]]}}";

        String json = "{\"messageType\":\"GameStarted\",\"messageBody\":{\"gameMap\":[[[{\"isOnBoard\":\"Start A\",\"type\":" +
                "\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}]" +
                ",[{\"isOnBoard\":\"Start A\",\"type\":\"StartPoint\"}],[{\"orientations\":[\"right\"],\"isOnBoard\":\"Start A\"," +
                "\"type\":\"Antenna\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"StartPoint\"}]," +
                "[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\"," +
                "\"type\":\"Empty\"}]],[[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"StartPoint\"}]," +
                "[{\"orientations\":[\"top\"],\"isOnBoard\":\"Start A\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}]," +
                "[{\"isOnBoard\":\"Start A\",\"type\":\"StartPoint\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"StartPoint\"}],[{\"isOnBoard\":" +
                "\"Start A\",\"type\":\"Empty\"}],[{\"orientations\":[\"bottom\"],\"isOnBoard\":\"Start A\",\"type\":\"Wall\"}],[{\"isOnBoard\":" +
                "\"Start A\",\"type\":\"StartPoint\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}]],[[{\"speed\":1,\"orientations\":[\"right\"," +
                "\"left\"],\"isOnBoard\":\"Start A\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A" +
                "\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"orientations\":[\"right\"],\"isOnBoard\":\"Start A\",\"type\":" +
                "\"Wall\"}],[{\"orientations\":[\"right\"],\"isOnBoard\":\"Start A\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}]," +
                "[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"speed\":1,\"orientations\":[\"right\"," +
                "\"left\"],\"isOnBoard\":\"Start A\",\"type\":\"ConveyorBelt\"}]],[[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":" +
                "\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}]," +
                "[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"right\",\"left\"]," +
                "\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"right\",\"left\"],\"isOnBoard\":\"5B\",\"type\":" +
                "\"ConveyorBelt\"}],[{\"count\":1,\"isOnBoard\":\"5B\",\"type\":\"EnergySpace\"}]],[[{\"speed\":2,\"orientations\":[\"bottom\",\"top\"]," +
                "\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"top\",\"right\"],\"isOnBoard\":\"5B\",\"type" +
                "\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"top\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2," +
                "\"orientations\":[\"bottom\",\"top\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"top\"]," +
                "\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"top\"],\"isOnBoard\":\"5B\",\"type\":" +
                "\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"top\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"left\",\"top\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"right\",\"left\",\"top\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}]],[[{\"speed\":2,\"orientations\":[\"bottom\",\"top\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"left\",\"top\",\"right\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"count\":1,\"isOnBoard\":\"5B\",\"type\":\"EnergySpace\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"right\",\"left\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}]],[[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"left\",\"right\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"orientations\":[\"top\"],\"isOnBoard\":\"5B\",\"type\":\"Wall\"}],[{\"count\":1,\"orientations\":[\"top\"],\"isOnBoard\":\"5B\",\"type\":\"Laser\"},{\"orientations\":[\"bottom\"],\"isOnBoard\":\"5B\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"orientations\":[\"left\"],\"isOnBoard\":\"5B\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"right\",\"left\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}]],[[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"left\",\"right\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"},{\"orientations\":[\"bottom\"],\"isOnBoard\":\"DizzyHighway\",\"type\":\"RestartPoint\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"count\":1,\"isOnBoard\":\"5B\",\"type\":\"EnergySpace\"}],[{\"count\":1,\"orientations\":[\"left\"],\"isOnBoard\":\"5B\",\"type\":\"Laser\"},{\"orientations\":[\"right\"],\"isOnBoard\":\"5B\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"right\",\"left\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}]],[[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"left\",\"right\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"count\":1,\"orientations\":[\"right\"],\"isOnBoard\":\"5B\",\"type\":\"Laser\"},{\"orientations\":[\"left\"],\"isOnBoard\":\"5B\",\"type\":\"Wall\"}],[{\"count\":1,\"isOnBoard\":\"5B\",\"type\":\"EnergySpace\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"right\",\"left\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}]],[[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"left\",\"right\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"orientations\":[\"right\"],\"isOnBoard\":\"5B\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"count\":1,\"orientations\":[\"bottom\"],\"isOnBoard\":\"5B\",\"type\":\"Laser\"},{\"orientations\":[\"top\"],\"isOnBoard\":\"5B\",\"type\":\"Wall\"}],[{\"orientations\":[\"bottom\"],\"isOnBoard\":\"5B\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"right\",\"left\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}]],[[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"left\",\"right\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"count\":1,\"isOnBoard\":\"5B\",\"type\":\"EnergySpace\"}],[{\"speed\":2,\"orientations\":[\"right\",\"bottom\",\"left\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"bottom\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}]],[[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"left\",\"right\",\"bottom\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"right\",\"bottom\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"bottom\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"bottom\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"bottom\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"bottom\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"bottom\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"bottom\",\"left\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"bottom\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}]],[[{\"count\":1,\"isOnBoard\":\"5B\",\"type\":\"EnergySpace\"}],[{\"speed\":2,\"orientations\":[\"left\",\"right\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"left\",\"right\"],\"isOnBoard\":\"5B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"},{\"order\":0,\"isOnBoard\":\"DizzyHighway\",\"type\":\"CheckPoint\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"5B\",\"type\":\"Empty\"}]]]}}";


        Message m = Adopter.getMessage(map);
        Message i = Adopter.getMessage(json);

        Board board = new DeathTrap();
        Message death = Adopter.getMessage(board.json);
        generatingMap(death);
        System.out.println(m.getMessageBody().getContent());

    }

    public ArrayList<BoardElement>[][] generatesMap(Message m) {
            ArrayList<BoardElement>[][] map = new ArrayList[13][10];
            int i = 0;
            while (i < map.length) {
                int u = 0;
                while (u < map[i].length) {
                    map[i][u] = new ArrayList<BoardElement>();
                    u++;
                }
                i++;
            }

            ArrayList<Object> list = (ArrayList<Object>) m.getMessageBody().getContent()[0];
            int x = 0;
            while (x < list.size()) {
                ArrayList<Object> y_list = (ArrayList<Object>) list.get(x);
                int y = 0;
                while (y < y_list.size()) {
                    ArrayList<Object> field = (ArrayList<Object>) y_list.get(y);
                    int z = 0;
                    while (z < field.size()) {
                        LinkedTreeMap<String, Object> typ = (LinkedTreeMap<String, Object>) field.get(z);
                        if (typ == null) {
                            //map[x][y].add(new Empty());
                        } else {
                            String zuPruefen = (String) typ.get("type");
                            switch (zuPruefen) {
                                case "Empty":
                                    map[x][y].add(new Empty("A"));
                                    break;
                                case "StartPoint":
                                    map[x][y].add(new Empty("A"));
                                    break;
                                case "ConveyorBelt":
                                    map[x][y].add(new Empty("A"));
                                    break;
                                case "PushPanel":
                                    map[x][y].add(new Empty("A"));
                                    break;
                                case "Gear":
                                    map[x][y].add(new Empty("A"));
                                    break;
                                case "Pit":
                                    map[x][y].add(new Empty("A"));
                                    break;
                                case "EnergySpace":
                                    map[x][y].add(new Empty("A"));
                                    break;
                                case "Wall":
                                    map[x][y].add(new Empty("A"));
                                    break;
                                case "Laser":
                                    map[x][y].add(new Empty("A"));
                                    break;
                                case "Antenna":
                                    map[x][y].add(new Empty("A"));
                                    break;
                                case "CheckPoint":
                                    map[x][y].add(new Empty("A"));
                                    break;
                                case "RestartPoint":
                                    map[x][y].add(new Empty("A"));
                                    break;
                                default:
                            }
                        }
                        z += 1;
                    }
                    y += 1;
                }
                x += 1;
            }

            System.out.println("test");
            return map;
        }

    public void generatingMap(Message m){
        ArrayList<BoardElement>[][] map = new ArrayList[10][13];
        int i = 0;
        while (i < map.length){
            int u = 0;
            while (u < map[i].length){
                map[i][u] = new ArrayList<BoardElement>();
                u++;
            }
            i++;
        }
        ArrayList<Object> list = (ArrayList<Object>) m.getMessageBody().getContent()[0];
        int x = 0;
        while (x < list.size()){
            ArrayList<Object> y_list = (ArrayList<Object>) list.get(x);
            int y = 0;
            while (y < y_list.size()){
                ArrayList<Object> field = (ArrayList<Object>) y_list.get(y);
                int z = 0;
                while (z < field.size()){
                    LinkedTreeMap<String, Object> typ = (LinkedTreeMap<String, Object>) field.get(z);
                    if (typ == null){
                        map[y][x].add(new Empty("A"));
                    } else {
                        String zuPrüfen = (String) typ.get("type");
                        String[] orientations;
                        switch (zuPrüfen) {
                            case "StartPoint":
                                map[y][x].add(new StartPoint((String) typ.get("isOnBoard")));
                                break;
                            case "ConveyorBelt":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                map[y][x].add(new ConveyorBelt((String) typ.get("isOnBoard"), orientations, (int) (double) typ.get("speed")));
                                break;
                            case "PushPanel":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                ArrayList<Double> list1 = (ArrayList<Double>) typ.get("registers");
                                int[] register = new int[list1.size()];
                                for (int p = 0; p < register.length; p++){
                                    register[p] = (int) (double) list1.remove(0);
                                }
                                map[y][x].add(new PushPanel((String) typ.get("isOnBoard"), orientations, register));
                                break;
                            case "Gear":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                map[y][x].add(new Gear((String) typ.get("isOnBoard"), orientations));
                                break;
                            case "Pit":
                                map[y][x].add(new Pit((String) typ.get("isOnBoard")));
                                break;
                            case "EnergySpace":
                                map[y][x].add(new EnergySpace((String) typ.get("isOnBoard"), (int)(double) typ.get("count")));
                                break;
                            case "Wall":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                map[y][x].add(new Wall((String) typ.get("isOnBoard"), orientations));
                                break;
                            case "Laser":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                map[y][x].add(new Laser((String) typ.get("isOnBoard"), orientations, (int)(double) typ.get("count")));
                                break;
                            case "Antenna":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                map[y][x].add(new Antenna((String) typ.get("isOnBoard"), orientations));
                                break;
                            case "CheckPoint":
                                map[y][x].add(new CheckPoint((String) typ.get("isOnBoard"), (int)(double) typ.get("order")));
                                break;
                            case "RestartPoint":
                                orientations = changeListIntoArray((ArrayList<String>) typ.get("orientations"));
                                map[y][x].add(new RestartPoint((String) typ.get("isOnBoard"), orientations));
                                break;
                            default:
                        }
                    }
                    z += 1;
                }
                y += 1;
            }
            x += 1;
        }
        this.map = map;
    }

    public String[] changeListIntoArray(ArrayList<String> list){
        String[] orientations = new String[list.size()];
        for(int i = 0; i < list.size(); i++){
            orientations[i] = list.get(i);
        }
        return orientations;
    }
}

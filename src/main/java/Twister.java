import java.util.ArrayList;

public class Twister extends Board {

    public Twister() {

            this.setName("Twister");
            this.setHeight(10);
            this.setWidth(13);

            json = "{\"messageType\":\"GameStarted\",\"messageBody\":{\"gameMap\":[[[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"StartPoint\"}],[{\"orientations\":[\"right\"],\"isOnBoard\":\"Start A\",\"type\":\"Antenna\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"},{\"orientations\":[\"right\"],\"isOnBoard\":\"Twister\",\"type\":\"RestartPoint\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"StartPoint\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}]],[[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"StartPoint\"}],[{\"orientations\":[\"top\"],\"isOnBoard\":\"Start A\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"StartPoint\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"StartPoint\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"orientations\":[\"bottom\"],\"isOnBoard\":\"Start A\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"StartPoint\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}]],[[{\"speed\":1,\"orientations\":[\"right\",\"left\"],\"isOnBoard\":\"Start A\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"orientations\":[\"right\"],\"isOnBoard\":\"Start A\",\"type\":\"Wall\"}],[{\"orientations\":[\"right\"],\"isOnBoard\":\"Start A\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"speed\":1,\"orientations\":[\"right\",\"left\"],\"isOnBoard\":\"Start A\",\"type\":\"ConveyorBelt\"}]],[[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"count\":1,\"orientations\":[\"bottom\"],\"isOnBoard\":\"6B\",\"type\":\"Laser\"},{\"orientations\":[\"top\"],\"isOnBoard\":\"6B\",\"type\":\"Wall\"}],[{\"count\":1,\"orientations\":[\"top\"],\"isOnBoard\":\"6B\",\"type\":\"Laser\"},{\"orientations\":[\"bottom\"],\"isOnBoard\":\"6B\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}]],[[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"right\",\"bottom\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"bottom\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"right\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"right\",\"bottom\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"bottom\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"right\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}]],[[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"right\",\"left\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"count\":1,\"isOnBoard\":\"6B\",\"type\":\"EnergySpace\"}],[{\"speed\":2,\"orientations\":[\"left\",\"right\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"},{\"count\":2,\"isOnBoard\":\"Twister\",\"type\":\"CheckPoint\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"right\",\"left\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"count\":1,\"isOnBoard\":\"6B\",\"type\":\"EnergySpace\"}],[{\"speed\":2,\"orientations\":[\"left\",\"right\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}]],[[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"left\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"top\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"left\",\"top\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"left\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"top\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"},{\"count\":1,\"isOnBoard\":\"Twister\",\"type\":\"CheckPoint\"}],[{\"speed\":2,\"orientations\":[\"left\",\"top\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}]],[[{\"count\":1,\"orientations\":[\"right\"],\"isOnBoard\":\"6B\",\"type\":\"Laser\"},{\"orientations\":[\"left\"],\"isOnBoard\":\"6B\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"count\":1,\"isOnBoard\":\"6B\",\"type\":\"EnergySpace\"},{\"orientations\":[\"bottom\"],\"isOnBoard\":\"6B\",\"type\":\"Wall\"}],[{\"orientations\":[\"right\"],\"isOnBoard\":\"6B\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"count\":1,\"orientations\":[\"right\"],\"isOnBoard\":\"6B\",\"type\":\"Laser\"},{\"orientations\":[\"left\"],\"isOnBoard\":\"6B\",\"type\":\"Wall\"}]],[[{\"count\":1,\"orientations\":[\"left\"],\"isOnBoard\":\"6B\",\"type\":\"Laser\"},{\"orientations\":[\"right\"],\"isOnBoard\":\"6B\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"orientations\":[\"left\"],\"isOnBoard\":\"6B\",\"type\":\"Wall\"}],[{\"count\":1,\"isOnBoard\":\"6B\",\"type\":\"EnergySpace\"},{\"orientations\":[\"top\"],\"isOnBoard\":\"6B\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"count\":1,\"orientations\":[\"left\"],\"isOnBoard\":\"6B\",\"type\":\"Laser\"},{\"orientations\":[\"right\"],\"isOnBoard\":\"6B\",\"type\":\"Wall\"}]],[[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"right\",\"bottom\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"bottom\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"right\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"right\",\"bottom\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"bottom\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"},{\"count\":3,\"isOnBoard\":\"Twister\",\"type\":\"CheckPoint\"}],[{\"speed\":2,\"orientations\":[\"top\",\"right\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}]],[[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"right\",\"left\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"},{\"count\":0,\"isOnBoard\":\"Twister\",\"type\":\"CheckPoint\"}],[{\"count\":1,\"isOnBoard\":\"6B\",\"type\":\"EnergySpace\"}],[{\"speed\":2,\"orientations\":[\"left\",\"right\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"right\",\"left\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"count\":1,\"isOnBoard\":\"6B\",\"type\":\"EnergySpace\"}],[{\"speed\":2,\"orientations\":[\"left\",\"right\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}]],[[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"left\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"top\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"left\",\"top\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"left\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"top\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"left\",\"top\"],\"isOnBoard\":\"6B\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}]],[[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"count\":1,\"orientations\":[\"bottom\"],\"isOnBoard\":\"6B\",\"type\":\"Laser\"},{\"orientations\":[\"top\"],\"isOnBoard\":\"6B\",\"type\":\"Wall\"}],[{\"count\":1,\"orientations\":[\"top\"],\"isOnBoard\":\"6B\",\"type\":\"Laser\"},{\"orientations\":[\"bottom\"],\"isOnBoard\":\"6B\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"6B\",\"type\":\"Empty\"}]]]}}";

            this.setJson(json);

        ArrayList<BoardElement>[][] map = new ArrayList[10][13];
            int i = 0;
            while (i < map.length) {
                int u = 0;
                while (u < map[i].length) {
                    map[i][u] = new ArrayList<BoardElement>();
                    u++;
                }
                i++;
            }

            map[0][0].add(new Empty("A"));
            map[0][1].add(new Empty("A"));
            map[0][2].add(new ConveyorBelt("A", new String[]{"right", "left"}, 1));
            map[0][3].add(new Empty("6B"));
            map[0][4].add(new Empty("6B"));
            map[0][5].add(new Empty("6B"));
            map[0][6].add(new Empty("6B"));
            map[0][7].add(new Wall("6B", new String[]{"left"}));
            map[0][8].add(new Wall("6B", new String[]{"right"}));
            map[0][8].add(new Laser("6B", new String[]{"left"}, 1));
            map[0][9].add(new Empty("6B"));
            map[0][10].add(new Empty("6B"));
            map[0][11].add(new Empty("6B"));
            map[0][12].add(new Empty("6B"));

            map[1][0].add(new Empty("A"));
            map[1][1].add(new StartPoint("A"));
            map[1][2].add(new Empty("A"));
            map[1][3].add(new Empty("6B"));
            map[1][4].add(new ConveyorBelt("6B", new String[]{"right", "bottom"}, 2));
            map[1][5].add(new ConveyorBelt("6B", new String[]{"right", "left"}, 2));
            map[1][6].add(new ConveyorBelt("6B", new String[]{"bottom", "left"}, 2));
            map[1][7].add(new Empty("6B"));
            map[1][8].add(new Empty("6B"));
            map[1][9].add(new ConveyorBelt("6B", new String[]{"right", "bottom"}, 2));
            map[1][10].add(new ConveyorBelt("6B", new String[]{"right", "left"}, 2));
            map[1][10].add(new CheckPoint("6B", 0));
            map[1][11].add(new ConveyorBelt("6B", new String[]{"bottom", "left"}, 2));
            map[1][12].add(new Empty("6B"));

            map[2][0].add(new Empty("A"));
            map[2][1].add(new Wall("A", new String[]{"top"}));
            map[2][2].add(new Empty("A"));
            map[2][3].add(new Empty("6B"));
            map[2][4].add(new ConveyorBelt("6B", new String[]{"top", "bottom"}, 2));
            map[2][5].add(new EnergySpace("6B", 1));
            map[2][6].add(new ConveyorBelt("6B", new String[]{"bottom", "top"}, 2));
            map[2][7].add(new Empty("6B"));
            map[2][8].add(new Empty("6B"));
            map[2][9].add(new ConveyorBelt("6B", new String[]{"top", "bottom"}, 2));
            map[2][10].add(new EnergySpace("6B", 1));
            map[2][11].add(new ConveyorBelt("6B", new String[]{"bottom", "top"}, 2));
            map[2][12].add(new Empty("6B"));

            map[3][0].add(new StartPoint("A"));
            map[3][1].add(new Empty("A"));
            map[3][2].add(new Empty("A"));
            map[3][3].add(new Empty("6B"));
            map[3][4].add(new ConveyorBelt("6B", new String[]{"top", "right"}, 2));
            map[3][5].add(new ConveyorBelt("6B", new String[]{"left", "right"}, 2));
            map[3][5].add(new CheckPoint("6B", 2));
            map[3][6].add(new ConveyorBelt("6B", new String[]{"left", "top"}, 2));
            map[3][7].add(new Empty("6B"));
            map[3][8].add(new Empty("6B"));
            map[3][9].add(new ConveyorBelt("6B", new String[]{"top", "right"}, 2));
            map[3][10].add(new ConveyorBelt("6B", new String[]{"left", "right"}, 2));
            map[3][11].add(new ConveyorBelt("6B", new String[]{"left", "top"}, 2));
            map[3][12].add(new Empty("6B"));

            map[4][0].add(new Antenna("A", new String[]{"right"}));
            map[4][1].add(new StartPoint("A"));
            map[4][2].add(new Wall("A", new String[]{"right"}));
            map[4][3].add(new Wall("6B", new String[]{"top"}));
            map[4][3].add(new Laser("6B", new String[]{"bottom"}, 1));
            map[4][4].add(new Empty("6B"));
            map[4][5].add(new Empty("6B"));
            map[4][6].add(new Empty("6B"));
            map[4][7].add(new EnergySpace("6B", 1));
            map[4][7].add(new Wall("6B", new String[]{"bottom"}));
            map[4][8].add(new Wall("6B", new String[]{"left"}));
            map[4][9].add(new Empty("6B"));
            map[4][10].add(new Empty("6B"));
            map[4][11].add(new Empty("6B"));
            map[4][12].add(new Wall("6B", new String[]{"top"}));

            map[5][0].add(new Empty("A"));
            map[5][1].add(new StartPoint("A"));
            map[5][2].add(new Wall("A", new String[]{"right"}));
            map[5][3].add(new Wall("A", new String[]{"bottom"}));
            map[5][4].add(new Empty("6B"));
            map[5][5].add(new Empty("6B"));
            map[5][6].add(new Empty("6B"));
            map[5][7].add(new Wall("6B", new String[]{"right"}));
            map[5][8].add(new EnergySpace("6B", 1));
            map[5][8].add(new Wall("6B", new String[]{"top"}));
            map[5][9].add(new Empty("6B"));
            map[5][10].add(new Empty("6B"));
            map[5][11].add(new Empty("6B"));
            map[5][12].add(new Wall("6B", new String[]{"bottom"}));
            map[5][12].add(new Laser("6B", new String[]{"top"}, 1));

            map[6][0].add(new StartPoint("A"));
            map[6][1].add(new Empty("A"));
            map[6][2].add(new Empty("A"));
            map[6][3].add(new Empty("6B"));
            map[6][4].add(new ConveyorBelt("6B", new String[]{"right", "bottom"}, 2));
            map[6][5].add(new ConveyorBelt("6B", new String[]{"right", "left"}, 2));
            map[6][6].add(new ConveyorBelt("6B", new String[]{"bottom", "left"}, 2));
            map[6][7].add(new Empty("6B"));
            map[6][8].add(new Empty("6B"));
            map[6][9].add(new ConveyorBelt("6B", new String[]{"right", "bottom"}, 2));
            map[6][10].add(new ConveyorBelt("6B", new String[]{"right", "left"}, 2));
            map[6][11].add(new ConveyorBelt("6B", new String[]{"bottom", "left"}, 2));
            map[6][12].add(new Empty("6B"));

            map[7][0].add(new RestartPoint("A", new String[]{"right"}));
            map[7][1].add(new Wall("A", new String[]{"bottom"}));
            map[7][2].add(new Empty("A"));
            map[7][3].add(new Empty("6B"));
            map[7][4].add(new ConveyorBelt("6B", new String[]{"top", "bottom"}, 2));
            map[7][5].add(new EnergySpace("6B", 1));
            map[7][6].add(new ConveyorBelt("6B", new String[]{"bottom", "top"}, 2));
            map[7][6].add(new CheckPoint("6B", 1));
            map[7][7].add(new Empty("6B"));
            map[7][8].add(new Empty("6B"));
            map[7][9].add(new CheckPoint("6B", 3));
            map[7][9].add(new ConveyorBelt("6B", new String[]{"top", "bottom"}, 2));
            map[7][10].add(new EnergySpace("6B", 1));
            map[7][11].add(new ConveyorBelt("6B", new String[]{"bottom", "top"}, 2));
            map[7][12].add(new Empty("6B"));

            map[8][0].add(new Empty("A"));
            map[8][1].add(new StartPoint("A"));
            map[8][2].add(new Empty("A"));
            map[8][3].add(new Empty("6B"));
            map[8][4].add(new ConveyorBelt("6B", new String[]{"top", "right"}, 2));
            map[8][5].add(new ConveyorBelt("6B", new String[]{"left", "right"}, 2));
            map[8][6].add(new ConveyorBelt("6B", new String[]{"left", "top"}, 2));
            map[8][7].add(new Empty("6B"));
            map[8][8].add(new Empty("6B"));
            map[8][9].add(new ConveyorBelt("6B", new String[]{"top", "right"}, 2));
            map[8][10].add(new ConveyorBelt("6B", new String[]{"left", "right"}, 2));
            map[8][11].add(new ConveyorBelt("6B", new String[]{"left", "top"}, 2));
            map[8][12].add(new Empty("6B"));

            map[9][0].add(new Empty("A"));
            map[9][1].add(new Empty("A"));
            map[9][2].add(new ConveyorBelt("A", new String[]{"right", "left"}, 1));
            map[9][3].add(new Empty("6B"));
            map[9][4].add(new Empty("6B"));
            map[9][5].add(new Empty("6B"));
            map[9][6].add(new Empty("6B"));
            map[9][7].add(new Wall("6B", new String[]{"left"}));
            map[9][7].add(new Laser("6B", new String[]{"right"}, 1));
            map[9][8].add(new Wall("6B", new String[]{"right"}));
            map[9][9].add(new Empty("6B"));
            map[9][10].add(new Empty("6B"));
            map[9][11].add(new Empty("6B"));
            map[9][12].add(new Empty("6B"));

            this.setMap(map);
        }
    }


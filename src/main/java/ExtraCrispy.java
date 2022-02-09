import java.util.ArrayList;

public class ExtraCrispy extends Board{

    public ExtraCrispy() {
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

        String json = "{\"messageType\":\"GameStarted\",\"messageBody\":{\"gameMap\":[[[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"},{\"orientations\":[\"right\"],\"isOnBoard\":\"ExtraCrispy\",\"type\":\"RestartPoint\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"StartPoint\"}],[{\"orientations\":[\"right\"],\"isOnBoard\":\"Start A\",\"type\":\"Antenna\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"StartPoint\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}]],[[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"StartPoint\"}],[{\"orientations\":[\"top\"],\"isOnBoard\":\"Start A\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"StartPoint\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"StartPoint\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"orientations\":[\"bottom\"],\"isOnBoard\":\"Start A\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"StartPoint\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}]],[[{\"speed\":1,\"orientations\":[\"right\",\"left\"],\"isOnBoard\":\"Start A\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"orientations\":[\"right\"],\"isOnBoard\":\"Start A\",\"type\":\"Wall\"}],[{\"orientations\":[\"right\"],\"isOnBoard\":\"Start A\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"Start A\",\"type\":\"Empty\"}],[{\"speed\":1,\"orientations\":[\"right\",\"left\"],\"isOnBoard\":\"Start A\",\"type\":\"ConveyorBelt\"}]],[[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"count\":1,\"isOnBoard\":\"4A\",\"type\":\"EnergySpace\"},{\"orientations\":[\"top\"],\"isOnBoard\":\"4A\",\"type\":\"Wall\"}],[{\"orientations\":[\"bottom\"],\"isOnBoard\":\"4A\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"count\":1,\"isOnBoard\":\"4A\",\"type\":\"EnergySpace\"}]],[[{\"speed\":2,\"orientations\":[\"bottom\",\"top\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"top\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"top\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"right\",\"top\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"right\",\"bottom\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"bottom\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"bottom\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"orientations\":[\"left\"],\"isOnBoard\":\"4A\",\"type\":\"Wall\"},{\"count\":1,\"orientations\":[\"right\"],\"isOnBoard\":\"4A\",\"type\":\"Laser\"}]],[[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"orientations\":[\"top\"],\"isOnBoard\":\"4A\",\"type\":\"Wall\"},{\"count\":1,\"orientations\":[\"bottom\"],\"isOnBoard\":\"4A\",\"type\":\"Laser\"},{\"order\":3,\"isOnBoard\":\"ExtraCrispy\",\"type\":\"CheckPoint\"}],[{\"speed\":2,\"orientations\":[\"right\",\"left\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"right\",\"left\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"orientations\":[\"bottom\"],\"isOnBoard\":\"4A\",\"type\":\"Wall\"},{\"count\":1,\"orientations\":[\"top\"],\"isOnBoard\":\"4A\",\"type\":\"Laser\"},{\"order\":1,\"isOnBoard\":\"ExtraCrispy\",\"type\":\"CheckPoint\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}]],[[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"speed\":1,\"orientations\":[\"bottom\",\"right\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Pit\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Pit\"}],[{\"orientations\":[\"clockwise\"],\"isOnBoard\":\"4A\",\"type\":\"Gear\"}],[{\"orientations\":[\"left\"],\"isOnBoard\":\"4A\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Pit\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Pit\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"orientations\":[\"right\"],\"isOnBoard\":\"4A\",\"type\":\"Wall\"},{\"count\":1,\"orientations\":[\"left\"],\"isOnBoard\":\"4A\",\"type\":\"Laser\"}]],[[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"speed\":1,\"orientations\":[\"left\",\"right\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"orientations\":[\"left\"],\"isOnBoard\":\"4A\",\"type\":\"Wall\"},{\"count\":1,\"orientations\":[\"right\"],\"isOnBoard\":\"4A\",\"type\":\"Laser\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"count\":1,\"isOnBoard\":\"4A\",\"type\":\"EnergySpace\"}],[{\"orientations\":[\"left\"],\"isOnBoard\":\"4A\",\"type\":\"Wall\"},{\"count\":1,\"orientations\":[\"right\"],\"isOnBoard\":\"4A\",\"type\":\"Laser\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}]],[[{\"count\":1,\"isOnBoard\":\"4A\",\"type\":\"EnergySpace\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"orientations\":[\"right\"],\"isOnBoard\":\"4A\",\"type\":\"Wall\"},{\"count\":1,\"orientations\":[\"left\"],\"isOnBoard\":\"4A\",\"type\":\"Laser\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"orientations\":[\"right\"],\"isOnBoard\":\"4A\",\"type\":\"Wall\"},{\"count\":1,\"orientations\":[\"left\"],\"isOnBoard\":\"4A\",\"type\":\"Laser\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"speed\":1,\"orientations\":[\"right\",\"left\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}]],[[{\"orientations\":[\"left\"],\"isOnBoard\":\"4A\",\"type\":\"Wall\"},{\"count\":1,\"orientations\":[\"right\"],\"isOnBoard\":\"4A\",\"type\":\"Laser\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Pit\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Pit\"}],[{\"orientations\":[\"right\"],\"isOnBoard\":\"4A\",\"type\":\"Wall\"}],[{\"orientations\":[\"counterclockwise\"],\"isOnBoard\":\"4A\",\"type\":\"Gear\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Pit\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Pit\"}],[{\"speed\":1,\"orientations\":[\"top\",\"left\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}]],[[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"orientations\":[\"top\"],\"isOnBoard\":\"4A\",\"type\":\"Wall\"},{\"count\":1,\"orientations\":[\"bottom\"],\"isOnBoard\":\"4A\",\"type\":\"Laser\"},{\"order\":0,\"isOnBoard\":\"ExtraCrispy\",\"type\":\"CheckPoint\"}],[{\"speed\":2,\"orientations\":[\"left\",\"right\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"left\",\"right\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"orientations\":[\"bottom\"],\"isOnBoard\":\"4A\",\"type\":\"Wall\"},{\"count\":1,\"orientations\":[\"top\"],\"isOnBoard\":\"4A\",\"type\":\"Laser\"},{\"order\":2,\"isOnBoard\":\"ExtraCrispy\",\"type\":\"CheckPoint\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}]],[[{\"orientations\":[\"right\"],\"isOnBoard\":\"4A\",\"type\":\"Wall\"},{\"count\":1,\"orientations\":[\"left\"],\"isOnBoard\":\"4A\",\"type\":\"Laser\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"top\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"top\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"left\",\"top\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"count\":1,\"isOnBoard\":\"4A\",\"type\":\"EnergySpace\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"speed\":2,\"orientations\":[\"bottom\",\"left\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"bottom\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"bottom\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}],[{\"speed\":2,\"orientations\":[\"top\",\"bottom\"],\"isOnBoard\":\"4A\",\"type\":\"ConveyorBelt\"}]],[[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"orientations\":[\"top\"],\"isOnBoard\":\"4A\",\"type\":\"Wall\"}],[{\"orientations\":[\"bottom\"],\"isOnBoard\":\"4A\",\"type\":\"Wall\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}],[{\"isOnBoard\":\"4A\",\"type\":\"Empty\"}]]]}}";
        this.setJson(json);
        this.setName("ExtraCrispy");
        this.setHeight(10);
        this.setWidth(13);

        map[0][0].add(new RestartPoint("A", new String[]{"right"}));
        map[0][1].add(new Empty("A"));
        map[0][2].add(new ConveyorBelt("A", new String[]{"right", "left"}, 1));
        map[0][3].add(new Empty("4A"));
        map[0][4].add(new ConveyorBelt("4A", new String[]{"bottom", "top"}, 2));
        map[0][5].add(new Empty("4A"));
        map[0][6].add(new Empty("4A"));
        map[0][7].add(new Empty("4A"));
        map[0][8].add(new EnergySpace("4A", 1));
        map[0][9].add(new Wall("4A", new String[]{"left"}));
        map[0][9].add(new Laser("4A", new String[]{"right"},1));
        map[0][10].add(new Empty("4A"));
        map[0][11].add(new Laser("4A", new String[]{"left"},1));
        map[0][11].add(new Wall("4A", new String[]{"right"}));
        map[0][12].add(new Empty("4A"));

        map[1][0].add(new Empty("A"));
        map[1][1].add(new StartPoint("A"));
        map[1][2].add(new Empty("A"));
        map[1][3].add(new Empty("4A"));
        map[1][4].add(new ConveyorBelt("4A", new String[]{"bottom", "top"}, 2));
        map[1][5].add(new Empty("4A"));
        map[1][6].add(new ConveyorBelt("4A", new String[]{"bottom", "right"}, 1));
        map[1][7].add(new ConveyorBelt("4A", new String[]{"left", "right"}, 1));
        map[1][8].add(new Empty("4A"));
        map[1][9].add(new Empty("4A"));
        map[1][10].add(new Empty("4A"));
        map[1][11].add(new ConveyorBelt("4A", new String[]{"bottom", "top"},2));
        map[1][12].add(new Empty("4A"));

        map[2][0].add(new Empty("A"));
        map[2][1].add(new Wall("A", new String[]{"top"}));
        map[2][2].add(new Empty("A"));
        map[2][3].add(new Empty("4A"));
        map[2][4].add(new ConveyorBelt("4A", new String[]{"bottom", "top"}, 2));
        map[2][5].add(new CheckPoint("4A",3));
        map[2][5].add(new Wall("4A", new String[]{"top"}));
        map[2][5].add(new Laser("4A", new String[]{"bottom"},1));
        map[2][6].add(new Pit("4A"));
        map[2][7].add(new Empty("4A"));
        map[2][8].add(new Empty("4A"));
        map[2][9].add(new Pit("4A"));
        map[2][10].add(new CheckPoint("4A",0));
        map[2][10].add(new Wall("4A", new String[]{"top"}));
        map[2][10].add(new Laser("4A", new String[]{"bottom"},1));
        map[2][11].add(new ConveyorBelt("4A", new String[]{"bottom", "top"},2));
        map[2][12].add(new Empty("4A"));

        map[3][0].add(new StartPoint("A"));
        map[3][1].add(new Empty("A"));
        map[3][2].add(new Empty("A"));
        map[3][3].add(new Empty("4A"));
        map[3][4].add(new ConveyorBelt("4A", new String[]{"right", "top"},2));
        map[3][5].add(new ConveyorBelt("4A", new String[]{"right", "left"},2));
        map[3][6].add(new Pit("4A"));
        map[3][7].add(new Wall("4A", new String[]{"left"}));
        map[3][7].add(new Laser("4A", new String[]{"right"},1));
        map[3][8].add(new Wall("4A", new String[]{"right"}));
        map[3][8].add(new Laser("4A", new String[]{"left"},1));
        map[3][9].add(new Pit("4A"));
        map[3][10].add(new ConveyorBelt("4A", new String[]{"left", "right"},2));
        map[3][11].add(new ConveyorBelt("4A", new String[]{"left", "top"},2));
        map[3][12].add(new Empty("4A"));

        map[4][0].add(new Antenna("A", new String[]{"right"}));
        map[4][1].add(new StartPoint("A"));
        map[4][2].add(new Wall("A", new String[]{"right"}));
        map[4][3].add(new EnergySpace("4A", 1));
        map[4][3].add(new Wall("4A", new String[]{"top"}));
        map[4][4].add(new Empty("4A"));
        map[4][5].add(new Empty("4A"));
        map[4][6].add(new Gear("4A", new String[]{"clockwise"}));
        map[4][7].add(new Empty("4A"));
        map[4][8].add(new Empty("4A"));
        map[4][9].add(new Wall("4A", new String[]{"right"}));
        map[4][10].add(new Empty("4A"));
        map[4][11].add(new EnergySpace("4A", 1));
        map[4][12].add(new Wall("4A", new String[]{"top"}));

        map[5][0].add(new Empty("A"));
        map[5][1].add(new StartPoint("A"));
        map[5][2].add(new Wall("A", new String[]{"right"}));
        map[5][3].add(new Wall("4A", new String[]{"bottom"}));
        map[5][4].add(new Empty("4A"));
        map[5][5].add(new Empty("4A"));
        map[5][6].add(new Wall("4A", new String[]{"left"}));
        map[5][7].add(new EnergySpace("4A", 1));
        map[5][8].add(new Empty("4A"));
        map[5][9].add(new Gear("4A", new String[]{"counterclockwise"}));
        map[5][10].add(new Empty("4A"));
        map[5][11].add(new Empty("4A"));
        map[5][12].add(new Wall("4A", new String[]{"bottom"}));

        map[6][0].add(new StartPoint("A"));
        map[6][1].add(new Empty("A"));
        map[6][2].add(new Empty("A"));
        map[6][3].add(new Empty("4A"));
        map[6][4].add(new ConveyorBelt("4A", new String[]{"right", "bottom"},2));
        map[6][5].add(new ConveyorBelt("4A", new String[]{"right", "left"},2));
        map[6][6].add(new Pit("4A"));
        map[6][7].add(new Wall("4A", new String[]{"left"}));
        map[6][7].add(new Laser("4A", new String[]{"right"},1));
        map[6][8].add(new Wall("4A", new String[]{"right"}));
        map[6][8].add(new Laser("4A", new String[]{"left"},1));
        map[6][9].add(new Pit("4A"));
        map[6][10].add(new ConveyorBelt("4A", new String[]{"left", "right"},2));
        map[6][11].add(new ConveyorBelt("4A", new String[]{"left", "bottom"},2));
        map[6][12].add(new Empty("4A"));

        map[7][0].add(new StartPoint("A"));
        map[7][1].add(new Wall("4A", new String[]{"bottom"}));
        map[7][2].add(new StartPoint("A"));
        map[7][3].add(new Empty("4A"));
        map[7][4].add(new ConveyorBelt("4A", new String[]{"top", "bottom"},2));
        map[7][5].add(new CheckPoint("4A",1));
        map[7][5].add(new Wall("4A", new String[]{"bottom"}));
        map[7][5].add(new Laser("4A", new String[]{"top"},1));
        map[7][6].add(new Pit("4A"));
        map[7][7].add(new Empty("4A"));
        map[7][8].add(new Empty("4A"));
        map[7][9].add(new Pit("4A"));
        map[7][10].add(new CheckPoint("4A",2));
        map[7][10].add(new Wall("4A", new String[]{"bottom"}));
        map[7][10].add(new Laser("4A", new String[]{"top"},1));
        map[7][11].add(new ConveyorBelt("4A", new String[]{"top", "bottom"},2));
        map[7][12].add(new Empty("4A"));

        map[8][0].add(new Empty("A"));
        map[8][1].add(new StartPoint("A"));
        map[8][2].add(new Empty("A"));
        map[8][3].add(new Empty("4A"));
        map[8][4].add(new ConveyorBelt("4A", new String[]{"top", "bottom"},2));
        map[8][5].add(new Empty("4A"));
        map[8][6].add(new Empty("4A"));
        map[8][7].add(new Empty("4A"));
        map[8][8].add(new ConveyorBelt("4A", new String[]{"right", "left"},1));
        map[8][9].add(new ConveyorBelt("4A", new String[]{"top", "left"},1));
        map[8][10].add(new Empty("4A"));
        map[8][11].add(new ConveyorBelt("4A", new String[]{"top", "bottom"},2));
        map[8][12].add(new Empty("4A"));

        map[9][0].add(new Empty("A"));
        map[9][1].add(new Empty("A"));
        map[9][2].add(new ConveyorBelt("A", new String[]{"right", "left"}, 1));
        map[9][3].add(new EnergySpace("4A", 1));
        map[9][4].add(new Wall("4A", new String[]{"left"}));
        map[9][4].add(new Laser("4A", new String[]{"right"},1));
        map[9][5].add(new Empty("4A"));
        map[9][6].add(new Wall("4A", new String[]{"left"}));
        map[9][6].add(new Laser("4A", new String[]{"left"},1));
        map[9][7].add(new Empty("4A"));
        map[9][8].add(new Empty("4A"));
        map[9][9].add(new Empty("4A"));
        map[9][10].add(new Empty("4A"));
        map[9][11].add(new ConveyorBelt("4A", new String[]{"top", "bottom"},2));
        map[9][12].add(new Empty("4A"));

        this.setMap(map);
    }

}

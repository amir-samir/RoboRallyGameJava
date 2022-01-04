package game.Maps;

import game.Board.*;

import java.util.HashMap;

public class DizzyHighway extends Board {
    private String name = "DizzyHighway";
    private int width = 10;
    private int height = 10;

    public DizzyHighway() {
        BoardElement[][] map = new BoardElement[10][10];
        //1.row
        map[0][0] = new Empty();
        map[0][1] = new Belt(new HashMap<String, BeltEffect>() {{
            put("0", new BeltEffect("2", 2, true));
            put("1", new BeltEffect("2", 2, true));
            put("2", new BeltEffect("2", 2, true));
            put("3", new BeltEffect("2", 2, true));
        }});
        map[0][3] = new Empty();
        map[0][4] = new Empty();
        map[0][5] = new Empty();
        map[0][6] = new Empty();
        map[0][7] = new Empty();
        map[0][8] = new Empty();
        map[0][9] = new EnergySpaces(1);

        //row two

        // 如果从012三个方向来就向下2个格子
        // 如果从3来就向下2个格子，并且转向
        map[1][1] = new Belt(new HashMap<String, BeltEffect>() {{
            put("0", new BeltEffect("2", 2, true));
            put("1", new BeltEffect("2", 2, true));
            put("2", new BeltEffect("2", 2, true));
            put("3", new BeltEffect("2", 2, true));
        }});

        //如果是旋转传送带 比如↗这个方向，1格
//        map[9][9] = new Belt( new HashMap<String, BeltEffect>(){{
//            put("0",null);
//            put("1",new BeltEffect("2",1,true));
//            put("2",null);
//            put("3",null);
//        }});

        this.setMap(map);
    }
}

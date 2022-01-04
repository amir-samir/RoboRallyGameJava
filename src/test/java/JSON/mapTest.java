package JSON;

import com.google.gson.internal.LinkedTreeMap;
import game.Board.BoardElement;
import game.Messages.Adopter;
import game.Messages.Message;
import org.junit.Test;

import java.util.ArrayList;

public class mapTest {

    public void generateMap(Message m){
        BoardElement[][] map = new BoardElement[5][5];
        ArrayList<Object> list = (ArrayList<Object>) m.getMessageBody().getContent()[0];

        int x = 0;
        while (x < list.size()){
            ArrayList<Object> y_list = (ArrayList<Object>) list.get(x);
            int y = 0;
            while (y < list.size()){
            }
        }
    }

    @Test
    public void Test(){
        String map = "{ \"messageType\": \"GameStarted\",\"messageBody\": {\"gameMap\": [[[{ " +
                "\"type\": \"ConveyorBelt\",\"isOnBoard\": \"1B\",\"speed\": 2,\"orientations\": [" +
                "\"top\",\"right\",\"bottom\"]}],[{\"type\": \"PushPanel\",\"isOnBoard\": \"1B\"," +
                "\"orientations\": [\"left\"],\"registers\": [2, 4]}]],[[{\"type\":\"Wall\"," +
                "\"isOnBoard\":\"4A\",\"orientations\":[\"top\",\"right\"]},{\"type\":\"Laser\"," +
                "\"isOnBoard\":\"4A\",\"orientations\":[\"bottom\"],\"count\":2}],[null]]]}}";

        Message m = Adopter.getMessage(map);
        System.out.println(m.getMessageBody().getContent());

        generateMap(m);
    }
}

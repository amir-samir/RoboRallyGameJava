import java.util.HashMap;

public class Game {

    private HashMap<Integer, ClientHandler> players = new HashMap<Integer, ClientHandler>();
    private String map;
    private final Server SERVER;


    public Game(HashMap<Integer, ClientHandler> clientHandlerMap, String selectedMap, Server server){
        SERVER = server;
        map = selectedMap;

        int i = 1;
        for (ClientHandler clientHandler: clientHandlerMap.values()){
            players.put(i, clientHandler);
            i += 1;
        }


    }
}


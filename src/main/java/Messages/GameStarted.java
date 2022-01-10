package Messages;

public class GameStarted extends Message{

    public GameStarted(Object[] gameMap){
        messageType = "GameStarted";
        messageBody = new MessageBody(gameMap);
    }

}


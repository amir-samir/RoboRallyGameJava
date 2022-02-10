package Messages;
/**
 * this class implements the specific messageType "GameStarted".
 * to inform game starts!
 * @author Luca Weyhofen
 */
public class GameStarted extends Message{

    public GameStarted(Object[] gameMap){
        messageType = "GameStarted";
        messageBody = new MessageBody(gameMap);
    }

}


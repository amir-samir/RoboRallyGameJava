package game.Messages;

public class PlayerConfirmed extends Message{
    PlayerConfirmed(int clientID, String name, int figure){
        super();

        Object[] inhalt = new Object[2];
        inhalt[1] = clientID;
        inhalt[2] = name;
        inhalt[3] = figure;


        messageType = "PlayerAdded";
        messageBody = new MessageBody(inhalt);
    }
}

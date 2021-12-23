package game.Messages;

public class SetName extends Message{
    SetName(String name,int figure){
        super();

        Object[] inhalt = new Object[2];
        inhalt[1] = name;
        inhalt[2] = figure;


        messageType = "PlayerValues";
        messageBody = new MessageBody(inhalt);
    }
}

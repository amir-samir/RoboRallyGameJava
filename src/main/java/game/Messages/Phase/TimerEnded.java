package game.Messages.Phase;

import game.Messages.Message;
import game.Messages.MessageBody;

public class TimerEnded extends Message {

    public TimerEnded(Integer[] ids){
        setMessageType("TimerEnded");
        Object[] daten = {ids};
        setMessageBody(new MessageBody(daten));
    }
}

package Messages.Phase;

import Messages.Message;
import Messages.MessageBody;

/**
 * TimerEnded Nachricht
 * @author MateoPranjic
 */
public class TimerEnded extends Message {

    public TimerEnded(Integer[] ids){
        setMessageType("TimerEnded");
        Object[] daten = {ids};
        setMessageBody(new MessageBody(daten));
    }
}

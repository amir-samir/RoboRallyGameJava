package Messages.Phase;

import Messages.Message;
import Messages.MessageBody;

public class TimerEnded extends Message {

    public TimerEnded(Integer[] ids){
        setMessageType("TimerEnded");
        setMessageBody(new MessageBody(ids));
    }
}

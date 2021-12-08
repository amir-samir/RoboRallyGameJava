package Messages.Phase;

import Messages.Message;

public class TimerStarted extends Message {

    public TimerStarted(){
        setMessageType("TimerStarted");
        setMessageBody(null);
    }
}

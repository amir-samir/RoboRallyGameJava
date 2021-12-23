package game.Messages.Phase;

import game.Messages.Message;

public class TimerStarted extends Message {

    public TimerStarted(){
        setMessageType("TimerStarted");
        setMessageBody(null);
    }
}

package Messages.Phase;

import Messages.Message;

/**
 * TimerStarted Nachricht
 * @author Luca Weyhofen
 */
public class TimerStarted extends Message {

    public TimerStarted(){
        setMessageType("TimerStarted");
        setMessageBody(null);
    }
}

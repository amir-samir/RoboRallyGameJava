package Messages;

/**
 * Diese Klasse ist das Grundgerüst einer jeden verschickten Nachricht.
 * @author Luca Weyhofen
 */
public class Message {

    String messageType;
    MessageBody messageBody;

    public String getMessageType(){
        return messageType;
    }

    public MessageBody getMessageBody(){
        return messageBody;
    }

    public void setMessageBody(MessageBody messageBody) {
        this.messageBody = messageBody;
    }


    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}

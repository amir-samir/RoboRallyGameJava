package Messages;

public class Message {

    String messageType = null;
    MessageBody messageBody = null;

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

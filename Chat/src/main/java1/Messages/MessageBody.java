package Messages;

public class MessageBody {

    Object[] content;

    public MessageBody(Object[] inhalt){
        super();
        content = inhalt;
    }

    public Object[] getContent() {
        return content;
    }
}

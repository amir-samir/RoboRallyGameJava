package Messages;

public class MessageBody {

    Object[] content;
    Object[] keys;

    public MessageBody(Object[] inhalt, Object[] inhaltKeys){
        super();
        content = inhalt;
        keys = inhaltKeys;
    }

    public Object[] getContent() {
        return content;
    }

    public Object[] getkeys() {
        return keys;
    }
}

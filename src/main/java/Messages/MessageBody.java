package Messages;

/**
 * Diese Klasse stellt den Inhalt einer verschickten Nachricht dar.
 * @author Luca Weyhofen
 */
public class MessageBody {

    Object[] content;
    Object[] keys;

    public MessageBody(Object[] inhalt){
        super();
        content = inhalt;
    }

    public void setKeys(Object[] keys) {
        this.keys = keys;
    }

    public Object[] getKeys() {
        return keys;
    }


    public Object[] getContent() {
        return content;
    }

    public Object[] getkeys() {
        return keys;
    }
}

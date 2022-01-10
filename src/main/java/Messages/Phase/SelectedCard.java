package Messages.Phase;
import Messages.Message;
import Messages.MessageBody;

public class SelectedCard extends Message {

    public SelectedCard(String card, int register){
        super();
        Object[] daten = new Object[2];
        daten[0] = card;
        daten[1] = register;

        this.setMessageType("SelectedCard");
        MessageBody b = new MessageBody(daten);
        this.setMessageBody(b);
    }
}

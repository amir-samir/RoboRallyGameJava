package Messages.Phase;

import Messages.Message;

public class SelectedCard extends Message {

    public SelectedCard(String card, int register){
        Object[] daten = new Object[2];
        daten[1] = card;
        daten[2] = register;
    }
}

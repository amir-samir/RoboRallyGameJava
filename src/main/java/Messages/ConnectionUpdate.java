package Messages;

public class ConnectionUpdate extends Message {
    public ConnectionUpdate(int id, boolean isConnected, String action) {
        Object[] daten = new Object[3];
        daten[0] = id;
        daten[1] = isConnected;
        daten[2] = action;

        messageType = "ConnectionUpdate";
        messageBody = new MessageBody(daten);
    }
}

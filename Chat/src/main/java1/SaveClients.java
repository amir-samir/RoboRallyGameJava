import javax.swing.text.html.ListView;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class SaveClients {
    public static Client client;
    public static List<Client> clientListForGui = new LinkedList<>();
    //Client client;

    public void setClient(Client client) {
        this.client = client;
    }
    public Client getClient(){
        return client;
    }
    public void addClientToList(Client client){

    }
}

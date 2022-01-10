import java.util.LinkedList;
import java.util.List;

public class SaveClients {
    public static Client client;
    public static List<Client> clientListForGui = new LinkedList<>();
    //game.Client client;

    public void setClient(Client client) {
        SaveClients.client = client;
    }
    public Client getClient(){
        return client;
    }
    public void addClientToList(Client client){

    }
}

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**
 * Diese Klasse spiechert den Client für die GUI
 *
 * @author Amir Azim
 * @author Dairen Gonschior
 * @author Mateo
 *
 *
 * @Version: 2.1
 */
public class SaveClients {
    public static Client client;
    public static List<Client> clientListForGui = new LinkedList<>();
    //game.Client client;
    public static int[] ausgewaehlteRoboter = {6,6,6,6,6,6};

    public void setClient(Client client) {
        SaveClients.client = client;
    }
    public Client getClient(){
        return client;
    }
    public void addClientToList(Client client){

    }
}
